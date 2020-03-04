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
		int sbNum = Integer.parseInt(request.getParameter("sbNum")) ;
		
		ShareService service = new ShareService() ;
		
		Share share = service.selectShare(sbNum) ;
		ArrayList<Sattachment> fileList = service.selectPicture(sbNum) ;
		ArrayList<sReply> rList = service.selectReplyList(sbNum) ;
		
		String page = null ;
		if(share != null && fileList != null) {
			request.setAttribute("share", share) ;
			request.setAttribute("fileList", fileList) ;
			request.setAttribute("rList", rList) ;
			System.out.println("[Info] 사진 게시판 상세보기에 입장합니다. 사진 게시판 주소는 다음과 같습니다.") ;
			for(int i=0; i<fileList.size(); i++)
				System.out.println("[Info] - [Picture "+i+"] ("+fileList.get(i).getChangeName()+")") ;
			System.out.println("[Info] 댓글이 로드되었습니다. 댓글은 총 ("+rList.size()+")개입니다.") ;
			for(int i=0; i<rList.size(); i++)
				System.out.println("[Info] - [Reply "+i+"] Writer:("+rList.get(i).getWriter()+") Content:("+rList.get(i).getContent()+")") ;
			page = "views/share/ShareDetailView.jsp" ;
		} else {
			request.setAttribute("msg", "사진 게시판 상세보기에 실패하였습니다.") ;
			page = "views/share/ShareListView.jsp" ;
		} request.getRequestDispatcher(page).forward(request, response) ;
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response) ;
	}
}
