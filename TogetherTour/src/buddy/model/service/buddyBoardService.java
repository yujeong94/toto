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

public class buddyBoardService {

	public int getListCount() {
		Connection conn = getConnection();
		
		int result = new buddyBoardDAO().getListCount(conn);
		close(conn);
		return result;
	}

	public ArrayList<buddyBoard> selectList(int currentPage) {
		Connection conn = getConnection();
		ArrayList<buddyBoard> list = new buddyBoardDAO().selectList(conn, currentPage);
		close(conn);
		 
		return list;
	
	}
	
/*	public ArrayList selectTList(int i) {
		// 2번 만들어야하니까 제네릭 지워서 사용
		Connection conn = getConnection();
		
		ArrayList list = null;
		
		buddyBoardDAO dao = new buddyBoardDAO();
		
		if(i == 1) {
			list = dao.selectBList(conn);
		} else {
			list = dao.selectFList(conn);
		}
		return list;
	}*/
	
	public buddyBoard selectBoard(int bnum) {
		Connection conn = getConnection();
		
		buddyBoardDAO dao = new buddyBoardDAO();		
				
		buddyBoard b = null;
		b = dao.selectBoard(conn, bnum);
		
		if(b != null) commit(conn) ;
		else		  rollback(conn) ;
		
//		int result = dao.updateCount(conn, bnum);
//		
//		buddyBoard b = null;
//		if(result > 0) {
//			b = dao.selectBoard(conn, bnum);
//			
//			if(b != null) {
//				commit(conn);
//			} else {
//				rollback(conn);
//			}
//		} else {
//			rollback(conn);
//		}
		
		close(conn);
		
		return b;
	}

	public int insertBoard(buddyBoard board, int category) {
		Connection conn = getConnection();
		buddyBoardDAO dao = new buddyBoardDAO();
		
		int result = dao.insertBoard(conn, board, category);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
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

	public int insertBoard(buddyBoard board) {
		Connection conn = getConnection();
		buddyBoardDAO dao = new buddyBoardDAO();
		
		int result = dao.insertBoard(conn, board);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

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

	public ArrayList<Reply> selectReplyList(int bId) {
		Connection conn = getConnection();
		ArrayList<Reply> list = new BoardDAO().selectReplyList(conn, bId);
		
		return list;
	}

	public ArrayList<Reply> insertReply(Reply r) {
		Connection conn = getConnection();
		BoardDAO dao = new BoardDAO();
		
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
	
}
