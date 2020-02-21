package guide.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guide.model.service.GuideService;
import guide.model.vo.Gboard;
import guide.model.vo.gReply;

/**
 * Servlet implementation class GuideDetailServlet
 */
@WebServlet("/detail.guide")
public class GuideDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuideDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int gNum = Integer.parseInt(request.getParameter("gbNum"));
		
		Gboard gboard = new GuideService().selectGboard(gNum);
		
		ArrayList<gReply> rList = new GuideService().selectReplyList(gNum);
		String page = null;
		if(gboard != null) {
			page="views/guide/GuideDetail.jsp";
			request.setAttribute("gboard", gboard);
			request.setAttribute("rList", rList);
		} else {
			page="views/guide/GuideListView.jsp";
			request.setAttribute("msg", "게시글 조회에 실패했습니다");
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
