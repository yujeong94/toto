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

	public int deleteUser(String userId) {
		Connection conn = getConnection();
		int result = new adminDAO().deleteUser(conn,userId);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}

}
