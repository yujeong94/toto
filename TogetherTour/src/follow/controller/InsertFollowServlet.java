package follow.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import follow.model.service.FollowService;
import follow.model.vo.Follow;
import review.model.service.ReviewService;

/**
 * Servlet implementation class InsertFollowServlet
 */
@WebServlet("/insert.fo")
public class InsertFollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertFollowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int rNum = Integer.parseInt(request.getParameter("rnum"));
		String mId = request.getParameter("mId");
		String fId = request.getParameter("fId");
		
		Follow follow = new Follow(mId, fId);
		
		int result = new FollowService().insertFollow(follow);
		
		String page="";
		
		if(result > 0) {
			page = "/detail.rv?rNum=" + rNum;
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "팔로우 등록에 실패하였습니다.");
		}
		
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
