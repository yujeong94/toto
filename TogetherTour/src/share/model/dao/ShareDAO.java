package share.model.dao;

import	java.io.* ;
import	java.sql.* ;
import	java.util.* ;
import	share.model.vo.* ;
import	static	common.JDBCTemplate.* ;

public	class ShareDAO {
	private	Properties prop = new Properties() ;
	
	public	ShareDAO() {
		String fileName = ShareDAO.class.getResource("/sql/share/share_query.properties").getPath() ;
		try {
			prop.load(new FileReader(fileName)) ;
		} catch (FileNotFoundException e) {
			System.out.println("------------[ERROR]------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------") ;
		} catch (IOException e) {
			System.out.println("------------[ERROR]------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------") ;
		}
	}
	
	public	int		insertShare(Connection conn, Share share) {
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
		} catch (SQLException e) {
			System.out.println("------------[ERROR]------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------") ;
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
				ptmt = conn.prepareStatement(query)  ;
				ptmt.setString(1, sa.getOriginName()) ;
				ptmt.setString(2, sa.getChangeName()) ;
				ptmt.setString(3, sa.getFilePath()) ;
				ptmt.setInt(4, sa.getFileLevel()) ;
				result += ptmt.executeUpdate() ;
			}
		} catch (SQLException e) {
			System.out.println("------------[ERROR]------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------") ;
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
				System.out.println("DAO S TEST") ;
			}
		} catch (SQLException e) {
			System.out.println("------------[ERROR]------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------") ;
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
			
			System.out.println("DAO F TEST") ;
		} catch (SQLException e) {
			System.out.println("------------[ERROR]------------") ;
			System.out.println(e.getMessage()) ;
			System.out.println("-------------------------------") ;
			e.printStackTrace() ;
			System.out.println("-------------------------------") ;
		} finally {
			close(rset) ;
			close(stmt) ;
		} return list ;
	}

	public int updateCount(Connection conn, int sbNum) {
		
		return 0 ;
	}

	public Share selectBoard(Connection conn, int sbNum) {
		
		return null ;
	}

	public ArrayList<Sattachment> selectPicture(Connection conn, int bId) {
		
		return null;
	}
}
