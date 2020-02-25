package qna.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import qna.model.dao.QnaDAO;
import qna.model.vo.Qna;
import qna.model.vo.Reply;

public class QnaService {

	public int getListCount() {
		Connection conn = getConnection();
		
		int result = new QnaDAO().getListCount(conn);
		close(conn);
		return result;
	}

	public ArrayList<Qna> selectList(int currentPage) {
		Connection conn = getConnection();
		ArrayList<Qna> list = new QnaDAO().selectList(conn, currentPage);
		close(conn);
		return list;
	}

	public Qna selectQna(int qNum) {
		Connection conn = getConnection();
		QnaDAO dao = new QnaDAO();
		
		int result = dao.updateCount(conn, qNum);
		
		Qna qna = null;
		if(result > 0) {
			qna = dao.selectQna(conn, qNum);
			if(qna != null) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		
		return qna;
	}

	public int updateQna(Qna qna) {
		Connection conn = getConnection();
		QnaDAO dao = new QnaDAO();
		
		int result = dao.updateQna(conn, qna);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public int deleteQna(int qNum) {
		Connection conn = getConnection();
		QnaDAO dao = new QnaDAO();
		
		int result = dao.deleteQna(conn,qNum);
		
		if(result> 0) {
			commit(conn);
		} else {
			rollback(conn);
		} 
		return result;
	}

	public int insertQna(Qna q) {
		Connection conn = getConnection();
		int result = new QnaDAO().insertQna(conn,q);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public ArrayList<Reply> insertReply(Reply r) {
		Connection conn = getConnection();
		QnaDAO dao = new QnaDAO();
		int result = dao.insertReply(conn,r);
		
		ArrayList<Reply> list = null;
		
		if(result > 0) {
			commit(conn);
			list=dao.selectReplyList(conn,r.getRefrNum());
		} else {
			rollback(conn);
		}
		
		return list;
	}

	public ArrayList<Reply> selectReplyList(int qNum) {
		Connection conn = getConnection();
		ArrayList<Reply> list = new QnaDAO().selectReplyList(conn, qNum);
		
		return list;
	}

}
