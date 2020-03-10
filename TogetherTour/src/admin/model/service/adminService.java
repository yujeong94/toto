package admin.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import admin.model.dao.adminDAO;
import buddy_me.model.vo.Report;

public class adminService {

	public ArrayList<Report> reportList() {
		Connection conn = getConnection();
		ArrayList<Report> report = new adminDAO().reportList(conn);
		
		close(conn);
		return report;
	}

	public int deleteUser(String userId, int rkey) {
		Connection conn = getConnection();
		adminDAO dao = new adminDAO();
		int result = dao.deleteUser(conn,userId);
		
		if(result > 0) {
			int result2 = dao.updateStatus(conn,rkey);
			if(result2 > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		} else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}

}
