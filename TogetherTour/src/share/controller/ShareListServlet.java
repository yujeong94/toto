package share.controller;
 
import java.io.IOException ;
import java.util.ArrayList ;

import javax.servlet.ServletException ;
import javax.servlet.annotation.WebServlet ;
import javax.servlet.http.HttpServlet ;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import share.model.service.ShareService ;
import share.model.vo.* ;

/** Servlet implementation class ShareListServlet */
@WebServlet("/list.share")
public class ShareListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** @see HttpServlet#HttpServlet() */
    public ShareListServlet() {
        super() ;
    }

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShareService service = new ShareService() ;
		ArrayList<Share>		sList = service.selectShareList(1) ;
		ArrayList<Sattachment>	fList = service.selectShareList(2) ;
		String page = null ;
		if(sList != null && fList != null) {
			request.setAttribute("sList", sList) ;
			request.setAttribute("fList", fList) ;
			page = "views/share/ShareListView.jsp" ;
		} else {
			request.setAttribute("msg","나눔 게시판 조회에 실패했습니다.") ;
			page = "views/common/errorPage.jsp" ;
		} request.getRequestDispatcher(page).forward(request, response) ;
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response) ;
	}
}
