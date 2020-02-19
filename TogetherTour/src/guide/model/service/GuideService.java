package guide.model.service;

import static common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.ArrayList;

import guide.model.dao.GuideDAO;
import guide.model.vo.Gboard;

public class GuideService {

	public int getListCount() {
		Connection conn = getConnection();
		int result = new GuideDAO().getListCount(conn);
		close(conn);
		return result;
	}

	public ArrayList<Gboard> selectList(int currentPage) {
		Connection conn = getConnection();
		ArrayList<Gboard> gb = new GuideDAO().selectList(conn, currentPage);
		close(conn);
		return gb;
	}

}
