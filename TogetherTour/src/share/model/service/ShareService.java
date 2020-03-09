package share.model.service ; 

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection	;
import java.util.ArrayList ;

import share.model.dao.ShareDAO ;
import share.model.vo.Sattachment ;
import share.model.vo.Share ;
import share.model.vo.sReply;

public class ShareService {
	public int insertShare(Share share, ArrayList<Sattachment> fileList) {
		Connection	conn = getConnection() ;
		ShareDAO	sDAO = new ShareDAO()  ;
		int result1 = sDAO.insertShare(conn, share) ;
		int result2 = sDAO.insertSattachment(conn, fileList, share) ;
		if(result1>0 && result2>0)	commit(conn) ;
		else						rollback(conn) ;
		return result1 ;
	}

	public int insertShare(Share share) {
		Connection	conn = getConnection() ;
		ShareDAO	sDAO = new ShareDAO()  ;
		int result = sDAO.insertShare(conn, share) ;
		if(result>0) commit(conn) ;
		else		 rollback(conn) ;
		return result ;
	}

	public ArrayList selectShareList(int i) {
		Connection	conn = getConnection() ;
		ArrayList  list = null ;
		ShareDAO	sDAO = new ShareDAO() ;
		if(i==1) list = sDAO.selectSList(conn) ;
		else	 list = sDAO.selectFList(conn) ;
		close(conn) ;
		return list ;
	}

	public Share selectShare(int sbNum) {
		Connection	conn = getConnection() ;
		ShareDAO dao = new ShareDAO() ;
		int result = dao.updateCount(conn, sbNum) ;
		
		Share share = null ;
		if(result > 0) {
			share = dao.selectShare(conn, sbNum);
			if(share!=null) commit(conn)   ;
			else			rollback(conn) ;
		} else {
			rollback(conn);
		} return share ;
	}

	public ArrayList<Sattachment> selectPicture(int sbNum) {
		Connection	conn = getConnection() ;
		ArrayList<Sattachment> list = new ShareDAO().selectPicture(conn, sbNum) ;
		return list ;
	}

	public int deleteShare(int sbNum) {
		Connection	conn = getConnection() ;
		int result = new ShareDAO().deleteShare(conn, sbNum) ;
		
		if(result>0) commit(conn) ;
		else		 rollback(conn) ;
		
		return result ;
	}

	public ArrayList<sReply> selectReplyList(int sbNum) {
		Connection	conn = getConnection() ;
		ShareDAO	sDAO = new ShareDAO()  ;
		ArrayList<sReply> rList = sDAO.selectReplyList(conn, sbNum) ;
		return rList ;
	}

	public ArrayList<sReply> insertReply(sReply reply) {
		Connection	conn = getConnection() ;
		ShareDAO	sDAO = new ShareDAO()  ;
		int result = sDAO.insertReply(conn, reply) ;
		
		ArrayList<sReply> rList = null ;
		if(result>0) {
			commit(conn) ;
			rList = sDAO.selectReplyList(conn, reply.getSbNum()) ;
		} else {
			rollback(conn) ;
		} return rList ;
	}

	public int updateShare(Share share, ArrayList<Sattachment> fileList) {
		Connection conn = getConnection() ;
		ShareDAO sDAO = new ShareDAO() ;
		int result1 = sDAO.updateSattachment(conn, fileList, share) ;
		System.out.println("result1 : "+result1) ;
		int result2 = sDAO.updateShare(conn, share) ;
		if(result1>0 && result2>0)	commit(conn) ;
		else						rollback(conn) ;
		return result2 ;
	}

	public int updateShare(Share share) {
		Connection conn = getConnection() ;
		ShareDAO sDAO = new ShareDAO() ;
		int result = sDAO.updateShare(conn, share) ;
		if(result>0) commit(conn) ;
		else		 rollback(conn) ;
		close(conn) ;
		return result ;
	}

	public int updateOldSatt(int sbNum) {
		Connection conn = getConnection() ;
		ShareDAO sDAO = new ShareDAO() ;
		int result = sDAO.updateOldSatt(conn, sbNum) ;
		if(result>0) commit(conn) ;
		else		 rollback(conn) ;
		close(conn) ;
		return result ;
	}

	public int deleteReply(int rId) {
		Connection conn = getConnection();
		ShareDAO sDAO = new ShareDAO();
		System.out.println("deleteReply service 입장") ;
		int result = sDAO.deleteReply(conn,rId);
		System.out.println("deleteReply service"+result) ;
		if(result>0) commit(conn) ;
		else		 rollback(conn) ;
		return result;
	}
}
