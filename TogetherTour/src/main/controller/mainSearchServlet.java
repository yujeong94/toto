package main.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buddy.model.vo.PageInfo;
import buddy.model.vo.buddyBoard;
import main.model.service.mainSearchService;

/**
 * Servlet implementation class mainServlet
 */
@WebServlet("/search.main")
public class mainSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mainSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
 
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int kind =Integer.parseInt(request.getParameter("kind"));
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		
		//System.out.println(kind + country + city);

		mainSearchService service = new mainSearchService(); // 두 개의 서비스를 호출할 것이기 때문에 참조변수로 생성
		
		String search = "on";
		
		int listCount = service.getSearchCount(kind, country , city); // 메인에서 검색시 필요한 항목 개수
		
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
		
		ArrayList<buddyBoard> list = service.searchList(currentPage, kind, country, city);		
		
		String page = null;
		if(list != null) {
			page = "views/buddy/buddyListView.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			request.setAttribute("search", search);
			request.setAttribute("kind", kind);
			request.setAttribute("country", country);
			request.setAttribute("city",city);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 조회에 실패하였습니다.");
		}
		RequestDispatcher view = request.getRequestDispatcher(page); // 원래 
		/*RequestDispatcher view = request.getRequestDispatcher("/buddyListView.jsp"); */// 넘길페이지 주소 (buddy list)
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
