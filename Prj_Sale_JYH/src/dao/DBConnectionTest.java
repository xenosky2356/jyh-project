package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest {

	public static void main(String[] args) {
		Connection conn = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String Driver = "jdbc:oracle:thin:@localhost:1521:xe";
			String User = "db7";
			String Pass = "1234";
			conn = DriverManager.getConnection(Driver, User, Pass);
			System.out.print("Oracle 접속에 성공하였습니다!\n");
		} catch (SQLException e) {
			System.out.println("접속 실패 : " + e);
		} catch (Exception ex) {
			System.out.println("예외 발생 : " + ex);
		} finally {
			if (conn != null) {
				try {
					conn.close();
					System.out.print("Oracle 연결 중지!");
				} catch (SQLException e) {
					System.out.println("예외 발생 : " + e);
				}
			}
		}
	}

}
