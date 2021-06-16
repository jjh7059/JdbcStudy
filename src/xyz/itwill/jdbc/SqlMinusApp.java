package xyz.itwill.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


//키보드로 SQL 명령(INSERT,UPDATE,DELETE,SELECT)을 입력받아 DBMS 서버에 전달하여 실행된
//결과를 출력하는 JDBC 프로그램
// => SQL 명령을 계속 입력받아 전달하여 실행된 결과값을 출력
// => SQL 명령 대신 [EXIT]를 입력한 경우 프로그램 종료 - 대소문자 미구분
// => 키보드로 입력된 SQL 명령이 잘못된 경우 에러 메시지 출력 - SQLException에 대한 예외처리
// => SQLPLUS 프로그램을 참고하여 처리 결과 출력
public class SqlMinusApp {
	public static void main(String[] args) throws Exception {
		//키보드로 SQL 명령을 입력받기 위한 입력 스트림 생성
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		Connection con = ConnectionFactory.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = null;
		
		while(true) {
			//키보드로 SQL 명령을 입력받아 저장
			System.out.print("SQL> ");
			String sql = in.readLine().trim();
			
			if(sql.equalsIgnoreCase("exit")) break;
			
			if(sql==null || sql.equals("")) continue;
			
			try {
				//키보드로 입력된 SQL 명령을 전달하여 실행
				if(stmt.execute(sql)) {//DQL
					rs = stmt.getResultSet();
					
					if(rs.next()) {//검색행이 있는 경우
						//검색행에 대한 부가적인 정보를 반환받아 저장
						ResultSetMetaData rsmd = rs.getMetaData();
						
						//검색행에 대한 컬럼의 갯수를 반환받아 저장
						int columnCount = rsmd.getColumnCount();
						
						System.out.println("====================================================================================");
						//검색행의 컬럼명을 반환받아 출력
						for(int i=1; i<=columnCount; i++) {
							System.out.print(rsmd.getColumnLabel(i) + "\t");
						}
						System.out.println();
						System.out.println("====================================================================================");
						//검색행의 컬럼값을 반환받아 출력 - 반복 처리
						do {
							for(int i = 1; i <= columnCount; i++) {
								String columnValue = rs.getString(i);
								if(rsmd.getColumnTypeName(i).equals("DATE")) {
									columnValue = columnValue.substring(0,10);
								}
								if(columnValue == null) {
									columnValue = "    ";
								}
								System.out.print(columnValue + "\t");
							}
							System.out.println();
						} while (rs.next());
						
					} else {//검색행이 없는 경우
						System.out.println("검색된 결과가 없습니다.");
					}
					
				} else {//DDL
					int rows = stmt.getUpdateCount();
					System.out.println(rows + "개의 행을 " + sql.substring(0, 6).toUpperCase() + "하였습니다.");
				}
			} catch (SQLException e) {
				//키보드로 입력받아 전달하여 실행된 SQL 명령이 잘못된 경우 SQLException 발생
				System.out.println("SQL 오류 = " + e.getMessage());
			}
		}
		
		ConnectionFactory.close(con, stmt, rs);
		System.out.println("[메시지]SQLMinus 프로그램을 종료합니다.");
	}
}
