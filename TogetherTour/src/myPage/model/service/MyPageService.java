package myPage.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import member.model.vo.Member;
import member.model.vo.mAttachment;
import myPage.model.dao.MyPageDAO;

public class MyPageService {

	public mAttachment profileImg(String userNick) {
		Connection conn = getConnection();
		mAttachment profileImg = new MyPageDAO().profileImg(conn,userNick);
		close(conn);
		return profileImg;
	}

	public Member userProfile(String userNick) {
		Connection conn = getConnection();
		Member userProfile = new MyPageDAO().userProfile(conn,userNick);
		close(conn);
		return userProfile;
	}
}
