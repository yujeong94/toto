package trip_plan.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import trip_plan.model.service.TplanService;
import trip_plan.model.vo.Tplan;

/**
 * Servlet implementation class TplanInsertServlet
 */
@WebServlet("/insert.trip")
public class TplanInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TplanInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		int kind = Integer.parseInt(request.getParameter("kind"));
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String sDate = request.getParameter("tStart"); 
		String eDate = request.getParameter("tEnd"); 
		int day = Integer.parseInt(request.getParameter("tDay"));
		
		// 일차별 여행 일정 내용을 배열로 받아서
		String[] tContentsArr = request.getParameterValues("tContents");
		// "-"로 조인한 후 tContents에 담는다.
		String tContents = "";
		if(tContentsArr != null) {
			tContents = String.join("-", tContentsArr);
		}
		
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getmId();		
		
		Date sqlSdate = null; 
		Date sqlEdate = null;
		
		if(sDate != "" && eDate != "") {
			String[] sDateArr = sDate.split("-");
			String[] eDateArr = eDate.split("-");
			
			int syear = Integer.parseInt(sDateArr[0]);
			int smonth = Integer.parseInt(sDateArr[1])-1;
			int sday = Integer.parseInt(sDateArr[2]);
			
			int eyear = Integer.parseInt(eDateArr[0]);
			int emonth = Integer.parseInt(eDateArr[1])-1;
			int eday = Integer.parseInt(eDateArr[2]);
			
			sqlSdate = new Date(new GregorianCalendar(syear, smonth, sday).getTimeInMillis());
			sqlEdate = new Date(new GregorianCalendar(eyear,emonth,eday).getTimeInMillis());
			
		} else {
			sqlSdate = new Date(new GregorianCalendar().getTimeInMillis());
			sqlEdate = new Date(new GregorianCalendar().getTimeInMillis());
		}
		
		Tplan t = new Tplan();
		t.setTitle(title);
		t.setmId(userId);
		t.setDay(day);
		t.setContent(tContents);
		t.setCountry(country);
		t.setCity(city);
		t.setStartDate(sqlSdate);
		t.setEndDate(sqlEdate);
		t.setKind(kind);
		
		int result = new TplanService().insertTplan(t);
		
		if(result > 0) {
			response.sendRedirect("list.trip");
		} else {
			request.setAttribute("msg", "여행 일정 등록에 실패했습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/trip_plan/TplanList.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
