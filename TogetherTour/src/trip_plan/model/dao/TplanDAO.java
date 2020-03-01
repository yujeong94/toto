package trip_plan.model.dao;

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

import trip_plan.model.vo.Tplan;

public class TplanDAO {
	
	private Properties prop = new Properties();
	
	public TplanDAO() {
		
		String fileName = TplanDAO.class.getResource("/sql/trip_plan/tplan-query.properties").getPath();
		
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

	public ArrayList<Tplan> selectList(Connection conn, int currentPage) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Tplan> list = null;
		int posts = 10;
		
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;
		
		String query = prop.getProperty("selectList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Tplan>();
			
			while(rset.next()) {
				Tplan t = new Tplan(rset.getInt("rNum"),
									rset.getString("title"),
									rset.getString("mid"),
									rset.getInt("day"),
									rset.getString("content"),
									rset.getDate("create_date"),
									rset.getString("country"),
									rset.getString("city"),
									rset.getInt("tcount"),
									rset.getDate("start_date"),
									rset.getDate("end_date"),
									rset.getString("status"),
									rset.getInt("kind"));
									
				list.add(t);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int insertTplan(Connection conn, Tplan t) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertTplan");
		System.out.println("country" + t.getCountry());
		System.out.println("mid" + t.getmId());
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getmId());
			pstmt.setString(3, t.getContent());
			pstmt.setString(4, t.getCountry());
			pstmt.setString(5, t.getCity());
			pstmt.setDate(6, t.getStartDate());
			pstmt.setDate(7, t.getEndDate());
			pstmt.setInt(8, t.getKind());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateCount(Connection conn, int tPnum) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tPnum);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Tplan selectTplan(Connection conn, int tPnum) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Tplan t = null;
		
		String query = prop.getProperty("selectTplan");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tPnum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	
				t = new Tplan(rs.getInt("tpnum"),
							  rs.getString("title"),
							  rs.getString("mid"),
							  rs.getInt("day"),
							  rs.getString("content"),
							  rs.getDate("create_date"),
							  rs.getString("country"),
							  rs.getString("city"),
							  rs.getInt("tcount"),
							  rs.getDate("start_date"),
							  rs.getDate("end_date"),
							  rs.getString("status"),
							  rs.getInt("kind"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return t;
	}

	public int updateTplan(Connection conn, Tplan t) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateTplan");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getContent());
			pstmt.setString(3, t.getCountry());
			pstmt.setString(4, t.getCity());
			pstmt.setDate(5, t.getStartDate());
			pstmt.setDate(6, t.getEndDate());
			pstmt.setInt(7, t.getKind());
			pstmt.setInt(8, t.gettPnum());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteTplan(Connection conn, int tPnum) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteTplan");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, tPnum);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
