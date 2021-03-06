package buddy_me.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buddy_me.model.vo.Buddy;
import member.model.vo.Member;
import buddy_me.model.service.MyPageService;

/**
 * Servlet implementation class MyBuddyListServlet
 */
@WebServlet("/list.myBuddy")
public class MyBuddyListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBuddyListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getmId();
		ArrayList<Buddy> mybuddyList =new MyPageService().mybuddyList(userId);
		
		String page = null;
		if(mybuddyList != null) {
			request.setAttribute("mybuddyList", mybuddyList);
			page="views/buddy_me/myBuddyList.jsp";
		} else {
			request.setAttribute("msg", "동행자 목록 조회에 실패했습니다");
			page="views/common/errorPage.jsp";
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
