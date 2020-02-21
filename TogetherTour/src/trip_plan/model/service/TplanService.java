package trip_plan.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
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

}
