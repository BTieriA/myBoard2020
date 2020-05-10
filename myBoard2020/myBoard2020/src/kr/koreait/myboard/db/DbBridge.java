package kr.koreait.myboard.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbBridge {
	public static void main(String[] args) {
		try {
			getCon();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getCon() throws Exception{
		Connection con = null;
		final String DBURL="jdbc:mysql://127.0.0.1:4406/myboard?serverTimezone=UTC";
//		myboard = 테이블명
		final String USER ="root";
		final String PASSWORD ="root";
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection(DBURL, USER, PASSWORD);
		System.out.println("DB 연결!");
		return con;
	}
	
	public static void close(Connection con, PreparedStatement ps) {
		close(con, ps, null);
	}
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try {rs.close();} catch(Exception e) { e.printStackTrace(); }
		}
		
		if(ps != null) {
			try {ps.close();} catch(Exception e) { e.printStackTrace(); }
		}
		
		if(con != null) {
			try {con.close();} catch(Exception e) { e.printStackTrace(); }
		}
	}
}
