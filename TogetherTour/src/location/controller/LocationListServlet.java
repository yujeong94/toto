package location.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import location.model.service.LocationService;
import location.model.vo.Location;

/**
 * Servlet implementation class LocationLIstServlet
 */
@WebServlet("/list.loca")
public class LocationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 분류리스트 
		ArrayList<Integer> kind = new LocationService().kindList();
		
		// 국가리스트
		ArrayList<String> conList = new LocationService().conList();
		
		// 도시리스트
		ArrayList<Location> cityList = new LocationService().cityList();
		
		// 모든 리스트 담을 리스트 
		ArrayList<Object> allList = new ArrayList<Object>();
		
		allList.add(kind);
		allList.add(conList);
		allList.add(cityList);
		response.setContentType("application/json; charset=UTF-8");
	    new Gson().toJson(allList, response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
