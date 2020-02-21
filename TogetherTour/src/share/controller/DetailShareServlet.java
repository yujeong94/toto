package share.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.api.message.Attachment;

import share.model.service.ShareService ;
import share.model.vo.* ;

/** Servlet implementation class DetailShareServlet */
@WebServlet("/detail.share") 
public class DetailShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** @see HttpServlet#HttpServlet() */
    public DetailShareServlet() {
        super() ;
    }

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sbNum = Integer.parseInt(request.getParameter("bid"));
		
		ShareService service = new ShareService();
		
		Share share = service.selectShare(sbNum);
		ArrayList<Sattachment> fileList = service.selectPicture(sbNum) ;
		
		String page = null ;
		if(share != null && fileList != null) {
			request.setAttribute("share", share) ;
			request.setAttribute("fileList", fileList) ;
			page = "views/share/ShareDetailView.jsp" ;
		} else {
			request.setAttribute("msg", "사진 게시판 상세보기에 실패하였습니다.") ;
			page = "views/common/errorPage.jsp" ;
		} request.getRequestDispatcher(page).forward(request, response) ;
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response) ;
	}

}
