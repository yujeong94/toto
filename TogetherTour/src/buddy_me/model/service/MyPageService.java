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

	public int selectCount(int bnum) {
		Connection conn = getConnection();
		int headC = new MyPageDAO().selectCount(conn,bnum);
		
		close(conn);
		
		return headC;
	}

	public int insertBuddy(int bnum, int ageNum, ArrayList<String> infoArr) {
		Connection conn = getConnection();
		MyPageDAO dao = new MyPageDAO();
		
		// 동행자 조건 확인
		int okay = dao.checkInfo(conn,ageNum,infoArr);
		
		String userId = infoArr.get(0);
		String buddyId = infoArr.get(2);
		int result = 0;
		
		if(okay > 0) { //조건 맞음 
			Buddy b = new Buddy();
			// 작성자 들어갔는지 조회
			int check = dao.selectWriter(conn,bnum,userId);
			
			// 작성자 존재여부에 따라 작성자도 넣거나, 동행자만 넣거나
			if(check > 0) {
				//동행자만
				b.setbNum(bnum);
				b.setmId(buddyId);
				result = dao.insertBuddy(conn,b);
				commit(conn);
			} else {
				//작성자도 같이
				b.setbNum(bnum);
				b.setmId(buddyId);
				b.setWriter_mid(userId);
				result = dao.insertAll(conn,b);
				commit(conn);
			}
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
}
