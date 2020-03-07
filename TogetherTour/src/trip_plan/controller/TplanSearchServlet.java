package trip_plan.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trip_plan.model.vo.PageInfo;
import trip_plan.model.service.TplanService;
import trip_plan.model.vo.Tplan;


/**
 * Servlet implementation class TplanSearchServlet
 */
@WebServlet("/search.trip")
public class TplanSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TplanSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String menu = request.getParameter("menu");
		String content = request.getParameter("content");
		
		TplanService service = new TplanService();
		
		String search = "on";
		
		int listCount = service.getSearchCount(menu,content); // 게시판 리스트 개수
		
		int currentPage;	// 현재 페이지 표시
		int limit;			// 한 페이지에 표시될 페이징 수
		int maxPage;		// 전체 페이지 중 가장 마지막 페이지
		int startPage;		// 페이징 된 페이지 중 시작 페이지
		int endPage;		// 페이징 된 페이지 중 마지막 페이지
		
		currentPage =1 ;
		if(request.getParameter("currentPage") != null) {
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
		
		ArrayList<Tplan> list = service.searchList(currentPage, menu, content);
		
		String page = null;
		if(list != null) {
			page = "views/trip_plan/TplanList.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			request.setAttribute("search", search);
			request.setAttribute("menu", menu);
			request.setAttribute("content", content);
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
