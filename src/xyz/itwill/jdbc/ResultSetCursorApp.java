package xyz.itwill.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Statement 인스턴스를 반환하는 메소드에 전달되는 값에 따라 ResultSet 인스턴스 사용방법 변경
// => ResultSet 커서의 위치 변경 또는 ResultSet 커서 위치의 처리행에 대한 컬럼값 조작 가능

//STUDENT 테이블에 저장된 모든 학생정보를 검색하여 출력하는 JDBC 프로그램
public class ResultSetCursorApp {
	public static void main(String[] args) throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		
		//Connection.createStatement() : Statement 인스턴스를 반환하는 메소드
		//Statement : SQL 명령을 접속된 DBMS 서버에 전달하는 기능을 제공하는 인스턴스
		//Statement.executeQuery(String sql) : SELECT 명령을 전달하여 실행하고 검색결과를 
		//ResultSet 인스턴스로 반환하는 메소드
		// => ResultSet 인스턴스는 커서를 이용하여 행단위로 처리
		// => ResultSet 커서는 다음행으로 이동 가능하며 커서 위치의 행에 대한 조작 불가능
		Statement stmt = con.createStatement();
		
		String sql = "select * from student order by no";
		ResultSet rs = stmt.executeQuery(sql);
		
		//ResultSet.next() : ResultSet 커서를 다음행으로 이동하는 메소드
		// => 커서 위치에 처리행이 없는 경우 false 반환, 처리행이 있는 경우 true 반환 
		while(rs.next()) {
			//ResultSet.getRow() : ResultSet 커서가 위치한 처리행의 행번호(ColumnIndex)를 반환하는 메소드
			//ResultSet.getXXX(String columnLabel) : ResultSet 커서가 위치한 처리행의 컬럼값을 반환하는 메소드
			System.out.println(rs.getRow() + "행 : 학번 = " + rs.getInt("no") + ", 이름 = " + rs.getString("name"));
		}
		
		ConnectionFactory.close(con, stmt, rs);
		System.out.println("=================================================================");
		
		con = ConnectionFactory.getConnection();
		//Connection.createStatement(int resultSetType, int resultSetConcurrency)
		// => Statement 인스턴스를 반환하는 메소드
		// => 매개변수에 전달되는 값에 따라 SELECT 명령의 실행결과를 저장한 ResultSet 인스턴스의 속성 변경
		//resultSetType : ResultSet 커서의 이동 관련 속성 변경
		// => ResultSet.TYPE_FORWARD_ONLY : ResultSet 커서를 다음행으로만 이동(기본)
		// => ResultSet.TYPE_SCROLL_INSENSITIVE : ResultSet 커서를 원하는 위치의행으로 이동 - 조작행 미반환
		// => ResultSet.TYPE_SCROLL_SENSITIVE : ResultSet 커서를 원하는 위치의행으로 이동 - 조작행 반환
		//resultSetConCurrency : ResultSet 커서 위치의 처리행에 대한 조작 관련 속성 변경
		// => ResultSet.CONCUR_READ_ONLY : 처리행에 대한 조작 불가능(기본)
		// => ResultSet.CONCUR_UPDATABLE : 처리행에 대한 조작 가능 - 테이블 적용 가능
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		sql = "select * from student order by no";
		rs = stmt.executeQuery(sql);
		
		//Result.first() : ResultSet 커서를 첫번째 검색행을 이동하는 메소드
		rs.first();
		System.out.println(rs.getRow() + "행 : 학번 = " + rs.getInt("no") + ", 이름 = " + rs.getString("name"));
		
		//Result.last() : ResultSet 커서를 마지막 검색행을 이동하는 메소드
		rs.last();
		System.out.println(rs.getRow() + "행 : 학번 = " + rs.getInt("no") + ", 이름 = " + rs.getString("name"));
		
		//Result.absolute(int rowIndex) : ResultSet 커서를 원하는 위치(row)의 검색행을 이동하는 메소드
		rs.absolute(2);
		System.out.println(rs.getRow() + "행 : 학번 = " + rs.getInt("no") + ", 이름 = " + rs.getString("name"));
		System.out.println("===================================================================");
		
		//Result.beforeFirst() : ResultSet 커서를 BOF로 이동하는 메소드
		rs.beforeFirst();
		while(rs.next()) {
			System.out.println(rs.getRow() + "행 : 학번 = " + rs.getInt("no") + ", 이름 = " + rs.getString("name"));
		}
		System.out.println("===================================================================");
		
		//Result.afterLast() : ResultSet 커서를 EOF로 이동하는 메소드
		rs.afterLast();
		//Result.previous() : ResultSet 커서를 이전행로 이동하는 메소드
		while(rs.previous()) {
			System.out.println(rs.getRow() + "행 : 학번 = " + rs.getInt("no") + ", 이름 = " + rs.getString("name"));
		}
		ConnectionFactory.close(con, stmt, rs);
		System.out.println("===================================================================");
		
		
		con = ConnectionFactory.getConnection();
		stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		//ResultSet 커서 위치의 행을 조작하고자 할 경우 SELECT 명령에서 검색대상으로 * 사용 불가능
		sql = "select no,name,phone,address,birthday from student order by no";
		rs = stmt.executeQuery(sql);
		
		/*
		//ResultSet 인스턴스의 2번째 위치에 새로운 행을 삽입하고 테이블에 적용
		rs.absolute(2);
		
		//ResultSet.moveToInsertRow() : ResultSet 커서 위치에 새로운 행을 삽입하여 생성하는 메소드
		// => 삽입된 행 다음에 존재하는 기존 행은 자동으로 다음행으로 이동
		rs.moveToInsertRow();
		
		//삽입된 새로운 행의 컬럼값을 변경
		//ResultSet.updateXXX(String columnLabel, XXX value) : ResultSet 커서 위치의
		//처리행의 컬럼값을 변경하는 메소드 - XXX : Java 자료형
		rs.updateInt("no", 4000);
		rs.updateString("name", "일지매");
		rs.updateString("phone", "010-7861-3132");
		rs.updateString("address", "수원시 팔달구");
		//rs.updateDate("birthday", new Date(100, 11, 20));//2000-12-20
		rs.updateString("birthday", "2000-12-20 00:00:00.0");
		
		//ResultSet.insertRow() : 커서 위치의 행을 테이블의 행으로 삽입하는 메소드
		rs.insertRow();
		*/
		
		/*
		//ResultSet 인스턴스의 3번째 위치의 행을 삭제하고 테이블에 적용
		rs.absolute(3);
		
		//ResultSet.deleteRow() : 커서 위치의 행으로 테이블의 행을 삭제하는 메소드
		rs.deleteRow();
		*/
		
		//ResultSet 인스턴스의 2번째 위치의 행의 이름을 [임걱정]으로 변경하여 테이블에 적용
		rs.absolute(2);
		rs.updateString("name", "임걱정");
		
		//ResultSet.updateRow() : 커서 위치의 행으로 테이블의 컬럼값을 변경하는 메소드
		rs.updateRow();
		
		ConnectionFactory.close(con, stmt, rs);
	}
}
