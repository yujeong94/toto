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
import member.model.vo.Member;

/**
 * Servlet implementation class buddyInsertServlet
 */
@WebServlet(name = "insert.buddy", urlPatterns = { "/insert.buddy" })
public class buddyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buddyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String writer = ((Member)request.getSession().getAttribute("loginUser")).getmId();
		
		String title = request.getParameter("title");
		// 동현씨 수정 전 ------
		// String kind = request.getParameter("kind");
		
		// 동현씨 수정 후 -------
		int kind = Integer.parseInt(request.getParameter("kind"));
		
		// ------------- 이부분
		
		
		
		
		
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		String theme = request.getParameter("theme");
		int head_cnt = Integer.parseInt(request.getParameter("head_cnt"));
		String gender = request.getParameter("gender");
		int group_age = Integer.parseInt(request.getParameter("group_age"));
		String kakao = request.getParameter("kakao");
		String content=request.getParameter("content");
		
		System.out.println(gender);
		
		// 동현씨 수정
//		int kindInt = 0;
//		if(kind.equals("국내")) {
//			kindInt = 1;
//		} else {
//			kindInt = 2;
//		}
		
		// 여기까지 
		
		
		
		Date sqlSdate = null;
		Date sqlEdate = null;
		
		// 동현씨 수정 전
//		if(start_date != "" && end_date != "") {
//			
//			String[] startArr = start_date.split("-");
//			String[] endArr = end_date.split("-");
//			int syear = Integer.parseInt(startArr[0]);
//			int smonth = Integer.parseInt(startArr[1])-1;
//			int sday = Integer.parseInt(startArr[2]);
//			
//			int eyear = Integer.parseInt(endArr[0]);
//			int emonth = Integer.parseInt(startArr[1])-1;
//			int eday = Integer.parseInt(startArr[2]);
//			
//			sqlSdate = new Date(new GregorianCalendar(syear,smonth,sday).getTimeInMillis());
//			sqlEdate = new Date(new GregorianCalendar(eyear,emonth,eday).getTimeInMillis());
//		} else {
//			sqlSdate = new Date(new GregorianCalendar().getTimeInMillis());
//			sqlEdate = new Date(new GregorianCalendar().getTimeInMillis());
//		}
		
		// 동현씨 수정 후
		if(start_date != "") {
			String[] dateArr = start_date.split("-");
			
			int year = Integer.parseInt(dateArr[0]);
			int month = Integer.parseInt(dateArr[1])-1;
			int day = Integer.parseInt(dateArr[2]);
			
			sqlSdate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		} else {
			sqlSdate = new Date(new GregorianCalendar().getTimeInMillis());
		}
		
		if(end_date != "") {
			String[] dateArr = end_date.split("-");
			
			int year = Integer.parseInt(dateArr[0]);
			int month = Integer.parseInt(dateArr[1])-1;
			int day = Integer.parseInt(dateArr[2]);
			
			sqlEdate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		} else {
			sqlEdate = new Date(new GregorianCalendar().getTimeInMillis());
		}
		
		// ------- 이곳까지 마지막날짜 들어가도록 수정
		
		
		/* 동현씨 수정전
		 * buddyBoard board = new buddyBoard(title, kindInt, country, city, sqlSdate, 
		 *		sqlEdate, theme, head_cnt, gender, group_age,kakao, content);
		 */
		
		// 동현씨 수정 후 
		buddyBoard board = new buddyBoard(title, kind, country, city, sqlSdate,
				sqlEdate, theme, head_cnt, gender, group_age, kakao, content);
		
		// -----여기까지
		
		
		
	     System.out.println(board.getKind());
		
		int result = new buddyBoardService().insertBoard(writer, board);
		
		if(result > 0) {
			response.sendRedirect("list.buddy");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			request.setAttribute("msg", "게시판 등록에 실패하였습니다.");  
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
