package buddy_me.model.dao;

import static common.JDBCTemplate.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import buddy_me.model.vo.Buddy;
import buddy_me.model.vo.Report;

public class MyPageDAO {
	
	private Properties prop = new Properties();
	
	public MyPageDAO () {
		String fileName = MyPageDAO.class.getResource("/sql/buddy_me/buddy-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> selectBnum(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Integer> bnumList = new ArrayList<Integer>();
		
		String query = prop.getProperty("selectBnum");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,userId);
			pstmt.setString(2, userId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				bnumList.add(rset.getInt("bnum"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return bnumList;
	}

	public ArrayList<Buddy> mybuddyList(Connection conn, ArrayList<Integer> bnumList, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Buddy> mybuddyList = new ArrayList<Buddy>();
		
		String query = prop.getProperty("mybuddyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			for(int i = 0; i < bnumList.size(); i++) {
				pstmt.setInt(1, bnumList.get(i));
				pstmt.setString(2, userId);
				rset = pstmt.executeQuery();
				if(rset != null) {
					while(rset.next()) {
						mybuddyList.add(new Buddy(
												  rset.getString("mid"),
												  rset.getString("nick"),
												  rset.getString("name"),
												  rset.getString("writer_nick"),
												  rset.getString("writer_name"),
												  rset.getString("country"),
												  rset.getString("city"),
												  rset.getDate("start_date"),
												  rset.getDate("end_date"),
												  rset.getInt("head_cnt")));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return mybuddyList;
	}
	
	public int updateGcount(Connection conn, String gradeNick) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateGcount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gradeNick);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
		return result;
	}

	public int updateGrade(Connection conn, String gradeNick, int grade) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("updateGrade");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, grade);
			pstmt.setString(2, gradeNick);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int resetGrade(Connection conn, String gradeNick, int grade) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("resetGrade");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, grade);
			pstmt.setString(2, gradeNick);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertReport(Connection conn, Report r) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReport");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, r.getRmid());
			pstmt.setString(2, r.getMid());
			pstmt.setInt(3, r.getRsNum());
			pstmt.setString(4, r.getReason());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectCount(Connection conn, int bnum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int headC = 0;
		
		String query = prop.getProperty("selectCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bnum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				headC = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return headC;
	}
	
	public int checkInfo(Connection conn, int ageNum, ArrayList<String> infoArr) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("checkInfo");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, infoArr.get(2));
			pstmt.setString(2, infoArr.get(1));
			pstmt.setInt(3, ageNum);
			
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

	public int selectWriter(Connection conn, int bnum, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int check = 0;
		String query = prop.getProperty("selectWriter");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bnum);
			pstmt.setString(2, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				check = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return check;
	}

	public int insertBuddy(Connection conn, Buddy b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBuddy");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getbNum());
			pstmt.setString(2, b.getmId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertAll(Connection conn, Buddy b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAll");
		
		try {
			for(int i = 0; i < 2; i++) {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, b.getbNum());
				if(i == 0) {
					pstmt.setString(2, b.getmId());					
				} else {
					pstmt.setString(2, b.getWriter_mid());
				}
				result = pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
