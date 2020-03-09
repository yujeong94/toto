package buddy.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buddy.model.service.buddyBoardService;
import buddy.model.vo.buddyBoard;

/**
 * Servlet implementation class buddyDetailServlet
 */
@WebServlet(name = "detail.buddy", urlPatterns = { "/detail.buddy" })
public class buddyDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet() 
     */
    public buddyDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		String headC = (String)request.getAttribute("headC");
		String msg = (String)request.getAttribute("msg");
		buddyBoard board = new buddyBoardService().selectBoard(bnum); // 
		System.out.println("디테일이다 "+ msg + headC);
		String page = null;
		if(board != null) {
			page = "views/buddy/buddyBoardDetail.jsp" ;
			request.setAttribute("headC", headC);
			request.setAttribute("board", board) ; 
			request.setAttribute("msg", msg);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 조회에 실패했습니다.");
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
