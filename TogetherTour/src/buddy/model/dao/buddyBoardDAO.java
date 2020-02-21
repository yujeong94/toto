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

import com.sun.xml.internal.ws.api.message.Attachment;

import buddy.model.vo.buddyBoard;

public class buddyBoardDAO {
	private Properties prop = new Properties();
	
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
					buddyBoard b = new buddyBoard(
												rset.getInt("bnum"),
												rset.getString("title"),
												rset.getDate("create_date"),
												rset.getString("nick"),
												rset.getString("country"),
												rset.getString("city"),
												rset.getDate("start_date"),   	// 생성자에 필요한것만 추가해놓기
												rset.getInt("bcount"),
												rset.getInt("head_cnt"),
												rset.getString("content"));
					list.add(b);
					System.out.println(list);
					
					
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}
	
	
		//
	
	public int updateCount(Connection conn, int bId) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
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
										rset.getDate("start_date"),
										rset.getString("nick"),
										rset.getString("country"),
										rset.getString("city"),
										rset.getDate("start_date"),   	// 생성자에 필요한것만 추가해놓기
										rset.getInt("bcount"),
										rset.getInt("head_cnt"),
										rset.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return board;
	}
	
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
	
	public int insertBoard(Connection conn, buddyBoard board, int category) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
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
	
	public ArrayList selectBList(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<buddyBoard> list = null;
		
		String query = prop.getProperty("selectBList");
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<buddyBoard>();
			
			while(rset.next()) {
				list.add(new buddyBoard(
									rset.getInt("bnum"),
									rset.getString("title"),
									rset.getDate("start"),
									rset.getString("nick"),
									rset.getString("country"),
									rset.getString("city"),
									rset.getDate("start_date"),   	// 생성자에 필요한것만 추가해놓기
									rset.getInt("bcount"),
									rset.getInt("head_cnt"),
									rset.getString("content")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
				
		return list;
	}
		

	public int insertBoard(Connection conn, buddyBoard board) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
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

	
/*	
	public ArrayList selectFList(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Attachment> list = null;
		
		String query = prop.getProperty("selectFList");
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
			list = new ArrayList<Attachment>();
			
			while(rs.next()) {
				list.add(new Attachment(rs.getInt("bnum"),
										rs.getString("change_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
		}
		
		return list;
	}*/
	/*
	public int insertThBoard(Connection conn, Board b) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertThBoard");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getbTitle());
			pstmt.setString(2, b.getbContent());
			pstmt.setString(3, b.getbWriter());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	
		return result;
	}
	
	public int insertAttachment(Connection conn, ArrayList<Attachment> fileList) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAttachment");
		
		try {
			for(int i = 0; i < fileList.size(); i++) {
				Attachment a = fileList.get(i);
			
				pstmt= conn.prepareStatement(query);
				pstmt.setString(1, a.getOriginName());
				pstmt.setString(2, a.getChangeName());
				pstmt.setString(3, a.getFilePath());
				pstmt.setInt(4, a.getFileLevel());
				
				result += pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<Attachment> selectThumbnail(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Attachment> list = null;
		
		String query = prop.getProperty("selectThumbnail");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<Attachment>();
			
			while(rs.next()) {
				Attachment at = new Attachment();
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
	
	public ArrayList<Reply> selectReplyList(Connection conn, int bId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Reply> list = null;
		
		String query = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<Reply>();
			
			while(rs.next()) {				
				list.add( new Reply(rs.getInt("RID"),
									rs.getString("RCONTENT"),
									rs.getInt("REF_BID"),
									rs.getString("NICKNAME"),
									rs.getDate("CREATE_DATE"),
									rs.getDate("MODIFY_DATE"),
									rs.getString("STATUS")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int insertReply(Connection conn, Reply r) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReply");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, r.getrContent());
			pstmt.setInt(2, r.getRefBid());
			pstmt.setString(3, r.getrWriter());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
*/
		
}

