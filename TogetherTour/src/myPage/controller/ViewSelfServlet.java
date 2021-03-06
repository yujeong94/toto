package myPage.controller;  

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
import member.model.vo.mAttachment;
import myPage.model.service.MyPageService;

/** Servlet implementation class ViewSelfServlet */
@WebServlet("/viewSelf.myPage")
public class ViewSelfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** @see HttpServlet#HttpServlet() */
    public ViewSelfServlet() {
        super() ;
    }

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession() ;
		String mId = ((Member)session.getAttribute("loginUser")).getmId() ;
		System.out.println(mId) ;
		mAttachment mAttach = new MyPageService().profileImg(mId) ;
		String page = null ;
		if(mAttach != null) {
			page = "views/myPage/MyPageContentView.jsp" ;
			request.setAttribute("mAttach", mAttach) ;
		} else {
			page = "views/common/errorPage.jsp" ;
			request.setAttribute("msg", "회원조회에 실패하였습니다.") ;
		}
		RequestDispatcher view = request.getRequestDispatcher(page) ;
		view.forward(request, response) ;
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response) ;
	}
}
