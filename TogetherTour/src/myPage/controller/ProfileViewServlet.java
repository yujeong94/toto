package myPage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.model.vo.Member;
import member.model.vo.mAttachment;
import myPage.model.service.MyPageService;

/**
 * Servlet implementation class ProfileViewServlet
 */
@WebServlet("/memberProfile.mypage")
public class ProfileViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userNick = request.getParameter("userNick");
		MyPageService service = new MyPageService();
		
		mAttachment userImg = service.profileImg(userNick);
		Member userProfile = service.userProfile(userNick);
		
		ArrayList<Object> allList = new ArrayList<Object>();
		
		allList.add(userImg);
		allList.add(userProfile);
		response.setContentType("application/json; charset=UTF-8");
	    new Gson().toJson(allList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
