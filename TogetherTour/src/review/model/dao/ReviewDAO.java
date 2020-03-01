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

import follow.model.vo.Follow;
import review.model.vo.Reply;
import review.model.vo.Review;
import review.model.vo.rAttachment;

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

	public int updateRpoint(Connection conn, Review review) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateRpoint");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, review.getrNum());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Review> searchList(Connection conn, int currentPage, String menu, String content) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Review> list = null;
		int posts = 10; // 한 페이지에 보여질 게시글 개수
		
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts - 1;
		
		String query = "";
		
		switch(menu) {
		case "TITLE" : query = prop.getProperty("searchTitle"); break;
		case "NICK" : query = prop.getProperty("searchNick"); break;
		case "LOCATION" : query = prop.getProperty("searchLocation"); break;
		}
		
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, '%'+content+'%');
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			
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

	public int getSearchCount(Connection conn, String menu, String content) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		//String query = prop.getProperty("getSearchCount");
		String query="";
		switch(menu) {
		case "TITLE" : query = prop.getProperty("getTitleCount"); break;
		case "NICK" : query = prop.getProperty("getNickCount"); break;
		case "LOCATION" : query = prop.getProperty("getLocationCount"); break;
		}
		
		
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, '%'+content+'%');
			
			
			
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

	public ArrayList<Follow> selectFollowList(Connection conn) {
		Statement stmt = null;
		ResultSet rs=null;
		ArrayList<Follow> flist = null;
		
		String query = prop.getProperty("selectFollowList");
		
		try {
			stmt=conn.createStatement();
			
			rs=stmt.executeQuery(query);
			flist = new ArrayList<Follow>();
			
			while(rs.next()) {
				Follow f = new Follow(rs.getString("mid"), rs.getString("fid"));
		
				flist.add(f);
				
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return flist;
	}

	public int insertrAttachment(Connection conn, ArrayList<rAttachment> fileList) {
		PreparedStatement pstmt = null;
		int result  = 0;
		
		String query = prop.getProperty("insertrAttachment");
		
		
		
		try {
			
			for(int i =0; i <fileList.size(); i++) {
				rAttachment a = fileList.get(i);
			
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, a.getOriginName());
				pstmt.setString(2, a.getChangeName());
				pstmt.setString(3, a.getFilePath());
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<rAttachment> selectThumbnail(Connection conn, int rNum) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<rAttachment> list = null;
		
		String query = prop.getProperty("selectThumbnail");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rNum);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<rAttachment>();
			
			while(rs.next()) {
				rAttachment at = new rAttachment();
				at.setfId(rs.getInt("fid"));
				at.setOriginName(rs.getString("origin_name"));
				at.setChangeName(rs.getString("change_name"));
				at.setFilePath(rs.getString("file_path"));
				at.setUploadDate(rs.getDate("upload_date"));
				
				list.add(at);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return list;
	}

	public int insertNewAttachment(Connection conn, ArrayList<rAttachment> newInsertFile) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertNewAttachment");
		// insertNewAttachment=INSERT INTO ATTACHMENT VALUES(SEQ_FID.NEXTVAL, ?, ?, ?, ?, SYSDATE, ?, DEFAULT, DEFAULT)
		
		try {
			for(int i = 0; i < newInsertFile.size(); i++) {
				rAttachment a = newInsertFile.get(i);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, a.getbId());
				pstmt.setString(2, a.getOriginName());
				pstmt.setString(3, a.getChangeName());
				pstmt.setString(4, a.getFilePath());
				pstmt.setInt(5, a.getFileLevel());
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateAttachment(Connection conn, ArrayList<rAttachment> changeFile) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateAttachment");
		// updateAttachment=UPDATE ATTACHMENT SET ORIGIN_NAME=?, CHANGE_NAME=?, UPLOAD_DATE=SYSDATE WHERE FID=?
		
		try {
			for(int i = 0; i < changeFile.size(); i++) {
				rAttachment a = changeFile.get(i);
				
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, a.getOriginName());
				pstmt.setString(2, a.getChangeName());
				pstmt.setInt(3, a.getfId());
				
				result += pstmt.executeUpdate();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteReply(Connection conn, int rId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, rId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	

}
