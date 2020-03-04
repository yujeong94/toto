package buddy.model.dao;

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

import buddy.model.vo.buddyBoard;
import buddy.model.vo.buddyReply;
import guide.model.vo.gReply;

public class buddyBoardDAO {
	//
	private Properties prop = new Properties();
	
	//
	public buddyBoardDAO() {
		String fileName = buddyBoardDAO.class.getResource("/sql/buddyBoard/buddyboard_query.properties").getPath();
			try {
				prop.load(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}	 
	
	//
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

	//
	public ArrayList<buddyBoard> selectList(Connection conn, int currentPage) {
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ArrayList<buddyBoard> list = null;
			int posts = 10;		// 한 페이지에 보여질 게시글 개수 
			
			int startRow = (currentPage - 1) * posts + 1;
			int endRow = startRow + posts - 1;
			
			String query = prop.getProperty("selectList");
			
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
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
				System.out.println("dao 확인 : " + list);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return list;
		}
	
	
	// 동행자 등록
	public int insertBoard(Connection conn, String writer, buddyBoard board) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, writer);
			pstmt.setString(4, board.getCountry());
			pstmt.setString(5, board.getCity());
			pstmt.setDate(6, board.getStart_date());
			pstmt.setDate(7, board.getEnd_date());
			pstmt.setString(8,board.getTheme());
			pstmt.setInt(9, board.getGroup_age());
			pstmt.setString(10, board.getKakao());
			pstmt.setInt(11,  board.getKind());	
			pstmt.setInt(12,  board.getHead_cnt());
			pstmt.setString(13,  board.getGender());
			
			System.out.println(board.getTitle()+" : dao");
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
				
		return result;
	}
	
	// 
	public int updateCount(Connection conn, int bnum) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bnum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	//
	public buddyBoard detailBoard(Connection conn, int bnum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		buddyBoard board = null;
		
		String query = prop.getProperty("detailBoard");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,bnum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board = new buddyBoard(
									   bnum,
									   rset.getString("title"),
									   rset.getString("part_yn"),
									   rset.getString("content"),
									   rset.getDate("create_date"),
									   rset.getString("nick"),
									   rset.getString("country"),
									   rset.getString("city"),
									   rset.getDate("start_date"),
									   rset.getDate("end_date"),
									   rset.getString("status"),
									   rset.getInt("bcount"),
									   rset.getString("theme"),
									   rset.getInt("group_age"),
									   rset.getString("kakao"),
									   rset.getInt("kind"),
									   rset.getInt("head_cnt"),
									   rset.getString("gender"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return board;
	}
	
	public buddyBoard selectBoard(Connection conn, int bnum) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		buddyBoard board = null;
		
		String query = prop.getProperty("selectBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bnum);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board = new buddyBoard(
										rset.getInt("bnum"),
										rset.getString("title"),
										rset.getString("country"),
										rset.getString("city"),
										rset.getDate("start_date"),
										rset.getInt("head_cnt"),
										rset.getString("nick"),
										rset.getInt("bcount"),
										rset.getDate("create_date"));
										
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return board;
	}
	
	// 수정
	public int updateBoard(Connection conn, buddyBoard board) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, board.getTitle());
			pstmt.setDate(2, board.getStart_date());
			pstmt.setString(3, board.getCountry());
			pstmt.setString(4, board.getTheme());
			pstmt.setInt(5, board.getHead_cnt());
			pstmt.setInt(6, board.getGroup_age());
			pstmt.setString(7, board.getContent());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	//
	public int insertReply(Connection conn, buddyReply reply) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, reply.getrContent());
			pstmt.setInt(2, reply.getRefBid());
			pstmt.setString(3, reply.getrNick());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//
	public ArrayList<buddyReply> selectReplyList(Connection conn, int refGid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<buddyReply> rList = null;
		buddyReply buddy = null;
		
		String query = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, refGid);
			
			rset = pstmt.executeQuery();
			rList = new ArrayList<buddyReply>();
			while(rset.next()) {
				rList.add(new buddyReply(rset.getInt("grid"),
						 rset.getString("grcontent"),
						 rset.getInt("ref_gid"),
						 rset.getString("nick"),
						 rset.getDate("createDate"),
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
	
	
	
	//
	public int deleteBoard(Connection conn, int bnum) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bnum);
			
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
	
	public ArrayList<buddyBoard> searchList(Connection conn, int currentPage, String menu, String content) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<buddyBoard> list = null;
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
				pstmt.setString(1,  '%' + content +'%');
				pstmt.setInt(2,  startRow);
				pstmt.setInt(3, endRow);
			}
			
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
									rset.getDate("create_date")));		
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

