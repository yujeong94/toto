package qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.model.service.QnaService;
import qna.model.vo.Qna;
import qna.model.vo.Reply;

/**
 * Servlet implementation class QnaDetailServlet
 */
@WebServlet("/detail.qna")
public class QnaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qNum = Integer.parseInt(request.getParameter("qnum"));
		Qna qna = new QnaService().selectQna(qNum);
		
		ArrayList<Reply> list = new QnaService().selectReplyList(qNum);
		
		String page = null;
		if(qna != null) {
			page = "views/qna/QnaDetailView.jsp";
			request.setAttribute("qna", qna);
			request.setAttribute("list", list); // 댓글 보내기
			//request.setAttribute("flist", flist); // 팔로우 조합보내기
			//request.setAttribute("loginUser", loginUser);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("msg", "질문 상세보기에 실패하였습니다.");
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
