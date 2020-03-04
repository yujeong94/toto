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
		System.out.println("[Info] 사진 포함 게시글 등록 Service에 입력된 게시글의 이름은 ("+share.getTitle()+")입니다.") ;
		int result1 = sDAO.insertShare(conn, share) ;
		int result2 = sDAO.insertSattachment(conn, fileList, share) ;
		System.out.println("[Info] 사진 sAttachment 등록 결과는 "+((result2>0) ? "(성공,1)" : "(실패,0)")+"입니다.") ;
		if(result1>0 && result2>0)	commit(conn) ;
		else						rollback(conn) ;
		return result1 ;
	}

	public int insertShare(Share share) {
		Connection	conn = getConnection() ;
		ShareDAO	sDAO = new ShareDAO()  ;
		System.out.println("title - sBoard : ["+share.getTitle()+"]") ;
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
		ArrayList<sReply> rList = new ShareDAO().selectReplyList(conn, sbNum) ;
		System.out.println("[Test] 나눔 서비스의 selectReplyList 메소드에서 DAO안의 selectReplyList(conn, sbNum)에 접근하였습니다. 접근 결과는 다음과 같습니다.") ;
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
			System.out.println("[Info] [Insert Reply : Share : Service] [insert-Reply Result : Success]") ;
		} else {
			rollback(conn) ;
			System.out.println("[Info] [Insert Reply : Share : Service] [insert-Reply Result : Failed]") ;
		} return rList ;
	}
}
