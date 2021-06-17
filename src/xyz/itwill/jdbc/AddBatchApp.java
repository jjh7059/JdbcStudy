package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

//�ټ��� ���� �����ϰų� �����ϰ��� �� ��� Batch ������ SQL ����� �����Ͽ�
//�Ѳ����� SQL ����� �����Ͽ� �����ϴ� ���
public class AddBatchApp {
	public static void main(String[] args) throws Exception {
		Connection con = ConnectionFactory.getConnection();
		
		String sql = "insert into student values(?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, 5000);
		pstmt.setString(2, "����");
		pstmt.setString(3, "010-5746-2178");
		pstmt.setString(4, "����� ������");
		pstmt.setString(5, "1996-05-07");
		//PreparedStatement.addBatch() : PreparedStatement ������ ����� SQL ����� 
		//Batch ������ �߰��ϴ� �޼ҵ�		
		pstmt.addBatch();
		
		pstmt.setInt(1, 6000);
		pstmt.setString(2, "ȫ�淡");
		pstmt.setString(3, "010-5153-5153");
		pstmt.setString(4, "�λ�� ���ϱ�");
		pstmt.setString(5, "1995-11-27");
		pstmt.addBatch();
		
		pstmt.setInt(1, 7000);
		pstmt.setString(2, "�κ���");
		pstmt.setString(3, "010-5745-2168");
		pstmt.setString(4, "����� ���Ǳ�");
		pstmt.setString(5, "1999-09-09");
		pstmt.addBatch();
		
		//PreparedStatement.executeBatch() : Batch ������ �߰��� ��� SQL ����� ���ӵ�
		//DBMS ������ �����Ͽ� �����ϴ� �޼ҵ� - int[] ��ȯ
		pstmt.executeBatch();
		
		System.out.println("SQL ��ɿ� ���� ��ġ �۾� �Ϸ�");
		
		ConnectionFactory.close(con, pstmt);
	}
}
