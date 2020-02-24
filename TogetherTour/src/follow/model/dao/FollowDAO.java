package follow.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static common.JDBCTemplate.close;

import follow.model.vo.Follow;
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

}
