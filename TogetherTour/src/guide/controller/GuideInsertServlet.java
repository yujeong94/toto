package guide.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import guide.model.service.GuideService;
import guide.model.vo.Gboard;
import member.model.vo.Member;

/**
 * Servlet implementation class GuideInsertServlet
 */
@WebServlet("/insert.guide")
public class GuideInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuideInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gTitle = request.getParameter("gTitle");
		String kind = request.getParameter("kind");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String gStart = request.getParameter("gStart");
		String gEnd = request.getParameter("gEnd");
		int price = Integer.parseInt(request.getParameter("price"));
		String kakao = request.getParameter("kakao");
		String gContents = request.getParameter("gContents");
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getmId();
		System.out.println(request.getParameter("price"));
		int kindNum = 0;
		if(kind.equals("국내")) {
			kindNum = 1;
		} else {
			kindNum = 2;
		}
		Date sqlSdate = null;
		Date sqlEdate = null;
		
		if(gStart != "" && gEnd != "") {
			
			String[] startArr = gStart.split("-");
			String[] endArr = gEnd.split("-");
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
		
		Gboard gboard = new Gboard(gTitle,userId,price,gContents,country,city,sqlSdate,sqlEdate,kindNum,kakao);
		
		int result = new GuideService().insertGuide(gboard);
		System.out.println(result);
		if(result > 0) {
			response.sendRedirect("list.guide");
		} else {
			request.setAttribute("msg", "가이드 게시글 등록에 실패했습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/guide/GuideListView.jsp");
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
