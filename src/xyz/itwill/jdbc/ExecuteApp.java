package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteApp {
	public static void main(String[] args) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		
		Statement stmt = con.createStatement();
		
		/*
		String sql1 = "update student set name='임꺽정' where no = 2000";
		int rows = stmt.executeUpdate(sql1);
		
		System.out.println("[결과]" + rows + "명의 학생정보를 변경 하였습니다.");
		System.out.println("============================================================");
		
		String sql2 = "select * from student order by no";
		ResultSet rs = stmt.executeQuery(sql2);
		
		while(rs.next()) {
			System.out.println("학번 = " + rs.getInt("no") + ", 이름 = " + rs.getString("name"));
		}
		
		ConnectionFactory.close(con, stmt, rs);
		System.out.println("============================================================");
		*/
		
		int choice = 2;
		
		String sql = "";
		if(choice == 1) {
			sql = "update student set name='임꺽정' where no = 2000";
		} else {
			sql = "select * from student order by no";
		}
		
		//Statement.execute(String sql) : sql 명령을 전달하여 실행하는 메소드 - boolean 반환
		// => false 반환 : DML 명령(INSERT,DELETE,UPDATE)을 전달하여 실행한 경우의 반환값
		// => true 반환 : DQL 명령(SELECT)을 전달하여 실행한 경우의 반환값
		boolean result = stmt.execute(sql);
		
		ResultSet rs = null;
		if(result) {//DQL 명령을 전달하여 실행한 경우
			//Statement.getResultSet() : SELECT 명령의 실행결과(검색행)를 ResultSet 
			//인스턴스로 반환하는 메소드
			rs = stmt.getResultSet();
			
			while(rs.next()) {
				System.out.println("학번 = " + rs.getInt("no") + ", 이름 = " + rs.getString("name"));
			}
		} else {//DML 명령을 전달하여 실행한 경우
			//Statement.getUpdateCount() : INSERT,UPDATE,DELETE 명령의 실행결과(조작행의 갯수)를 
			//정수값으로 반환하는 메소드
			int rows = stmt.getUpdateCount();
			
			System.out.println("[결과]" + rows + "명의 학생정보를 변경 하였습니다.");
		}
	}
}
