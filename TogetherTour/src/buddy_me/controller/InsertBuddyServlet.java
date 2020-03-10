package buddy_me.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import buddy_me.model.service.MyPageService;
import member.model.vo.Member;

/**
 * Servlet implementation class InsertBuddyServlet
 */
@WebServlet("/insert.myBuddy")
public class InsertBuddyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertBuddyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bnum = Integer.parseInt(request.getParameter("bnum"));
		int head_cnt = Integer.parseInt(request.getParameter("head_cnt"));
		String userId = request.getParameter("userId"); // 작성자 아이디
		String gender1 = request.getParameter("gender");
		String age = request.getParameter("age");
		String buddyId = ((Member)request.getSession().getAttribute("loginUser")).getmId();
		
		String gender = null;
		if(gender1.equals("여자")) {
			gender = "F";
		} else {
			gender = "M";
		}
		
		int ageNum = 0;
		switch(age) {
		case "10대": ageNum = 10; break;
		case "20대 이상": ageNum = 20; break;
		case "30대 이상": ageNum = 30; break;
		case "40대 이상": ageNum = 40; break;
		default: ageNum = 0;
		}
		
		ArrayList<String> infoArr = new ArrayList<String>();
		
		infoArr.add(userId);
		infoArr.add(gender);
		infoArr.add(buddyId);
		
		MyPageService service = new MyPageService();
		
		// 동행인원수 카운트 
		int head = service.selectCount(bnum);
		String msg = null;
		if(head > head_cnt) {
			msg = "동행자 신청이 마감되었습니다.";
		} else {
			// 동행 등록
			int result = service.insertBuddy(bnum,ageNum,infoArr);
			if(result > 0) {
				msg = "신청되었습니다.";				
			} else {
				msg = "동행자 모집 조건에 부합하지 않습니다.";
			}
		}
		response.setContentType("application/json; charset=UTF-8");
	    new Gson().toJson(msg, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
