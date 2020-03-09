package buddy_me.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buddy_me.model.service.MyPageService;
import member.model.vo.Member;

/**
 * Servlet implementation class InsertBuddyServlet
 */
@WebServlet("/insert.myBuddy")
public class InsertBuddyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBuddyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		String userId = request.getParameter("userId");
		String buddyId = ((Member)request.getSession().getAttribute("loginUser")).getmId();
		
		MyPageService service = new MyPageService();
		// 동행 등록
		int result = service.insertBuddy(bnum,userId,buddyId);
		
		// 동행인원수 카운트 
		int head = service.selectCount(bnum);
		String headC = head + "";
		String page = null;
		if(result > 0) {
			page = "/detail.buddy";
			request.setAttribute("headC", headC);
			request.setAttribute("msg","참가 되었습니다."); 
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "동행참가에 실패했습니다.");
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
