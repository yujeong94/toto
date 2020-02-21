package guide.model.service;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;

import guide.model.dao.GuideDAO;
import guide.model.vo.Gboard;
import guide.model.vo.gReply;

public class GuideService {

	public int getListCount() {
		// 가이드게시글 목록 개수
		Connection conn = getConnection();
		int result = new GuideDAO().getListCount(conn);
		close(conn);
		return result;
	}

	public ArrayList<Gboard> selectList(int currentPage) {
		// 가이드게시글 리스트
		Connection conn = getConnection();
		ArrayList<Gboard> list = new GuideDAO().selectList(conn, currentPage);
		close(conn);
		return list;
	}

	public int insertGuide(Gboard gboard) {
		// 가이드게시글 등록
		Connection conn = getConnection();
		int result = new GuideDAO().insertGuide(conn,gboard);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public Gboard selectGboard(int gNum) {
		// 가이드게시글 상세보기 
		Connection conn = getConnection();
		GuideDAO dao = new GuideDAO();
		
		// 조회수 증가
		int result =  dao.updateCount(conn,gNum);
		
		Gboard gboard = null;
		if(result > 0) {			
			gboard = dao.detailGuide(conn,gNum);
			if(gboard != null) {
				commit(conn);				
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		close(conn);
		
		return gboard;
	}

	public int deleteGuide(int gbNum) {
		Connection conn = getConnection();
		int result = new GuideDAO().deleteGuide(conn, gbNum);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public ArrayList<gReply> insertReply(gReply reply) {
		Connection conn = null;
		GuideDAO dao = new GuideDAO();
		int result = dao.insertReply(conn, reply);
		
		ArrayList<gReply> rList = null;
		if(result > 0) {
			commit(conn);
			rList = dao.selectReplyList(conn, reply.getRefGid());
		} else {
			rollback(conn);
		}
		close(conn);
		return rList;
	}

	public ArrayList<gReply> selectReplyList(int gNum) {
		Connection conn = getConnection();
		ArrayList<gReply> rList = new GuideDAO().selectReplyList(conn, gNum);
		
		return rList;
	}

	public int updateGuide(Gboard gb) {
		Connection conn = getConnection();
		int result = new GuideDAO().updateGuide(conn, gb);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}

}
