package review.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.service.ReviewService;
import review.model.vo.Reply;
import review.model.vo.Review;

/**
 * Servlet implementation class ReviewDetailServlet
 */
@WebServlet("/detail.rv")
public class ReviewDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rNum = Integer.parseInt(request.getParameter("rnum"));
		
		Review review = new ReviewService().selectReview(rNum);
		
		
		// 댓글달기 
		ArrayList<Reply> list = new ReviewService().selectReplyList(rNum);
		
		System.out.println("가이드 : " +review.getGuide());
		
		String page = null;
		if(review != null) {
			page = "views/trip_review/reviewDetailView.jsp";
			request.setAttribute("review", review);
			request.setAttribute("list", list); // 댓글 보내기
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시글 상세보기에 실패하였습니다.");
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
