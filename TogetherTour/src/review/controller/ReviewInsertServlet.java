package review.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import review.model.service.ReviewService;
import review.model.vo.Review;

/**
 * Servlet implementation class ReviewInsertServlet
 */
@WebServlet("/insert.rv")
public class ReviewInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String location = request.getParameter("location");
		String startDate = request.getParameter("startDate");
		String lastDate = request.getParameter("lastDate");
		String gCheck = request.getParameter("guide");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String userId = ((Member)request.getSession().getAttribute("loginUser")).getmId();
		
		String guide="";
		
		if(gCheck != null) {
			guide="Y";
		} else {
			guide="N";
		}
		
		
		
		//System.out.println("확인 : " + guide);
		
		Date sqlStartDate = null;
		Date sqlLastDate = null;
		System.out.println(startDate);
		System.out.println(lastDate);
	
		
		Review r = new Review();
		
		
		if(startDate != "") {
			String[] dateArr = startDate.split("-");
			
			int year = Integer.parseInt(dateArr[0]);
			int month = Integer.parseInt(dateArr[1])-1;
			int day = Integer.parseInt(dateArr[2]);
			
			sqlStartDate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		} else {
			sqlStartDate = new Date(new GregorianCalendar().getTimeInMillis());
		}
		
		if(lastDate != "") {
			String[] dateArr = lastDate.split("-");
			
			int year = Integer.parseInt(dateArr[0]);
			int month = Integer.parseInt(dateArr[1])-1;
			int day = Integer.parseInt(dateArr[2]);
			
			sqlLastDate = new Date(new GregorianCalendar(year, month, day).getTimeInMillis());
		} else {
			sqlLastDate = new Date(new GregorianCalendar().getTimeInMillis());
		}
		
		r = new Review(title, content,location,userId, sqlStartDate,sqlLastDate,guide);
		//Notice n = new Notice(title, content, userId, sqlDate);
		int result = new ReviewService().insertReview(r);
		
		if(result > 0) {
			response.sendRedirect("list.rv");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			request.setAttribute("msg", "게시판 등록에 실패하였습니다.");
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
