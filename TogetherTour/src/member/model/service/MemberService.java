package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import member.model.dao.MemberDAO;
import member.model.vo.Member;
import member.model.vo.mAttachment;

public class MemberService {

	public Member loginMember(Member member) {
		Connection conn = getConnection();
		Member loginUser = new MemberDAO().loginMember(member,conn);

		close(conn);
		return loginUser;
	}

	public int insertMember(Member member, mAttachment userImg) {
		Connection conn = getConnection();
		int resultM = new MemberDAO().insertMember(conn, member);
		int resultA = 0;
		if(resultM > 0) {
			resultA = new MemberDAO().insertUserImg(conn, member, userImg);
			if(resultA > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		close(conn);
		
		return resultA;
	}
	public int idCheck(String userId) {
		Connection conn = getConnection();
		int result = new MemberDAO().idCheck(conn,userId);
		close(conn);
		return result;
	}

	public int nickCheck(String userNick) {
		Connection conn = getConnection();
		int result = new MemberDAO().nickCheck(conn, userNick);
		close(conn);
		return result;
	}

	public Member findId(String findName, String findEmail) {
		Connection conn = getConnection();
		Member member = new MemberDAO().findId(conn, findName, findEmail);
		close(conn);
		return member;
	}

	public Member findPwd(String userFindId, String findEmail) {
		Connection conn = getConnection();
		Member member = new MemberDAO().findPwd(conn, userFindId, findEmail);
		close(conn);
		return member;
	}

	public int fakePwd(Member member) {
		Connection conn = getConnection();
		int result = new MemberDAO().fakePwd(conn,member);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	// 관호 시작
	public int updateMember(Member member, mAttachment userImg) {
		Connection conn = getConnection();
		int resultM = new MemberDAO().updateMember(conn, member) ;
		int resultA = 0 ;
		if(resultM > 0) {
			resultA = new MemberDAO().updateUserImg(conn, member, userImg);
			if(resultA>0) commit(conn) ;
			else        rollback(conn) ;
		} else {
			rollback(conn) ;
		}
		System.out.println("[Info] [UpdateMember : MemberService] [Result Member : "+resultM+"]") ;
		System.out.println("[Info] [UpdateMember : MemberService] [Result Attachment : "+resultA+"]") ;
		close(conn) ;
		return resultA ;
	}
}
