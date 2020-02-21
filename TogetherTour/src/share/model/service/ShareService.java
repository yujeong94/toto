package share.model.service ;
 
import static common.JDBCTemplate.* ;

import java.sql.Connection;
import java.util.ArrayList;

import share.model.dao.ShareDAO;
import share.model.vo.Sattachment;
import share.model.vo.Share;

public class ShareService {
	public int insertShare(Share share, ArrayList<Sattachment> fileList) {
		Connection conn = getConnection() ;
		ShareDAO   sDAO = new ShareDAO()  ;
		int result1 = sDAO.insertShare(conn, share) ;
		int result2 = sDAO.insertSattachment(conn, fileList, share) ;
		if(result1>0 && result2>0)	commit(conn) ;
		else						rollback(conn) ;
		return result1 ;
	}

	public ArrayList selectShareList(int i) {
		Connection conn = getConnection() ;
		ArrayList  list = null ;
		System.out.println("SERVICE TEST") ;
		ShareDAO   sDAO = new ShareDAO() ;
		if(i==1) list = sDAO.selectSList(conn) ;
		else	 list = sDAO.selectFList(conn) ;
		close(conn) ;
		return list ;
	}

	public Share selectShare(int sbNum) {
		Connection conn = getConnection() ;
		ShareDAO dao = new ShareDAO() ;
		
		int result = dao.updateCount(conn, sbNum) ;
		
		Share share = null ;
		if(result > 0) {
			share = dao.selectBoard(conn, sbNum);
			if(share!=null) commit(conn) ;
			else			rollback(conn);
		} else {
			rollback(conn);
		} return share ;
	}

	public ArrayList<Sattachment> selectPicture(int sbNum) {
		
		return null ;
	}
}
