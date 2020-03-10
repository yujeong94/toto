package admin.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import buddy_me.model.vo.Report;

public class adminDAO {
	Properties prop = new Properties();
	
	public adminDAO () {
		String fileName = adminDAO.class.getResource("/sql/admin/admin_query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Report> reportList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Report> report = new ArrayList<Report>();
		
		String query = prop.getProperty("reportList");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				report.add(new Report(rset.getInt("rkey"),
									  rset.getString("rmid"),
									  rset.getString("mid"),
									  rset.getInt("rsnum"),
									  rset.getString("reason"),
									  rset.getString("status")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return report;
	}

	public int deleteUser(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteUser");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateStatus(Connection conn, int rkey) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateStatus");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rkey);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
