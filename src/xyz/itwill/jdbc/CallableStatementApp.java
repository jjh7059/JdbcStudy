package xyz.itwill.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;

//키보드로 학번을 입력받아 STUDENT 테이블에 저장된 학생정보 중 입력된 학번의 학생정보를
//삭제하는 JDBC 프로그램
public class CallableStatementApp {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("<<학생정보 삭제>>");
		System.out.print("학번 입력 >> ");
		int no = Integer.parseInt(in.readLine());
		System.out.println("=======================================================");
		
		Connection con = ConnectionFactory.getConnection();
		
		/*
		//DELETE_STUDENT 저장 프로시저 생성
		CREATE OR REPLACE PROCEDURE DELETE_STUDENT(VNO IN NUMBER) IS
		BEGIN
		    DELETE FROM STUDENT WHERE NO=VNO;
		    COMMIT;
		END;
		/
		*/
		
		//Connection.prepareCall(String sql) : SQL 명령을 저장한 CallableStatement 인스턴스를 반환하는 메소드
		// => CallableStatement 인스턴스는 저장 프로시저를 호출하는 SQL 명령을 저장
		// => 저장 프로시저를 호출하는 명령 : {call 프로시저명({값|변수},{값|변수},...}
		// => ? 기호(InParameter) 사용 가능
		String sql = "{call delete_student(?)}";
		CallableStatement cstmt = con.prepareCall(sql);
		cstmt.setInt(1, no);
		
		//CallableStatement.execute() : CallableStatement 인스턴스에 저장된 SQL 명령을 전달하여 실행하는 메소드
		cstmt.execute();
		
		System.out.println("[결과]저장 프로시저를 호출하여 학생정보를 삭제 하였습니다.");
		
		ConnectionFactory.close(con, cstmt);
	}
}
