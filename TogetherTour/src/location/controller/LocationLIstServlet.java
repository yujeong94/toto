package location.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import location.model.service.LocationService;

/**
 * Servlet implementation class LocationLIstServlet
 */
@WebServlet("/list.loca")
public class LocationLIstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationLIstServlet() {
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
		
		// 국내도시리스트
		ArrayList<String> inCityList = new LocationService().inCityList();
		//해외도시리스트
		ArrayList<String> outCityList = new LocationService().outCityList();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
