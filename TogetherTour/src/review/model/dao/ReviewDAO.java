package review.model.dao;

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

import review.model.vo.Reply;
import review.model.vo.Review;

public class ReviewDAO {
	
	private Properties prop = new Properties();
	
	public ReviewDAO() {
		String fileName = ReviewDAO.class.getResource("/sql/review/review-query.properties").getPath();
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

	public ArrayList<Review> selectList(Connection conn, int currentPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Review> list = null;
		int posts = 10; // 한 페이지에 보여질 게시글 개수
		
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;
		
		String query = prop.getProperty("selectList");
		
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Review>();
			
			while(rset.next()) {
				Review r = new Review(rset.getInt("rnum"),
									rset.getString("title"),
									rset.getString("nick"),
									rset.getInt("rcount"),
									rset.getInt("rpoint"),
									rset.getString("location"),
									rset.getDate("create_date"),
									rset.getString("status"));
				list.add(r);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int updateCount(Connection conn, int rNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount1");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Review selectReview(Connection conn, int rNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Review review = null;
		
		String query = prop.getProperty("selectReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				review = new Review(rs.getInt("rnum"),
						rs.getString("title"),
						rs.getString("mid"),
						rs.getString("content"),
						rs.getInt("rcount"),
						rs.getInt("rpoint"),
						rs.getString("location"),
						rs.getDate("create_date"),
						rs.getString("status"),
						rs.getDate("start_date"),
						rs.getDate("last_date"),
						rs.getString("guide"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return review;
		
	}

	public int insertReview(Connection conn, Review r) {
		PreparedStatement pstmt = null;
		int result = 0;
		String gCheck=null;
	
		
		String query = prop.getProperty("insertReview");
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, r.getTitle());
			pstmt.setString(2, r.getmId());
			pstmt.setString(3, r.getContent());
			pstmt.setString(4, r.getLocation());
			pstmt.setDate(5, r.getStartDate());
			pstmt.setDate(6, r.getLastDate());
			pstmt.setString(7, r.getGuide());
			
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteReview(Connection conn, int rNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;

	}

	public int updateReview(Connection conn, Review review) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateReview");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, review.getLocation());
			pstmt.setString(2, review.getTitle());
			pstmt.setString(3, review.getContent());
			pstmt.setString(4, review.getGuide());
			pstmt.setInt(5, review.getrNum());
			
			
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

	public ArrayList<Reply> selectReplyList(Connection conn, int refrNum) {
		PreparedStatement pstmt = null;
		ResultSet rs =  null;
		ArrayList<Reply> list = null;
		
		String query = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, refrNum);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<Reply>();

			while(rs.next()) {
				list.add(new Reply(rs.getInt("rid"),
									rs.getString("rcontent"),
									rs.getInt("ref_rnum"),
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
