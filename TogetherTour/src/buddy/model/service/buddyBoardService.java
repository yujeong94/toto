package buddy.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import buddy.model.dao.buddyBoardDAO;
import buddy.model.vo.buddyBoard;
import buddy.model.vo.buddyReply;
import guide.model.vo.gReply;
import review.model.vo.Reply;

public class buddyBoardService {

	// 동행자 게시글 개수
	public int getListCount() {
		Connection conn = getConnection();
		
		int result = new buddyBoardDAO().getListCount(conn);
		close(conn);
		return result;
	}

	// 동행자게시글 리스트
	public ArrayList<buddyBoard> selectList(int currentPage) {
		Connection conn = getConnection();
		ArrayList<buddyBoard> list = new buddyBoardDAO().selectList(conn, currentPage);
		close(conn);
		 
		return list;
	
	}
	

	// 동행자 게시글 등록
	public int insertBoard(String writer, buddyBoard board) {
		Connection conn = getConnection();
		buddyBoardDAO dao = new buddyBoardDAO();
		
		int result = dao.insertBoard(conn, writer, board);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	
	
	// 동행자 게시글 상세보기
	public buddyBoard selectBoard(int bnum) {
		Connection conn = getConnection();
		buddyBoardDAO dao = new buddyBoardDAO();
		
		// 조회수 증가
		int result =  dao.updateCount(conn,bnum);
		
		buddyBoard board = null;
		if(result > 0) {			
			board = dao.detailBoard(conn,bnum);
			if(board != null) {
				commit(conn);				
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		close(conn);
		
		return board;
	}
	
	public int deleteBoard(int bnum) {
		Connection conn = getConnection();
		buddyBoardDAO bDAO = new buddyBoardDAO();
		int result = bDAO.deleteBoard(conn, bnum);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	// 여기까진 일단 ㅇㅇ  아래 복붙한거임
	public int updateBoard(buddyBoard b) {
		Connection conn = getConnection();
		buddyBoardDAO bDAO = new buddyBoardDAO();
		int result = bDAO.updateBoard(conn, b);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
/*	
	public ArrayList<buddyReply> selectReplyList(Connection conn, int refGid) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<buddyReply> rList = null;
		buddyBoard r = null;
		
		String query = prop.getProperty("selectReplyList");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, refGid);
			
			rset = pstmt.executeQuery();
			rList = new ArrayList<buddyReply>();
			while(rset.next()) {
				rList.add(new gReply(
						 rset.getInt("rId"),
						 rset.getString("rContent"),
						 rset.getInt("refBid"),
						 rset.getString("rNick"),
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
	public ArrayList<buddyReply> insertReply(buddyReply r) {
		Connection conn = getConnection();
		buddyBoardDAO dao = new buddyBoardDAO();
		
		int result = dao.insertReply(conn, r);
		
		ArrayList<Reply> list = null;
		if(result > 0) {
			commit(conn);
			list = dao.selectReplyList(conn, r.getRefBid());
		} else {
			rollback(conn);
		}
		return list;
	}*/

	



/*	public ArrayList selectTList(int i) {
		// 2번 만들어야하니까 제네릭 지워서 사용
		Connection conn = getConnection();
		
		ArrayList list = null;
		
		BoardDAO dao = new BoardDAO();
		
		if(i == 1) {
			list = dao.selectBList(conn);
		} else {
			list = dao.selectFList(conn);
		}
		return list;
	}

	public int insertThumbnail(Board b, ArrayList<Attachment> fileList) {
		Connection conn = getConnection();
		
		BoardDAO dao = new BoardDAO();
		
		int result1 = dao.insertThBoard(conn, b);
		int result2 = dao.insertAttachment(conn, fileList);
		
		if(result1 > 0 && result2 > 0 ) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result1;
	}

	public ArrayList<Attachment> selectThumbnail(int bId) {
		Connection conn = getConnection();
		ArrayList<Attachment> list = new BoardDAO().selectThumbnail(conn, bId);
		close(conn);
		
		
		return list;
	}

	*/
	
	public int getSearchCount(String menu, String content) {
		Connection conn = getConnection();
		
		int result = new buddyBoardDAO().getSearchCount(conn, menu, content);
		close(conn);
		return result;
	}
	
	public ArrayList<buddyBoard> searchList(int currentPage, String menu, String content) {
		Connection conn = getConnection();
		ArrayList<buddyBoard> list = new buddyBoardDAO().searchList(conn, currentPage, menu, content);
		close(conn);
		return list;
	}
	
}
