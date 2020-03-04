package buddy_me.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import buddy_me.model.dao.MyPageDAO;
import buddy_me.model.vo.Buddy;
import buddy_me.model.vo.Report;

public class MyPageService {

	public ArrayList<Buddy> mybuddyList(String userId) {
		Connection conn = getConnection();
		// 게시글 번호 가져오기 
		ArrayList<Integer> bnumList = new MyPageDAO().selectBnum(conn,userId);
		
		// 게시글 번호로 동행자 목록 가져오기
		ArrayList<Buddy> mybuddyList = null; 
		if(bnumList != null) {
			mybuddyList = new MyPageDAO().mybuddyList(conn,bnumList,userId);
			if(mybuddyList != null) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		close(conn);
		
		return mybuddyList;
	}

	public int updateGrade(String gradeNick, int grade) {
		Connection conn = getConnection();
		MyPageDAO dao = new MyPageDAO();
		int cResult = dao.updateGcount(conn, gradeNick);
		int result = 0;
		if(cResult > 0) {
			
			result = dao.updateGrade(conn, gradeNick, grade);
			
			if(result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public int resetGrade(String gradeNick, int grade) {
		Connection conn = getConnection();
		int result = new MyPageDAO().resetGrade(conn, gradeNick, grade);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int insertReport(Report r) {
		Connection conn = getConnection();
		int result = new MyPageDAO().insertReport(conn, r);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


}
