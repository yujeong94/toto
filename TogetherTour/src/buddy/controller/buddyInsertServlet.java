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
		String kind = request.getParameter("kind");
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
		
		
		buddyBoard board = new buddyBoard(title, kindInt, country, city, sqlSdate, 
				sqlEdate, theme, head_cnt, gender, group_age,kakao, content);
		
	     System.out.println(board.getKind());
		
		int result = new buddyBoardService().insertBoard(writer, board);
		
		if(result > 0) {
			response.sendRedirect("list.buddy");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			request.setAttribute("msg", "게시판 등록에 실패하였습니다."); // D왜 자꾸 여기로 넘어가지냐고.. 
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
