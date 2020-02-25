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
import guide.model.vo.gReply;
import review.model.vo.Review;

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
									rset.getInt("gcount"),
									rset.getInt("kind")
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
	
	// 가이드등록
	public int insertGuide(Connection conn, Gboard gboard) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertGuide");
		 
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gboard.getgTitle());
			pstmt.setString(2, gboard.getgWriter());
			pstmt.setInt(3, gboard.getPrice());
			pstmt.setString(4, gboard.getgContent());
			pstmt.setString(5, gboard.getCountry());
			pstmt.setString(6, gboard.getCity());
			pstmt.setDate(7, gboard.getStartDate());
			pstmt.setDate(8, gboard.getEndDate());
			pstmt.setInt(9, gboard.getKind());
			pstmt.setString(10, gboard.getKakao());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateCount(Connection conn, int gNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, gNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Gboard detailGuide(Connection conn, int gNum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Gboard gboard = null;
		
		String query = prop.getProperty("detailGuide");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,gNum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				gboard = new Gboard(rset.getInt("gbnum"), 
						rset.getString("title"),
						rset.getString("nick"),
						rset.getInt("price"),
						rset.getString("content"),
						rset.getDate("create_date"),
						rset.getString("country"),
						rset.getString("city"),
						rset.getDate("start_date"),
						rset.getDate("end_date"),
						rset.getInt("gcount"),
						rset.getInt("kind"),
						rset.getString("kakao"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return gboard;
	}

	public int deleteGuide(Connection conn, int gbNum) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteGuide");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, gbNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertReply(Connection conn, gReply reply) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getGrContent());
			pstmt.setInt(2, reply.getRefGid());
			pstmt.setString(3, reply.getWriter());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public ArrayList<gReply> selectReplyList(Connection conn, int refGid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<gReply> rList = null;
		gReply r = null;
		
		String query = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, refGid);
			
			rset = pstmt.executeQuery();
			rList = new ArrayList<gReply>();
			while(rset.next()) {
				rList.add(new gReply(rset.getInt("grid"),
						 rset.getString("grcontent"),
						 rset.getInt("ref_gid"),
						 rset.getString("nick"),
						 rset.getDate("create_date"),
						 rset.getString("status")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}

	public int updateGuide(Connection conn, Gboard gb) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateGuide");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, gb.getgTitle());
			pstmt.setInt(2, gb.getKind());
			pstmt.setString(3, gb.getCountry());
			pstmt.setString(4, gb.getCity());
			pstmt.setDate(5, gb.getStartDate());
			pstmt.setDate(6, gb.getEndDate());
			pstmt.setInt(7, gb.getPrice());
			pstmt.setString(8, gb.getgContent());
			pstmt.setString(9, gb.getKakao());
			pstmt.setInt(10, gb.getGbNum());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
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

	public ArrayList<Gboard> searchList(Connection conn, int currentPage, String menu, String content) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Gboard> list = null;
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
			if(menu.equals("LOCATION")) {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, '%'+content+'%');
				pstmt.setString(2, '%'+content+'%');
				pstmt.setInt(3, startRow);
				pstmt.setInt(4, endRow);				
			} else {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, '%'+content+'%');
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
			}
			
			
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
						rset.getInt("gcount"),
						rset.getInt("kind")
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
