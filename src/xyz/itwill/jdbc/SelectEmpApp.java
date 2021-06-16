package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//EMP 테이블에서 모든 사원의 사원번호,사원이름,업무,급여,부서번호를 급여순으로 내림차순 정렬되도록 
//검색하여 출력하는 JDBC 프로그램
public class SelectEmpApp {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "tiger";
			
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			
			String sql = "select empno,ename,job,sal,deptno from emp order by sal desc";
			rs = stmt.executeQuery(sql);
			
			/*
			if(rs.next()) {
				do {
					int empno = rs.getInt("empno");
					String ename = rs.getString("ename");
					String job = rs.getString("job");
					int sal = rs.getInt("sal");
					int deptno = rs.getInt("deptno");
					
					System.out.println("사원번호 = " + empno);
					System.out.println("사원이름 = " + ename);
					System.out.println("업무 = " + job);
					System.out.println("급여 = " + sal);
					System.out.println("부서번호 = " + deptno);
					System.out.println("===================================");
				} while (rs.next());
			} else {
				System.out.println("[메시지]검색된 정보가 없습니다.");
			}
			*/
			
			while(rs.next()) {
				System.out.println("사원번호 = " + rs.getInt("empno"));
				System.out.println("사원이름 = " + rs.getString("ename"));
				System.out.println("업무 = " + rs.getString("job"));
				System.out.println("급여 = " + rs.getInt("sal"));
				System.out.println("부서번호 = " + rs.getInt("deptno"));
				System.out.println("===================================");
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("[에러]OracleDriver 클래스를 찾을 수 없습니다.");
		} catch (SQLException e) {
			System.out.println("[에러]JDBC 관련 문제가 발생 하였습니다." + e.getMessage());
		} finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {}
		}
	}
}
