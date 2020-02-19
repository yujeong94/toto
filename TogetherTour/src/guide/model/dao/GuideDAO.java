package guide.model.dao;

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

import guide.model.vo.Gboard;

public class GuideDAO {
	private Properties prop = new Properties();
	
	public GuideDAO () {
		String fileName = GuideDAO.class.getResource("/sql/guide/guide-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getListCount(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("getListCount");
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return result;
	}

	public ArrayList<Gboard> selectList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Gboard> list = null;
		int posts = 10; // 한페이지에 보여질 페이지 수
		
		int startRow = (currentPage - 1)*posts + 1; 
		int endRow = startRow + posts - 1;
		
		String query = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2,endRow);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Gboard>();
			
			while(rset.next()) {
				list.add(new Gboard(rset.getInt("gbnum"),
									rset.getString("title"),
									rset.getString("nick"),
									rset.getInt("price"),
									rset.getDate("create_date"),
									rset.getString("country"),
									rset.getString("city"),
									rset.getDate("start_date"),
									rset.getDate("end_date"),
									rset.getInt("gcount")
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
