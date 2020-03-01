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

import trip_plan.model.service.TplanService;
import trip_plan.model.vo.Tplan;

/**
 * Servlet implementation class TplanUpdateServlet
 */
@WebServlet("/update.trip")
public class TplanUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TplanUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int tPnum = Integer.parseInt(request.getParameter("tPnum"));
		
		String title = request.getParameter("title");
		String mid = request.getParameter("mid");
//		String create_date = request.getParameter("create_date");
		String sDate = request.getParameter("sDate");
		String eDate = request.getParameter("eDate");
		String kind = request.getParameter("kind");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String tContent = request.getParameter("tContents");
		
		int kindInt = 0;
		if(kind.equals("국내")) {
			kindInt = 1;
		} else {
			kindInt = 2;
		}
		
		Date sqlSdate = null;
		Date sqlEdate = null;
		
		if(sDate != "" && eDate != "") {
			
			String[] startArr = sDate.split("-");
			String[] endArr = eDate.split("-");
			int syear = Integer.parseInt(startArr[0]);
			int smonth = Integer.parseInt(startArr[1])-1;
			int sday = Integer.parseInt(startArr[2]);
			
			int eyear = Integer.parseInt(endArr[0]);
			int emonth = Integer.parseInt(startArr[1])-1;
			int eday = Integer.parseInt(startArr[2]);
			
			sqlSdate = new Date(new GregorianCalendar(syear,smonth,sday).getTimeInMillis());
			sqlEdate = new Date(new GregorianCalendar(eyear,emonth,eday).getTimeInMillis());
		} else {
			sqlSdate = new Date(new GregorianCalendar().getTimeInMillis());
			sqlEdate = new Date(new GregorianCalendar().getTimeInMillis());
		}
		
		Tplan t = new Tplan();
		t.settPnum(tPnum);
		t.setTitle(title);
		t.setmId(mid);
//		t.setCreateDate(create_date);
		t.setStartDate(sqlSdate);
		t.setEndDate(sqlEdate);
		t.setKind(kindInt);
		t.setCountry(country);
		t.setCity(city);
		t.setContent(tContent);
		
		int result = new TplanService().updateTplan(t);
		
		String page = null;
		if(result > 0) {
			request.setAttribute("msg", "게시글이 수정되었습니다.");
			page = "/detail.trip?tPnum=" + tPnum;
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
