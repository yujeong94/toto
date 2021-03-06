package qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import qna.model.service.QnaService;
import qna.model.vo.Qna;
import qna.model.vo.Reply;

/**
 * Servlet implementation class ReplyInsertServlet
 */
@WebServlet("/insertReply.qna")
public class ReplyInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		int qNum = Integer.parseInt(request.getParameter("qNum"));
		
		
		Reply r = new Reply();
		r.setrWriter(writer);
		r.setrContent(content);
		r.setRefrNum(qNum);
		
		Qna qna = new Qna();
		qna.setqNum(qNum);
		
		int result = new QnaService().updateAnswerQna(qna);
		
		
		ArrayList<Reply> list = new QnaService().insertReply(r);
		
		response.setContentType("application/json; charset=UTF-8");
//		new Gson().toJson(list,response.getWriter());
		
		// 위 주석한 코드에서 날짜출력방식을 변경해줌 (02-14,2020에서 2020-02-14 이런식으로 바뀌어 출력)
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(list, response.getWriter());
		//gson.toJson(result, response.getWriter());
		
		
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
