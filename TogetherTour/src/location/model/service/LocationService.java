package location.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import location.model.dao.LocationDAO;

public class LocationService {

	public ArrayList<Integer> kindList() {
		Connection conn = getConnection();
		ArrayList<Integer> kindList = new LocationDAO().kindList(conn);
		close(conn);
		return kindList;
	}

	public ArrayList<String> conList() {
		Connection conn = getConnection();
		ArrayList<String> conList = new LocationDAO().conList(conn);
		close(conn);
		return conList;
	}

	public ArrayList<String> inCityList() {
		Connection conn = getConnection();
		ArrayList<String> inCityList = new LocationDAO().inCityList(conn);
		close(conn);
		return inCityList;
	}

	public ArrayList<String> outCityList() {
		Connection conn = getConnection();
		ArrayList<String> outCityList = new LocationDAO().outCityList(conn);
		close(conn);
		return outCityList;
	}

}
