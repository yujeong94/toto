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
