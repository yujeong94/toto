package guide.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guide.model.service.GuideService;

/**
 * Servlet implementation class gReplyDeleteServlet
 */
@WebServlet("/rdelete.guide")
public class gReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gReplyDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gNum = Integer.parseInt(request.getParameter("gnum"));
		int grId = Integer.parseInt(request.getParameter("grId"));
		
		int result = new GuideService().deleteReply(grId);
		
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath()+"/detail.guide?gbNum=" +  gNum);
		} else {
			request.setAttribute("msg", "댓글 삭제에 실패하였습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
