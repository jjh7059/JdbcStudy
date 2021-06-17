package xyz.itwill.dbcp;

import java.sql.Connection;
import java.sql.SQLException;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

//javax.sql.DataSource : DBCP 클래스를 작성하기 위해 상속받는 인터페이스
// => DBCP 클래스에 동일한 이름의 메소드가 선언되도록 메소드 작성 규칙을 제공

//UCP(Universal Connection Pool) 라이브러리 DBCP 관련 클래스를 이용한 JDBC 프로그램
public class DataSourceApp {
	public static void main(String[] args)  throws SQLException {
		//PoolDataSource : DBCP 기능을 제공하기 위한 인스턴스
		//PoolDataSourceFactory.getPoolDataSource() : PoolDataSource 인스턴스를 생성하여 반환하는 메소드
		//DataSource ds = PoolDataSourceFactory.getPoolDataSource();
		PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();
		
		//Connection 인스턴스를 생성하기 위해 PoolDataSource 인스턴스의 필드값 변경
		//PoolDataSource.setConnectionFactoryClassName(String driver) : Driver 클래스를 변경하는 메소드
		pds.setConnectionFactoryClassName("oracle.jdbc.driver.OracleDriver");
		//PoolDataSource.setURL(String url) : DBMS 서버의 URL을 변경하는 메소드
		pds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
		//PoolDataSource.setUser(String user) : 사용자를 변경하는 메소드
		pds.setUser("scott");
		//PoolDataSource.setPassword(String password) : 비밀번호를 변경하는 메소드
		pds.setPassword("tiger");
		
		//PoolDataSource 인스턴스에 미리 생성되어 저장될 Connection 인스턴스의 제한 갯수를
		//변경하기 위한 메소드 호출 - 제한 갯수를 변경하지 않을 경우 기본값 사용
		pds.setInitialPoolSize(2);//최초 생성되는 Connection 인스턴스의 갯수 변경
		pds.setMaxPoolSize(3);//최대 생성 가능한 Connection 인스턴스의 갯수 변경
		
		//PoolDataSource.getConnection() : PoolDataSource 인스턴스에 미리 생성되어 저장된 
		//Connection 인스턴스 중 하나를 반환하는 메소드
		Connection con1 = pds.getConnection();
		System.out.println("con1 = " + con1);
		//PoolDataSource.getAvailableConnectionsCount() : 사용 가능한 Connection 인스턴스의
		//갯수를 반환하는 메소드
		System.out.println("사용 가능한 Connection 인스턴스의 갯수 = " 
				+ pds.getAvailableConnectionsCount());
		//Connection 인스턴스를 제거하면 자동으로 PoolDataSource 인스턴스의 새로운
		//Connection 인스턴스를 생성하여 추가
		con1.close();
		
		System.out.println("사용 가능한 Connection 인스턴스의 갯수 = " 
				+ pds.getAvailableConnectionsCount());
		System.out.println("=============================================================");
		
		Connection con2 = pds.getConnection();
		System.out.println("con2 = " + con2);
		con2.close();
		System.out.println("=============================================================");
		Connection con3 = pds.getConnection();
		System.out.println("con3 = " + con3);
		con3.close();
		System.out.println("=============================================================");
		Connection con4 = pds.getConnection();
		System.out.println("con4 = " + con4);
		con4.close();
	}
}
