package share.controller; 

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.model.service.ReviewService;
import share.model.service.ShareService;

/**
 * Servlet implementation class ReplyDeleteServlet
 */
@WebServlet("/Rdelete.share")
public class ReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("delete Reply Servlet");
		int sbNum = Integer.parseInt(request.getParameter("sbNum"));
		System.out.println("sbNum"+sbNum);
		int rId = Integer.parseInt(request.getParameter("rId"));
		
		int result = new ShareService().deleteReply(rId);
		
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/detail.share?sbNum="+sbNum);
		} else {
			request.setAttribute("msg", "댓글 삭제에 실패하였습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
