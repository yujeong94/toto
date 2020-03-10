package follow.controller ;

import java.io.IOException ;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException ;
import javax.servlet.annotation.WebServlet ;
import javax.servlet.http.HttpServlet ;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;
import javax.servlet.http.HttpSession;

import follow.model.service.FollowService;
import follow.model.vo.Follow;
import member.model.vo.Member;

/**
 * Servlet implementation class MyPageFollowServlet
 */
@WebServlet("/list.follow")
public class ListFollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L ;
       
    /** @see HttpServlet#HttpServlet() */
    public ListFollowServlet() {
        super() ;
    }

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Member> mList = new ArrayList<Member>() ;
		String mId = ((Member)session.getAttribute("loginUser")).getmId() ;
		String page = "" ;
		mList = new FollowService().selectFollowList(mId) ;
		page = "views/myPage/MyPageFollowView.jsp" ;
		request.setAttribute("mList", mList) ;
		RequestDispatcher view = request.getRequestDispatcher(page) ;
		view.forward(request, response) ;
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response) ;
	}
}
