package main.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import buddy.model.vo.buddyBoard;
import review.model.vo.Review;

public class mainSearchDAO {
	
	private Properties prop = new Properties();
	
	public mainSearchDAO() {
		String fileName = mainSearchDAO.class.getResource("/sql/buddyBoard/buddyboard_query.properties").getPath();
			try {
				prop.load(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}	 
	
	public int getSearchCount(Connection conn, int kind, String country, String city) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int result = 0;
		
		String query = prop.getProperty("getLocationSearchCount");
		//String query="";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, kind);
			pstmt.setString(2, country);
			pstmt.setString(3, city);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<buddyBoard> searchList(Connection conn, int currentPage,int kind, String country, String city) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<buddyBoard> list = null;
		int posts = 10; // 한 페이지에 보여질 게시글 개수
		
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;
		
		String query = prop.getProperty("getLocationSearch");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, kind);
			pstmt.setString(2, country);
			pstmt.setString(3, city);
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<buddyBoard>();
	
			
			while(rset.next()) {
				list.add(new buddyBoard(
									rset.getInt("bnum"),
									rset.getString("title"),
									rset.getString("country"),
									rset.getString("city"),
									rset.getDate("start_date"),
									rset.getInt("head_cnt"),
									rset.getString("nick"),
									rset.getInt("bcount"),
									rset.getDate("create_date")
								));					
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		 
		return list;
	}

	public int getDateSearchCount(Connection conn, Date sqlStartDate, Date sqlLastDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int result = 0;
		
		String query = prop.getProperty("getDateSearchCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setDate(1, sqlStartDate);
			pstmt.setDate(2, sqlLastDate);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<buddyBoard> DateSearchList(Connection conn, int currentPage,Date sqlStartDate, Date sqlLastDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<buddyBoard> list = null;
		int posts = 10; // 한 페이지에 보여질 게시글 개수
		
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;
		
		String query = prop.getProperty("getDateSearch");
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setDate(1, sqlStartDate);
			pstmt.setDate(2, sqlLastDate);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<buddyBoard>();
	
			
			while(rset.next()) {
				list.add(new buddyBoard(
									rset.getInt("bnum"),
									rset.getString("title"),
									rset.getString("country"),
									rset.getString("city"),
									rset.getDate("start_date"),
									rset.getInt("head_cnt"),
									rset.getString("nick"),
									rset.getInt("bcount"),
									rset.getDate("create_date")
								));					
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		 
		return list;
	}
	
}