package member.model.service;

import static common.JDBCTemplate.*;
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

}
