package buddy_me.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buddy_me.model.service.MyPageService;
import buddy_me.model.vo.Report;
import member.model.vo.Member;

/**
 * Servlet implementation class InsertReportServlet
 */
@WebServlet("/insert.report")
public class InsertReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rCate = Integer.parseInt(request.getParameter("reportCate"));
		String rContents = request.getParameter("rContents");
		String gradeMid = request.getParameter("gradeMid");
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getmId();
		
		String gradeNick = request.getParameter("gradeNick");
		
		Report r = new Report(gradeMid, userId, rCate, rContents);
		int result = new MyPageService().insertReport(r);
		
		String page = null;
		if(result > 0) {
			request.setAttribute("msg", "신고완료");
			request.setAttribute("gradeNick2", gradeNick);
			page="views/buddy_me/buddyGrade.jsp";
		} else {
			request.setAttribute("msg", "신고실패");
			page="views/common/errorPage.jsp";
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
