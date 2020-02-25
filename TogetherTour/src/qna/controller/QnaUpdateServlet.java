package qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.QnaService;
import qna.model.vo.Qna;

/**
 * Servlet implementation class QnaUpdateServlet
 */
@WebServlet("/update.qna")
public class QnaUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int qNum = Integer.parseInt(request.getParameter("qnum"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Qna qna = new Qna();
		qna.setqNum(qNum);
		qna.setTitle(title);
		qna.setContent(content);
		
		int result = new QnaService().updateQna(qna);
		String page = null;
		
		if(result > 0) {
			page = "/detail.qna?qNum=" + qNum;
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "게시판 수정에 실패하였습니다.");
		}
		RequestDispatcher view = request.getRequestDispatcher(page);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
