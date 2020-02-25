package qna.model.dao;

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

import qna.model.vo.Qna;
import qna.model.vo.Reply;

public class QnaDAO {
	
	private Properties prop = new Properties();
	
	public QnaDAO() {
		String fileName = QnaDAO.class.getResource("/sql/qna/qna-query.properties").getPath();
		
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

	public ArrayList<Qna> selectList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Qna> list = null;
		int posts = 10; // 한 페이지에 보여질 게시글 개수
		
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;
		
		String query = prop.getProperty("selectList");
		
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Qna>();
			
			while(rset.next()) {
				Qna q = new Qna(rset.getInt("qnum"),
									rset.getString("title"),
									rset.getString("nick"),
									rset.getDate("create_Date"),
									rset.getInt("qcount"),
									rset.getString("status"));
				list.add(q);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int updateCount(Connection conn, int qNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Qna selectQna(Connection conn, int qNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Qna qna = null;
		
		String query = prop.getProperty("selectQna");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qna = new Qna(rs.getInt("qnum"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getString("mid"),
						rs.getDate("create_date"),
						rs.getInt("qcount"),
						rs.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return qna;
	}

	public int updateQna(Connection conn, Qna qna) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateQna");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, qna.getTitle());
			pstmt.setString(2, qna.getContent());
			pstmt.setInt(3, qna.getqNum());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteQna(Connection conn, int qNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteQna");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertQna(Connection conn, Qna q) {
		PreparedStatement pstmt = null;
		int result = 0;
	
		
		String query = prop.getProperty("insertQna");
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, q.getmId());
			pstmt.setString(2, q.getContent());
			pstmt.setString(3, q.getTitle());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertReply(Connection conn, Reply r) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, r.getrContent());
			pstmt.setInt(2, r.getRefrNum());
			pstmt.setString(3, r.getrWriter());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Reply> selectReplyList(Connection conn, int qNum) {
		PreparedStatement pstmt = null;
		ResultSet rs =  null;
		ArrayList<Reply> list = null;
		
		String query = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, qNum);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<Reply>();

			while(rs.next()) {
				list.add(new Reply(rs.getInt("rid"),
									rs.getString("rcontent"),
									rs.getInt("ref_qnum"),
									rs.getString("writer"),
									rs.getDate("create_date"),
									rs.getString("status")));

			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	

}
