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
		
		/*String query = prop.getProperty("getSearchCount");*/
		String query="";
		
		try {
			pstmt = conn.prepareStatement(query);	
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
		
		String query = "";
		return list;
	}
	
}