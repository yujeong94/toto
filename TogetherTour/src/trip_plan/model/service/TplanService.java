package trip_plan.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import trip_plan.model.dao.TplanDAO;
import trip_plan.model.vo.Tplan;

public class TplanService {

	public int getListCount() {
		
		Connection conn = getConnection();
		
		int result = new TplanDAO().getListCount(conn);
		close(conn);
		
		return result;
	}

	public ArrayList<Tplan> selectList(int currentPage) {
		Connection conn = getConnection();
		ArrayList<Tplan> list = new TplanDAO().selectList(conn, currentPage);
		close(conn);
		
		return list;
	}

	public int insertTplan(Tplan t) {
		Connection conn = getConnection();
		int result = new TplanDAO().insertTplan(conn, t);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public Tplan detailTplan(int tPnum) {
		
		Connection conn = getConnection();
		TplanDAO tDAO = new TplanDAO();
		
		int result  = tDAO.updateCount(conn, tPnum);
		
		Tplan t = null;
		
		if(result > 0) {
			t = tDAO.detailTplan(conn, tPnum);
			
			if(t != null) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		
		close(conn);
		return t;
	}

	public int updateTplan(Tplan t) {
		
		Connection conn = getConnection();
		int result = new TplanDAO().updateTplan(conn, t);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

	public int deleteTplan(int tPnum) {
		
		Connection conn = getConnection();
		TplanDAO tDAO = new TplanDAO();
		int result = tDAO.deleteTplan(conn, tPnum);
		
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result;
	}

}
