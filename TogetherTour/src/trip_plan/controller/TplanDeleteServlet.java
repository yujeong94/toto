package trip_plan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trip_plan.model.service.TplanService;


/**
 * Servlet implementation class TplanDeleteServlet
 */
@WebServlet("/delete.trip")
public class TplanDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TplanDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int tPnum = Integer.parseInt(request.getParameter("tPnum"));
		int result = new TplanService().deleteTplan(tPnum);
	
		String page = null;
		
		if(result > 0) {
			page="/list.trip?tPnum=" + tPnum;
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
