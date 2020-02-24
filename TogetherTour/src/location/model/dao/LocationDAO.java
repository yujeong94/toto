package location.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import location.model.vo.Location;

public class LocationDAO {
	
	private Properties prop = new Properties();
	
	public LocationDAO () {
		String fileName = LocationDAO.class.getResource("/sql/location/location-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 분류리스트
	public ArrayList<Integer> kindList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Integer> kindList = new ArrayList<Integer>();
		
		String query = prop.getProperty("kindList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				kindList.add(rset.getInt("kind"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return kindList;
	}

	public ArrayList<String> conList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<String> conList = new ArrayList<String>();
		String query = prop.getProperty("countryList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				conList.add(rset.getString("country"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return conList;
	}

	public ArrayList<Location> cityList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Location> cityList = new ArrayList<Location>();
		String query = prop.getProperty("cityList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				cityList.add(new Location(rset.getString("country"),rset.getString("city")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return cityList;
	}


}
