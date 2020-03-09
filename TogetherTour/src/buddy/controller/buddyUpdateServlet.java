package buddy.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buddy.model.service.buddyBoardService;
import buddy.model.vo.buddyBoard;

/**
 * Servlet implementation class buddyUpdateServlet
 */
@WebServlet("/update.buddy")
public class buddyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** 
     * @see HttpServlet#HttpServlet()
     */
    public buddyUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		String title = request.getParameter("title");
		String kind = request.getParameter("kind");
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String theme = request.getParameter("theme");
		int head_cnt = Integer.parseInt(request.getParameter("head_cnt"));
		String gender = request.getParameter("gender");
		String group_age =request.getParameter("group_age");
		String content = request.getParameter("content");
		String kakao = request.getParameter("kakao");
		int kindInt = 0;
			
		if(kind.equals("국내")) {
			kindInt = 1;
		} else {
			kindInt = 2;
		}
		
		Date sqlSdate = null;
		Date sqlEdate = null;
		
		if(start_date != "" && end_date != "") {
			
			String[] startArr = start_date.split("-");
			String[] endArr = end_date.split("-");
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
		
		int age = 0;
		
		switch(group_age) {
		case "10대" : age = 10; break;
		case "20대 이상" : age = 20; break;
		case "30대 이상" : age = 30; break;
		case "40대 이상" : age = 40; break;
		default : age = 0;
		}

		
		buddyBoard b = new buddyBoard();
		b.setBnum(bnum);
		b.setTitle(title);
		b.setKind(kindInt);
		b.setCountry(country);
		b.setCity(city);
		b.setStart_date(sqlSdate);
		b.setEnd_date(sqlEdate);
		b.setTheme(theme);
		b.setGroup_age(age);
		b.setContent(content);
		b.setGender(gender);
		b.setHead_cnt(head_cnt);
		b.setKakao(kakao);
		
		int result = new buddyBoardService().updateBoard(b);
		
		String page = null;
		if(result > 0) {
			request.setAttribute("msg", "게시글이 수정되었습니다.");
			page = "/list.buddy?=" + bnum;
		} else {
			request.setAttribute("msg", "게시글 수정에 실패하였습니다.");
			page = "views/buddy/buddyBoardDetail.jsp";
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
