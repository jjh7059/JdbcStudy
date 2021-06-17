package xyz.itwill.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//PreparedStatement : ���ӵ� DBMS ������ SQL ����� �����Ͽ� ����� ����� ��ȯ�ϴ� ����� �����ϴ� �ν��Ͻ�
//���� : InParameter�� �̿��Ͽ� ���� ���޹޾� SQL ��� �ۼ�
// => InParameter�� ����Ͽ� �������� ���������� ȿ���� ����
// => InSQL ��ŷ ��� ��ȿȭ ó�� - ����� �Է°��� ������ SQL ����� ������ ó��
//���� : PreparedStatement �ν��Ͻ��� �ϳ��� SQL ��ɸ��� �����Ͽ� ���� ����
public class PreparedStatementApp {
	public static void main(String[] args) throws Exception {
		//Ű����� �л������� �Է¹޾� STUDENT ���̺� �����Ͽ� �����ϰ� STUDENT ���̺�
		//����� ��� �л������� �˻��Ͽ� ����ϴ� JDBC ���α׷�
		
		/*
		//Ű����� �л������� �Է¹ޱ� ���� �Է� ��Ʈ�� ����
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				
		//Ű����� �л������� �Է¹޾� ����
		System.out.println("<<�л����� �Է�>>");
		System.out.print("�й� �Է� >> ");
		int no = Integer.parseInt(in.readLine());
		System.out.print("�̸� �Է� >> ");
		String name = in.readLine();
		System.out.print("��ȭ��ȣ �Է� >> ");
		String phone = in.readLine();
		System.out.print("�ּ� �Է� >> ");
		String address = in.readLine();
		System.out.print("������� �Է� >> ");
		String birthday = in.readLine();
		System.out.println("=======================================================");
		
		//�Էµ� �л������� STUDENT ���̺� �����Ͽ� ����
		Connection con = ConnectionFactory.getConnection();
		
		//Connection.prepareStatement(String sql) : ���ӵ� DBMS ������ ���޵� SQL �����
		//������ PreparedStatement �ν��Ͻ��� ��ȯ�ϴ� �޼ҵ�
		// => PreparedStatement �ν��Ͻ��� ����� SQL ��ɿ� ?(InParameter) ��ȣ ���
		//InParameter : Java �������� �����޾� SQL ����� ������ ǥ���ϱ� ���� ��ȣ
		// => ��� InParameter���� �ݵ�� Java �������� ���޵Ǿ�߸� SQL ��� �ϼ�
		//PreparedStatement.setXXX(int parameterIndex, XXX value)
		// => ���ϴ� ��ġ�� InParameter�� Java �������� �����ϴ� �޼ҵ�
		// => �޼ҵ�� InParameter�� ������ŭ ȣ��
		String sql1 = "insert into student values(?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql1);
		pstmt.setInt(1, no);
		pstmt.setString(2, name);
		pstmt.setString(3, phone);
		pstmt.setString(4, address);
		pstmt.setString(5, birthday);
		
		//PreparedStatement.executeUpdate() : PreparedStatement �ν��Ͻ��� ����� SQL ���
		//(INSERT,UPDATE,DELETE)�� �����ϴ� �޼ҵ� - �������� ����(int) ��ȯ
		int rows = pstmt.executeUpdate();
		
		System.out.println("[���]" + rows + "���� �л������� ���� �Ͽ����ϴ�.");
		System.out.println("=======================================================");
		
		//STUDENT ���̺� ����� ��� ��(�л�����)�� �˻��Ͽ� ���
		String sql2 = "select * from student order by no";
		pstmt = con.prepareStatement(sql2);
		
		//PreparedStatement.executeQuery() : PreparedStatement �ν��Ͻ��� ����� SQL ���
		//(SELECT)�� �����ϴ� �޼ҵ� - �˻���(ResultSet �ν��Ͻ�) ��ȯ
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println("<<�л���� ���>>");
		while(rs.next()) {
			System.out.println("�й� = " + rs.getInt("no") + ", �̸� = " + rs.getString("name"));
		}
		
		ConnectionFactory.close(con, pstmt, rs);
		System.out.println("=======================================================");
		*/
		
		//Ű����� �̸��� �Է¹޾� STUDENT ���̺� ����� �л������� �ش� �̸����� �˻��Ͽ�
		//����ϴ� JDBC ���α׷� �ۼ�
				
		//Ű����� �л������� �Է¹ޱ� ���� �Է� ��Ʈ�� ����
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				
		//Ű����� �̸� �Է¹޾� ����
		System.out.println("<<�л����� �Է�>>");
		System.out.print("�̸� �Է� >> ");
		String name = in.readLine();
		System.out.println("=======================================================");
		//STUDENT ���̺� �Էµ� �̸��� �л������� �˻��Ͽ� ���
		Connection con = ConnectionFactory.getConnection();
		
		//InParameter�� Java �������� ������ SQL ����� �����θ� �νĵǾ� ó��
		String sql = "select * from student where name=? order by no";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, name);
		
		ResultSet rs = pstmt.executeQuery();
		
		System.out.println("<<�˻���� ���>>");
		if(rs.next()) {
			do {
				System.out.println("�й� = " + rs.getInt("no") + ", �̸� = " + rs.getString("name"));
			} while (rs.next());
		} else {
			System.out.println("�˻��� �л������� �����ϴ�.");
		}
		
		ConnectionFactory.close(con, pstmt, rs);
		System.out.println("=======================================================");

	}
}
