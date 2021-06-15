package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//STUDENT ���̺��� ����� �л����� �� �й��� [3000]�� �л������� �����ϴ� JDBC ���α׷� �ۼ�
public class DeleteStudentApp {
	public static void main(String[] args) {
		Connection con = null;
		Statement stmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "scott";
			String password = "tiger";
			
			con = DriverManager.getConnection(url, user, password);
			stmt = con.createStatement();
			
			String sql = "delete from student where no=3000";
			int rows = stmt.executeUpdate(sql);
			System.out.println("[�޽���]" + rows + "���� �л��� �����Ǿ����ϴ�.");
			
		} catch (ClassNotFoundException e) {
			System.out.println("[����]OracleDriver Class ������ ã�� �� �����ϴ�.");
		} catch (SQLException e) {
			System.out.println("[����]JDBC ���� ������ �߻��Ͽ����ϴ�.");
		} finally {		
			try {
				if(stmt!=null) stmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {}
		}
	}
}