package follow.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import follow.model.dao.FollowDAO;
import follow.model.vo.Follow;
import review.model.dao.ReviewDAO;

public class FollowService {

	public int insertFollow(Follow follow) {
		
		Connection conn = getConnection();
		int result = new FollowDAO().insertFollow(conn,follow);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
}
