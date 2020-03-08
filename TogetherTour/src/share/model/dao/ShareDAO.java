package share.model.dao;

import	java.io.* ;
import	java.sql.* ;
import	java.util.* ;
import	share.model.vo.* ;
import	static	common.JDBCTemplate.* ;

public	class ShareDAO {
	private	Properties prop = new Properties() ;
	
	public ShareDAO() {
		String fileName = ShareDAO.class.getResource("/sql/share/share_query.properties").getPath() ;
		try {
			prop.load(new FileReader(fileName)) ;
		} catch (FileNotFoundException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} catch (IOException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		}
	}
	
	public int insertShare(Connection conn, Share share) {
		PreparedStatement ptmt = null ;
		int result = 0 ;
		String query = prop.getProperty("insertShare") ;
		
		try {
			ptmt = conn.prepareStatement(query) ;
			ptmt.setString(1, share.getTitle()) ;
			int category = Integer.parseInt(share.getCategory()) ;
			ptmt.setInt(2, category) ;
			ptmt.setString(3, share.getWriter()) ;
			ptmt.setString(4, share.getContent()) ;
			ptmt.setString(5, share.getKakao()) ;
			ptmt.setInt(6, share.getKind()) ;
			ptmt.setString(7, share.getCountry()) ;
			ptmt.setString(8, share.getCity()) ;
			result = ptmt.executeUpdate() ;
			System.out.println("result : ["+result+"]") ;
		} catch (SQLException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} finally {
			close(ptmt) ;
		} return result ;
	}
	
	public	int		insertSattachment(Connection conn, ArrayList<Sattachment> fileList, Share share) {
		PreparedStatement ptmt = null ;
		int result = 0 ;
		String query = prop.getProperty("insertSattachment") ;
		
		try {
			for(int i=0; i<fileList.size(); i++) {
				Sattachment sa = fileList.get(i) ;
				ptmt = conn.prepareStatement(query);
				ptmt.setString(1, sa.getOriginName()) ;
				ptmt.setString(2, sa.getChangeName()) ;
				ptmt.setString(3, sa.getFilePath()) ;
				result += ptmt.executeUpdate() ;
			}
		} catch (SQLException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} finally {
			close(ptmt) ;
		} return result ;
	}

	public ArrayList selectSList(Connection conn) {
		Statement stmt = null ;
		ResultSet rset = null ;
		ArrayList<Share> list = null ;
		
		String query = prop.getProperty("selectSList") ;
		try {
			stmt = conn.createStatement() ;
			rset = stmt.executeQuery(query) ;
			list = new ArrayList<Share>() ;
			
			while(rset.next()) {
				list.add(new Share(rset.getInt("SBNUM"),
								   rset.getString("TITLE"),
								   rset.getString("CATEGORY"),
								   rset.getString("WRITER"),
								   rset.getDate("CREATE_DATE"),
								   rset.getString("CONTENT"),
								   rset.getString("STNAME"),
								   rset.getString("STATUS"),
								   rset.getInt("SCOUNT"),
								   rset.getString("KAKAO"),
								   rset.getInt("KIND"),
								   rset.getString("COUNTRY"),
								   rset.getString("CITY"))) ;
			}
		} catch (SQLException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} finally {
			close(rset) ;
			close(stmt) ;
		} return list ;
	}

	public ArrayList selectFList(Connection conn) {
		Statement stmt = null ;
		ResultSet rset = null ;
		ArrayList<Sattachment> list = null ;
		
		String query = prop.getProperty("selectFList") ;
		try {
			stmt = conn.createStatement() ;
			rset = stmt.executeQuery(query) ;
			list = new ArrayList<Sattachment>() ;
			
			while(rset.next())
				list.add(new Sattachment(rset.getInt("fid"), rset.getString("change_name"))) ;
		} catch (SQLException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} finally {
			close(rset) ;
			close(stmt) ;
		} return list ;
	}

	public int updateCount(Connection conn, int sbNum) {
		PreparedStatement ptmt = null ;
		int result = 0 ;
		String query = prop.getProperty("updateCount") ;
		
		try {
			ptmt = conn.prepareStatement(query) ;
			ptmt.setInt(1, sbNum) ;
			result = ptmt.executeUpdate() ;
		} catch (SQLException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} finally {
			close(ptmt) ;
		} return result ;
	}

