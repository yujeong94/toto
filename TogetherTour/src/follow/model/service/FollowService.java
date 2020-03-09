package follow.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import follow.model.dao.FollowDAO;
import follow.model.vo.Follow;
import member.model.vo.Member;
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

	public ArrayList<Member> selectFollowList(String mId) {
		Connection conn = getConnection() ;
		ArrayList<Member> mList = new FollowDAO().selectFollowList(conn,mId) ;
		close(conn) ;
		return mList ;
	}
	
}
