package share.controller ;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import share.model.service.ShareService;
import share.model.vo.Share;

/** Servlet implementation class DeleteShareServlet */
@WebServlet("/delete.share")
public class DeleteShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** @see HttpServlet#HttpServlet() */
    public DeleteShareServlet() {
        super();
    }

	/** @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sbNum = Integer.parseInt(request.getParameter("sbNum")) ;
		int result = new ShareService().deleteShare(sbNum) ;
		
		if(result > 0) {
			response.sendRedirect("list.share") ;
		} else {
			request.setAttribute("msg", "게시글 삭제에 실패하였습니다.") ;
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response) ;
		}	
	}

	/** @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response) ;
	}
}