	public Share selectShare(Connection conn, int sbNum) {
		PreparedStatement ptmt = null ;
		ResultSet rset = null ;
		Share share = null ;
		
		String query = prop.getProperty("selectShare") ;
		
		try {
			ptmt = conn.prepareStatement(query) ;
			ptmt.setInt(1, sbNum) ;
			
			rset = ptmt.executeQuery() ;
			
			if(rset.next()) {
				share = new Share(rset.getInt("SBNUM"), 
								  rset.getString("TITLE"),
								  rset.getString("CATEGORY"),
								  rset.getString("WRITER"),
								  rset.getDate("CREATE_DATE"),
								  rset.getString("CONTENT"),
								  rset.getString("STNAME"),
								  rset.getString("STATUS"),
								  rset.getInt("SCOUNT"),
								  rset.getString("KAKAO"),
								  rset.getInt("KIND"),
								  rset.getString("COUNTRY"),
								  rset.getString("CITY")) ;
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
		} return share ;
	}

	public ArrayList<Sattachment> selectPicture(Connection conn, int sbNum) {
		PreparedStatement ptmt = null ;
		ResultSet rset = null ;
		ArrayList<Sattachment> list = null ;
		String query = prop.getProperty("selectPicture") ;
		
		try {
			ptmt = conn.prepareStatement(query) ;
			ptmt.setInt(1, sbNum) ;
			rset = ptmt.executeQuery() ;
			list = new ArrayList<Sattachment>() ;
			
			while(rset.next()) {
				Sattachment satt = new Sattachment() ;
				satt.setfId(rset.getInt("FID")) ;
				satt.setOriginName(rset.getString("origin_name")) ;
				satt.setChangeName(rset.getString("change_name")) ;
				satt.setFilePath(rset.getString("file_path")) ;
				satt.setUploadDate(rset.getDate("upload_date")) ;
				list.add(satt) ;
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
		} return list ;
	}

	public int deleteShare(Connection conn, int sbNum) {
		PreparedStatement ptmt = null ;
		int result = 0 ;
		
		String query = prop.getProperty("deleteShare") ;
		
		try {
			ptmt = conn.prepareStatement(query) ;
			ptmt.setInt(1,sbNum) ;
			
			result = ptmt.executeUpdate() ;
		} catch (SQLException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} finally {
			close(ptmt) ;
		} return result ;
	}

	public ArrayList<sReply> selectReplyList(Connection conn, int sbNum) {
		PreparedStatement ptmt = null ;
		ResultSet rset = null ;
		ArrayList<sReply> rList = null ;
		
		String query = prop.getProperty("selectReplyList") ;
		
		try {
			ptmt = conn.prepareStatement(query) ;
			ptmt.setInt(1, sbNum) ;
			rset = ptmt.executeQuery() ;
			rList = new ArrayList<sReply>() ;
			while(rset.next()) {
				rList.add(new sReply(rset.getInt("rId"),
									 rset.getString("rContent"),
									 rset.getInt("sbNum"),
									 rset.getString("writer"),
									 rset.getDate("create_date"),
									 rset.getString("status"))) ;
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
		} return rList ;
	}

	public int insertReply(Connection conn, sReply reply) {
		PreparedStatement ptmt = null ;
		int result = 0 ;
		String query = prop.getProperty("insertReply") ;
		
		try {
			ptmt = conn.prepareStatement(query) ;
			ptmt.setString(1, reply.getContent()) ;
			ptmt.setInt(2, reply.getSbNum()) ;
			ptmt.setString(3, reply.getWriter()) ;
			result = ptmt.executeUpdate() ;
		} catch (SQLException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} finally {
			close(ptmt) ;
		} return result ;
	}

	public int updateOldSatt(Connection conn, int sbNum) {
		PreparedStatement ptmt = null ;
		int result = 0 ;
		String query = prop.getProperty("updateOldSatt") ;
		
		try {
			ptmt = conn.prepareStatement(query) ;
			ptmt.setInt(1, sbNum) ;
			result = ptmt.executeUpdate() ;
		} catch (SQLException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} finally {
			close(ptmt) ;
		} return result ;
	}

	public int updateShare(Connection conn, Share share) {
		PreparedStatement ptmt = null ;
		int result = 0 ;
		String query = prop.getProperty("updateShare") ;
		
		try {
			ptmt = conn.prepareStatement(query) ;
			ptmt.setString(1, share.getTitle()) ;
			ptmt.setInt(2, Integer.parseInt(share.getCategory())) ;
			ptmt.setString(3, share.getContent()) ;
			ptmt.setInt(4, Integer.parseInt(share.getStName())) ;
			ptmt.setString(5, share.getKakao()) ;
			ptmt.setInt(6, share.getKind()) ;
			ptmt.setString(7, share.getCountry()) ;
			ptmt.setString(8, share.getCity()) ;
			ptmt.setInt(9, share.getSbNum()) ;
			result = ptmt.executeUpdate() ;
		} catch (SQLException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} finally {
			close(ptmt) ;
		}
		return result ;
	}

	public int updateSattachment(Connection conn, ArrayList<Sattachment> fileList, Share share) {
		PreparedStatement ptmt = null ;
		int result = 0 ;
		String query = prop.getProperty("updateSattachment") ;
		
		try {
			for(int i=0; i<fileList.size(); i++) {
				Sattachment sa = fileList.get(i) ;
				ptmt = conn.prepareStatement(query);
				ptmt.setString(1, sa.getOriginName()) ;
				ptmt.setString(2, sa.getChangeName()) ;
				ptmt.setString(3, sa.getFilePath()) ;
				ptmt.setInt(4, share.getSbNum()) ;
				result += ptmt.executeUpdate() ;
			}
		} catch (SQLException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} finally {
			close(ptmt) ;
		} return result ;
	}

	public int deleteReply(Connection conn, int rId) {
		PreparedStatement ptmt = null ;
		int result = 0 ;
		String query = prop.getProperty("deleteReply") ;
		System.out.println("deleteReply dao 입장") ; 
		try {
			ptmt = conn.prepareStatement(query) ;
			ptmt.setInt(1, rId) ;
			
			result = ptmt.executeUpdate() ;
		} catch (SQLException e) {
			System.out.println("\n---------------------------[ERROR]---------------------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------------------------------------") ;
		} finally {
			close(ptmt) ;
		} return result ;
	}
}
