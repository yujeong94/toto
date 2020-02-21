package guide.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guide.model.service.GuideService;

/**
 * Servlet implementation class GuideDeleteServlet
 */
@WebServlet("/delete.guide")
public class GuideDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuideDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gbNum = Integer.parseInt(request.getParameter("gbNum"));
		
		int result = new GuideService().deleteGuide(gbNum);
		
		String page = "";
		if(result > 0) {
			page="/list.guide?gbNum=" + gbNum;
			request.setAttribute("msg", "게시글이 삭제되었습니다.");
		} else {
			page="views/guide/GuideDetail.jsp";
			request.setAttribute("msg", "게시글 삭제에 실패했습니다.");
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
