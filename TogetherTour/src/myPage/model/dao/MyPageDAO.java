package myPage.model.dao;

import static common.JDBCTemplate.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.vo.Member;
import member.model.vo.mAttachment;

public class MyPageDAO {
	private Properties prop = new Properties();
	
	public MyPageDAO() {
		String fileName = MyPageDAO.class.getResource("/sql/mypage/mypage-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public mAttachment profileImg(Connection conn, String userNick) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		mAttachment profileImg = null;
		
		String query = prop.getProperty("profileImg");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,userNick);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				profileImg = new mAttachment(rset.getInt("fid"),
											 rset.getString("mid"),
											 rset.getString("origin_name"),
											 rset.getString("change_name"),
											 rset.getString("file_path"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return profileImg;
	}
	public Member userProfile(Connection conn, String userNick) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member userProfile = null;
		
		String query = prop.getProperty("userProfile");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNick);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				userProfile = new Member(rset.getString("nick"),
							rset.getString("name"),
							rset.getString("gender"),
							rset.getInt("grade"),
							rset.getString("age"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return userProfile;
	}

}
