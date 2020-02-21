package location.model.dao;

import static common.JDBCTemplate.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

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
		ArrayList<Integer> allKind = new ArrayList<Integer>();
		ArrayList<Integer> kindList = new ArrayList<Integer>();
		
		String query = prop.getProperty("kindList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				allKind.add(rset.getInt("kind"));
			}
			
			// 중복 제거 
			for(int i = 0; i < allKind.size(); i++) {
				if(!kindList.contains(allKind.get(i))) {
					kindList.add(allKind.get(i));
				}
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
		ArrayList<String> allCon = new ArrayList<String>();
		String query = prop.getProperty("countryList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				conList.add(rset.getString("country"));
			}
			
			// 중복 제거 
			for(int i = 0; i < allCon.size(); i++) {
				if(!conList.contains(allCon.get(i))) {
					conList.add(allCon.get(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return conList;
	}

	public ArrayList<String> inCityList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<String> inCityList = new ArrayList<String>();
		String query = prop.getProperty("inCityList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				inCityList.add(rset.getString("country"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return inCityList;
	}

	public ArrayList<String> outCityList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<String> outCityList = new ArrayList<String>();
		String query = prop.getProperty("outCityList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				outCityList.add(rset.getString("country"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return outCityList;
	}


}
