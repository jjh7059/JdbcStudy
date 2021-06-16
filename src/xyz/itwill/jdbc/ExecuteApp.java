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
		String sql1 = "update student set name='�Ӳ���' where no = 2000";
		int rows = stmt.executeUpdate(sql1);
		
		System.out.println("[���]" + rows + "���� �л������� ���� �Ͽ����ϴ�.");
		System.out.println("============================================================");
		
		String sql2 = "select * from student order by no";
		ResultSet rs = stmt.executeQuery(sql2);
		
		while(rs.next()) {
			System.out.println("�й� = " + rs.getInt("no") + ", �̸� = " + rs.getString("name"));
		}
		
		ConnectionFactory.close(con, stmt, rs);
		System.out.println("============================================================");
		*/
		
		int choice = 2;
		
		String sql = "";
		if(choice == 1) {
			sql = "update student set name='�Ӳ���' where no = 2000";
		} else {
			sql = "select * from student order by no";
		}
		
		//Statement.execute(String sql) : sql ����� �����Ͽ� �����ϴ� �޼ҵ� - boolean ��ȯ
		// => false ��ȯ : DML ���(INSERT,DELETE,UPDATE)�� �����Ͽ� ������ ����� ��ȯ��
		// => true ��ȯ : DQL ���(SELECT)�� �����Ͽ� ������ ����� ��ȯ��
		boolean result = stmt.execute(sql);
		
		ResultSet rs = null;
		if(result) {//DQL ����� �����Ͽ� ������ ���
			//Statement.getResultSet() : SELECT ����� ������(�˻���)�� ResultSet 
			//�ν��Ͻ��� ��ȯ�ϴ� �޼ҵ�
			rs = stmt.getResultSet();
			
			while(rs.next()) {
				System.out.println("�й� = " + rs.getInt("no") + ", �̸� = " + rs.getString("name"));
			}
		} else {//DML ����� �����Ͽ� ������ ���
			//Statement.getUpdateCount() : INSERT,UPDATE,DELETE ����� ������(�������� ����)�� 
			//���������� ��ȯ�ϴ� �޼ҵ�
			int rows = stmt.getUpdateCount();
			
			System.out.println("[���]" + rows + "���� �л������� ���� �Ͽ����ϴ�.");
		}
	}
}
