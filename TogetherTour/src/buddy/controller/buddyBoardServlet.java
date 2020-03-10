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
import buddy.model.vo.PageInfo;
import buddy.model.vo.buddyBoard;

/**
 * Servlet implementation class buddyBoardServlet
 */
@WebServlet("/list.buddy")
public class buddyBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buddyBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		buddyBoardService service = new  buddyBoardService();
	
		int listCount = service.getListCount();	// 게시판 리스트 개수
		
		
		int currentPage;	// 현재 페이지 표시
		int limit;			// 한 페이지에 표시될 페이징 수
		int maxPage;		// 전체 페이지 중 가장 마지막 페이지
		int startPage;		// 페이징 된 페이지 중 시작 페이지
		int endPage;		// 페이징 된 페이지 중 마지막 페이지
		
		currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			// 현재페이지를 전달 받았으면
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
				// 페이지 전환 시 전달 받은 페이지로 currentPage 적용
		}
		
		limit = 10;
		maxPage = (int)((double)listCount/limit + 0.9);
		
	startPage = (((int)((double)currentPage/limit + 0.9)) - 1) * limit + 1;
	endPage = startPage + limit - 1;
	if(maxPage < endPage) {
		endPage = maxPage;
	}
	
	PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
	
	ArrayList<buddyBoard> list = service.selectList(currentPage); // 서블릿 확인

	String page = null;
	if(list != null) {
		page = "views/buddy/buddyListView.jsp";
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
	} else { 
		page = "views/common/errorPage.jsp";
		request.setAttribute("msg", "게시판 조회에 실패하였습니다.");
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
