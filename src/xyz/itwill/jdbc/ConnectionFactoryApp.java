package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//DEPT ���̺� ����� ��� �μ������� �˻��Ͽ� ����ϴ� JDBC ���α׷�
public class ConnectionFactoryApp {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionFactory.getConnection();
			
			stmt = con.createStatement();
			
			String sql = "select * from dept order by deptno";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println("�μ���ȣ = " + rs.getString("deptno") 
					+ ", �μ��̸� = " + rs.getString("dname")
					+ ", �μ���ġ = " + rs.getString("loc"));
			}
		} catch (SQLException e) {
			System.out.println("[����]SQL ���� = " + e.getMessage());
		} finally {
			ConnectionFactory.close(con, stmt, rs);
		}
	}
}
