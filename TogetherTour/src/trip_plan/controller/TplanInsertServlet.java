package trip_plan.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import trip_plan.model.vo.Tplan;

/**
 * Servlet implementation class TplanInsertServlet
 */
@WebServlet("/insert.trip")
public class TplanInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TplanInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String startDate = request.getParameter("start_date"); 
		int day = Integer.parseInt(request.getParameter("day"));
		int kind = Integer.parseInt(request.getParameter("kind"));
		String country = request.getParameter("country");
		String city = request.getParameter("city");
		String memoInsert1 = request.getParameter("memo1"); 
		/*String userId = ((Member)request.getSession().getAttribute("loginUser")).getUserId();*/		
		
		Date sqlDate = null; 
		
		if(startDate != "") {
			String[] startDateArr = startDate.split("-");
			
			int syear = Integer.parseInt(startDateArr[0]);
			int smonth = Integer.parseInt(startDateArr[1])-1;
			int sday = Integer.parseInt(startDateArr[2]);
			
			sqlDate = new Date(new GregorianCalendar(syear, smonth, sday).getTimeInMillis());
			
		} else {
			sqlDate = new Date(new GregorianCalendar().getTimeInMillis());
		}
		
		Tplan t = new Tplan();
		t.setTitle(title);
		t.setStartDate(sqlDate);
		t.setDay(day);
		t.setKind(kind);
		t.setCountry(country);
		t.setCity(city);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
