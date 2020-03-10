package share.controller ;
 
import java.io.IOException ;
import java.util.ArrayList ;

import javax.servlet.ServletException ;
import javax.servlet.annotation.WebServlet ;
import javax.servlet.http.HttpServlet ;
import javax.servlet.http.HttpServletRequest ;
import javax.servlet.http.HttpServletResponse ;

import com.google.gson.Gson ;
import com.google.gson.GsonBuilder ;

import share.model.service.ShareService ;
import share.model.vo.sReply ;

/**
 * Servlet implementation class ReplyInsertServlet
 */
@WebServlet("/insertReply.share")
public class ReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsertServlet() {
        super() ;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer = request.getParameter("writer") ;
		String content = request.getParameter("content") ;
		int sbNum = Integer.parseInt(request.getParameter("sbNum")) ;
		
		sReply reply = new sReply() ;
		reply.setWriter(writer) ;
		reply.setContent(content) ;
		reply.setSbNum(sbNum) ;
		
		ArrayList<sReply> list = new ShareService().insertReply(reply) ;
		
		response.setContentType("application/json; charset=UTF-8") ;
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create() ;
		gson.toJson(list, response.getWriter()) ;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response) ;
	}

}
