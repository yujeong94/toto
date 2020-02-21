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

/**
 * Servlet implementation class GuideUpdateServlet
 */
@WebServlet("/update.guide")
public class GuideUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuideUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gNum = Integer.parseInt(request.getParameter("gbNum"));
		String gTitle = request.getParameter("gTitle");
		String kind = request.getParameter("kind");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String gStart = request.getParameter("gStart");
		String gEnd = request.getParameter("gEnd");
		int price = Integer.parseInt(request.getParameter("price"));
		String kakao = request.getParameter("kakao");
		String gContent = request.getParameter("gContents");
		
		int kindInt = 0;
		if(kind.equals("국내")) {
			kindInt = 1;
		} else {
			kindInt = 2;
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
		
		Gboard gb = new Gboard();
		gb.setGbNum(gNum);
		gb.setgTitle(gTitle);
		gb.setKind(kindInt);
		gb.setCountry(country);
		gb.setCity(city);
		gb.setStartDate(sqlSdate);
		gb.setEndDate(sqlEdate);
		gb.setPrice(price);
		gb.setKakao(kakao);
		gb.setgContent(gContent);
		
		int result = new GuideService().updateGuide(gb);
		
		String page = null;
		if(result > 0) {
			request.setAttribute("msg", "게시글이 수정되었습니다.");
			page = "/detail.bo?=" + gNum;
		} else {
			request.setAttribute("msg", "게시글 수정에 실패하였습니다.");
			page = "views/guide/GuideDetail.jsp";
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
