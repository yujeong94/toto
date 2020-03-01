package review.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import follow.model.vo.Follow;
import review.model.dao.ReviewDAO;
import review.model.vo.Reply;
import review.model.vo.Review;
import review.model.vo.rAttachment;

public class ReviewService {
	
	public int getListCount() {
		Connection conn = getConnection();
		
		int result = new ReviewDAO().getListCount(conn);
		close(conn);
		return result;
	}

	public ArrayList<Review> selectList(int currentPage) {
		Connection conn = getConnection();
		ArrayList<Review> list = new ReviewDAO().selectList(conn, currentPage);
		close(conn);
		return list;
	}

	public Review selectReview(int rNum) {
		
		Connection conn = getConnection();
		ReviewDAO dao = new ReviewDAO();
		
		int result = dao.updateCount(conn, rNum);
		
		Review review = null;
		if(result > 0) {
			review = dao.selectReview(conn, rNum);
			if(review != null) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		
		return review;
		
	}

	public int insertReview(Review r, ArrayList<rAttachment> fileList) {
		Connection conn = getConnection();
		int result = new ReviewDAO().insertReview(conn,r);
		
		int result2 = new ReviewDAO().insertrAttachment(conn,fileList);
		
		if(result > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

	public int deleteBoard(int rNum) {
		Connection conn = getConnection();
		ReviewDAO dao = new ReviewDAO();
		
		int result = dao.deleteReview(conn,rNum);
		
		if(result> 0) {
			commit(conn);
		} else {
			rollback(conn);
		} 
		return result;
	}

	public int updateReview(Review review) {
		Connection conn = getConnection();
		ReviewDAO dao = new ReviewDAO();
		
		int result = dao.updateReview(conn, review);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
		
	}

	

	public ArrayList<Reply> selectReplyList(int rNum) {
		Connection conn = getConnection();
		ArrayList<Reply> list = new ReviewDAO().selectReplyList(conn, rNum);
		
		return list;
	}
	
	public ArrayList<Reply> insertReply(Reply r) {
		Connection conn = getConnection();
		ReviewDAO dao = new ReviewDAO();
		int result = dao.insertReply(conn,r);
		
		ArrayList<Reply> list = null;
		
		if(result > 0) {
			commit(conn);
			list=dao.selectReplyList(conn,r.getRefrNum());
		} else {
			rollback(conn);
		}
		
		return list;
	}

	public int updateRpoint(Review review) {
		
		Connection conn = getConnection();
		ReviewDAO dao = new ReviewDAO();
		
		int result = dao.updateRpoint(conn, review);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

	public ArrayList<Review> searchList(int currentPage, String menu, String content) {
		Connection conn = getConnection();
		ArrayList<Review> list = new ReviewDAO().searchList(conn, currentPage, menu, content);
		close(conn);
		return list;
	}

	public int getSearchCount(String menu, String content) {
		Connection conn = getConnection();
		
		int result = new ReviewDAO().getSearchCount(conn, menu, content);
		close(conn);
		return result;
	}

	public ArrayList<Follow> selectFollowList() {
		Connection conn = getConnection();
		ArrayList<Follow> flist = new ReviewDAO().selectFollowList(conn);
		close(conn);
		return flist;

	}

	public ArrayList<rAttachment> selectThumbnail(int rNum) {
		Connection conn = getConnection();
		ArrayList<rAttachment> list = new ReviewDAO().selectThumbnail(conn, rNum);
		
		return list;
	}

	public int updateReview(Review review, ArrayList<rAttachment> file) {
		Connection conn = getConnection();
		
		ReviewDAO dao = new ReviewDAO();
		
		int result1 = dao.updateReview(conn, review);
		int result2 = 0;
		
		if(file.get(0).getfId() == 0) {
			result2 = dao.insertNewAttachment(conn, file);
		} else {
			result2 = dao.updateAttachment(conn, file);
		}
		
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result1;
	}

	public int updateReview(Review review, ArrayList<rAttachment> changeFile, ArrayList<rAttachment> newInsertFile) {
		Connection conn = getConnection();
		
		ReviewDAO dao = new ReviewDAO();
		
		int result1 = dao.updateReview(conn, review);
		int result2 = dao.updateAttachment(conn, changeFile);
		int result3 = dao.insertNewAttachment(conn, newInsertFile);
		
		if(result1 > 0 && result2 > 0 && result3 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result1;
	}

	public int deleteReply(int rId) {
		Connection conn = getConnection();
		ReviewDAO dao = new ReviewDAO();
		
		int result = dao.deleteReply(conn,rId);
		
		if(result> 0) {
			commit(conn);
		} else {
			rollback(conn);
		} 
		return result;
	}
	
	
}
