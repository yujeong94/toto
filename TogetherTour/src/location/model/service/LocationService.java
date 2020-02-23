package location.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import location.model.dao.LocationDAO;
import location.model.vo.Location;

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

	public ArrayList<Location> cityList() {
		Connection conn = getConnection();
		ArrayList<Location> cityList = new LocationDAO().cityList(conn);
		close(conn);
		return cityList;
	}

}
