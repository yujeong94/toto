package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="MemberJoinServlet", urlPatterns="/login.me")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginUserId = request.getParameter("loginUserId");
		String loginUserPwd = request.getParameter("loginUserPwd");
		
		Member member = new Member(loginUserId,loginUserPwd);
		
		Member loginUser = new MemberService().loginMember(member);
		
		/*String page = null;*/
		if(loginUser != null) {
			HttpSession session = request.getSession();
			session.setMaxInactiveInterval(3600);
			session.setAttribute("loginUser", loginUser);
			response.sendRedirect("index.jsp");
			/*page = "views/common/header.jsp";
			request.setAttribute("welcome", loginUser.getUserName()+"님 환영합니다♥");*/
		} else {
			/*page = "views/loginView.jsp";*/
			request.setAttribute("msg", "존재하지 않는 회원입니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/member/loginView.jsp");
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
