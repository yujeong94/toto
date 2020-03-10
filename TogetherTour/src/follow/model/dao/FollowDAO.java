package follow.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static common.JDBCTemplate.close;

import follow.model.vo.Follow;
import member.model.vo.Member;
import review.model.dao.ReviewDAO;

public class FollowDAO {
	
	private Properties prop = new Properties();
	
	public FollowDAO() {
		String fileName = ReviewDAO.class.getResource("/sql/follow/follow-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public int insertFollow(Connection conn, Follow follow) {
		PreparedStatement pstmt = null;
		int result = 0;

	
		
		String query = prop.getProperty("insertFollow");
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1, follow.getmId());
			pstmt.setString(2, follow.getfId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Member> selectFollowList(Connection conn, String mId) {
		PreparedStatement ptmt = null ;
		ResultSet rset = null ;
		ArrayList<Member> mList = new ArrayList<Member>() ; ;
		Member member = null ;
		int grade = 0 ;
		
		String query = prop.getProperty("selectFollowList") ;
		try {
			ptmt = conn.prepareStatement(query) ;
			ptmt.setString(1, mId) ;
			rset = ptmt.executeQuery() ;
			while(rset.next()) {
				if(rset.getInt("GCOUNT") == 0)	grade = 0 ;
				else							grade = Math.round(rset.getInt("grade")/rset.getInt("gCount")) ;
				member = new Member() ;
				member.setNickName(rset.getString("FNICK")) ;
				member.setEmail(rset.getString("EMAIL")) ;
				member.setGender(rset.getString("GENDER")) ;
				member.setAge(rset.getString("AGE")) ;
				member.setFollow(rset.getInt("FOLLOW")) ;
				member.setGrade(grade) ;
				member.setgCount(rset.getInt("GCOUNT")) ;
				mList.add(member) ;
			}
		} catch (SQLException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} finally {
			close(rset) ;
			close(ptmt) ;
		} return mList ;
	}
}
