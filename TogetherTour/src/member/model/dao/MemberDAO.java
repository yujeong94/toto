package member.model.dao;

import static common.JDBCTemplate.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import member.model.vo.Member;
import member.model.vo.mAttachment;

public class MemberDAO {
	
	private Properties prop = new Properties();
	
	public MemberDAO() {
		String fileName = MemberDAO.class.getResource("/sql/member/member-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Member loginMember(Member member, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member loginUser = null;
		
		String query = prop.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getmId());
			pstmt.setString(2, member.getPwd());
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				loginUser = new Member(rset.getString("mid"),
										rset.getString("nick"),
										rset.getString("pwd"),
										rset.getString("name"),
										rset.getString("gender"),
										rset.getString("email"),
										rset.getInt("mkind"),
										rset.getInt("follow"),
										rset.getInt("grade"),
										rset.getString("age"),
										rset.getInt("gcount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return loginUser;
	}

	public int insertMember(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getmId());
			pstmt.setString(2, member.getNickName());
			pstmt.setString(3, member.getPwd());
			pstmt.setString(4, member.getUserName());
			pstmt.setString(5, member.getGender());
			pstmt.setString(6, member.getEmail());
			pstmt.setInt(7, member.getmKind());
			pstmt.setString(8, member.getAge());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int idCheck(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result  = 0;
		
		String query = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}
	public int nickCheck(Connection conn, String userNick) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String query = prop.getProperty("nickCheck");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNick);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return result;
	}
	public int insertUserImg(Connection conn, Member member, mAttachment userImg) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertUserImg");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getmId());
			pstmt.setString(2, userImg.getOriginName());
			pstmt.setString(3, userImg.getChangeName());
			pstmt.setString(4, userImg.getFilePath());
			pstmt.setInt(5, 0);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Member findId(Connection conn, String findName, String findEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		String query = prop.getProperty("findId");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, findName);
			pstmt.setString(2, findEmail);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member(rset.getString("mid"),
									rset.getString("name"),
									rset.getString("email"),
									rset.getInt("mkind"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	public Member findPwd(Connection conn, String userFindId, String findEmail) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		
		String query = prop.getProperty("findPwd");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userFindId);
			pstmt.setString(2, findEmail);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				member = new Member(rset.getString("mid"),
									rset.getString("pwd"),
									rset.getString("name"),
									rset.getString("email"),
									rset.getInt("mkind"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return member;
	}

	public int fakePwd(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("fakePwd");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getPwd());
			pstmt.setString(2, member.getmId());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	//관호 시작
	public int updateMember(Connection conn, Member member) {
		PreparedStatement pstmt = null ;
		int result = 0 ;
		
		String query = prop.getProperty("updateMember") ;
		
		try {
			pstmt = conn.prepareStatement(query) ;
			pstmt.setString(1, member.getNickName()) ;
			pstmt.setString(2, member.getUserName()) ;
			pstmt.setString(3, member.getGender()) ;
			pstmt.setString(4, member.getEmail()) ;
			pstmt.setInt(5, member.getmKind()) ;
			pstmt.setString(6, member.getAge()) ;
			pstmt.setString(7, member.getmId()) ;
			result = pstmt.executeUpdate() ;
		} catch (SQLException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} finally {
			close(pstmt) ;
		} return result ;
	}

	public int updateUserImg(Connection conn, Member member, mAttachment userImg) {
		PreparedStatement pstmt = null ;
		int result = 0 ;
		
		String query = prop.getProperty("updateUserImg");
		
		try {
			pstmt = conn.prepareStatement(query) ;
			pstmt.setString(1, userImg.getOriginName()) ;
			pstmt.setString(2, userImg.getChangeName()) ;
			pstmt.setString(3, userImg.getFilePath()) ;
			pstmt.setString(4, member.getmId()) ;
			result = pstmt.executeUpdate() ;
			System.out.println("[Info] [DAO : updateUserImg] [OriginName : '"+userImg.getOriginName()+"']") ;
			System.out.println("[Info] [DAO : updateUserImg] [ChangeName : '"+userImg.getChangeName()+"']") ;
			System.out.println("[Info] [DAO : updateUserImg] [FilePath : '"+userImg.getFilePath()+"']") ;
		} catch (SQLException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} finally {
			close(pstmt) ;
		} return result ;
	}
}
