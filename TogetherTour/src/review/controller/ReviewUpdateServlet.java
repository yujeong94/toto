package review.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.service.ReviewService;
import review.model.vo.Review;

/**
 * Servlet implementation class ReviewUpdateServlet
 */
@WebServlet("/update.rv")
public class ReviewUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int rNum = Integer.parseInt(request.getParameter("rnum"));
		String location = request.getParameter("location");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String gCheck = request.getParameter("guide");
		String guide="";
		
		if(gCheck != null) {
			guide="Y";
		} else {
			guide="N";
		}
		
		Review review = new Review();
		review.setrNum(rNum);
		review.setLocation(location);
		review.setTitle(title);
		review.setContent(content);
		review.setGuide(guide);
		
		int result = new ReviewService().updateReview(review);
		
		String page = null;
		
		if(result > 0) {
			page = "/detail.rv?rNum=" + rNum;
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 수정에 실패하였습니다.");
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
