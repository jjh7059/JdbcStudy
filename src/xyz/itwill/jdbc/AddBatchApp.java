package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

//다수의 행을 삽입하거나 변경하고자 할 경우 Batch 영역에 SQL 명령을 저장하여
//한꺼번에 SQL 명령을 전달하여 실행하는 방법
public class AddBatchApp {
	public static void main(String[] args) throws Exception {
		Connection con = ConnectionFactory.getConnection();
		
		String sql = "insert into student values(?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, 5000);
		pstmt.setString(2, "장길산");
		pstmt.setString(3, "010-5746-2178");
		pstmt.setString(4, "서울시 도봉구");
		pstmt.setString(5, "1996-05-07");
		//PreparedStatement.addBatch() : PreparedStatement 영역에 저장된 SQL 명령을 
		//Batch 영역에 추가하는 메소드		
		pstmt.addBatch();
		
		pstmt.setInt(1, 6000);
		pstmt.setString(2, "홍경래");
		pstmt.setString(3, "010-5153-5153");
		pstmt.setString(4, "부산시 사하구");
		pstmt.setString(5, "1995-11-27");
		pstmt.addBatch();
		
		pstmt.setInt(1, 7000);
		pstmt.setString(2, "로빈훗");
		pstmt.setString(3, "010-5745-2168");
		pstmt.setString(4, "서울시 관악구");
		pstmt.setString(5, "1999-09-09");
		pstmt.addBatch();
		
		//PreparedStatement.executeBatch() : Batch 영역에 추가된 모든 SQL 명령을 접속된
		//DBMS 서버에 전달하여 실행하는 메소드 - int[] 반환
		pstmt.executeBatch();
		
		System.out.println("SQL 명령에 대한 배치 작업 완료");
		
		ConnectionFactory.close(con, pstmt);
	}
}
