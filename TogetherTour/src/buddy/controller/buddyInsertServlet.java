package buddy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buddy.model.service.buddyBoardService;
import buddy.model.vo.buddyBoard;

/**
 * Servlet implementation class buddyInsertServlet
 */
@WebServlet(name = "insert.buddy", urlPatterns = { "/insert.buddy" })
public class buddyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**ddd
     * @see HttpServlet#HttpServlet()
     */
    public buddyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int category = Integer.parseInt(request.getParameter("category"));
		//String userId = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
		
		buddyBoard board = new buddyBoard();
		board.setBnum(bnum);
		board.setTitle(title);
		
		//board.setbWriter(userId);//
		
		int result = new buddyBoardService().insertBoard(board, category);
		
		if(result > 0) {
			response.sendRedirect("list.buddy");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			request.setAttribute("msg", "게시판 등록에 실패하였습니다.");
			view.forward(request, response);
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
