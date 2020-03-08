package main.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;

import buddy.model.dao.buddyBoardDAO;
import buddy.model.vo.buddyBoard;
import main.model.dao.mainSearchDAO;


public class mainSearchService {
	
	public int getSearchCount(int kind, String country, String city) {
		Connection conn = getConnection();
		
		int result = new mainSearchDAO().getSearchCount(conn, kind, country, city);
		close(conn);
		return result;
	}
	
	
	public ArrayList<buddyBoard> searchList(int currentPage, int kind, String country, String city) {
		
		Connection conn = getConnection();
		ArrayList<buddyBoard> list = new mainSearchDAO().searchList(conn, currentPage, kind, country, city);
		close(conn);
		return list;

	}
	
	
	


	public int getDateSearchCount(Date sqlStartDate, Date sqlLastDate) {
		Connection conn = getConnection();
		
		int result = new mainSearchDAO().getDateSearchCount(conn, sqlStartDate, sqlLastDate);
		close(conn);
		return result;
	}


	public ArrayList<buddyBoard> DateSearchList(int currentPage, Date sqlStartDate, Date sqlLastDate) {
		Connection conn = getConnection();
		ArrayList<buddyBoard> list = new mainSearchDAO().DateSearchList(conn, currentPage, sqlStartDate, sqlLastDate);
		close(conn);
		return list;
	}
}
