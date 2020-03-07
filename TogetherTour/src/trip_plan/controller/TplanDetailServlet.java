package trip_plan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trip_plan.model.service.TplanService;
import trip_plan.model.vo.Tplan;

/**
 * Servlet implementation class TripDetailServlet
 */
@WebServlet("/detail.trip")
public class TplanDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TplanDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int tPnum = Integer.parseInt(request.getParameter("tPnum"));
		
		Tplan t = new TplanService().detailTplan(tPnum);
		
		String page = null;
		
		if(t != null) {
			page = "views/trip_plan/TplanDetail.jsp";
			request.setAttribute("Tplan", t);
		} else {
			page="views/trip_plan/TplanList.jsp";
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
