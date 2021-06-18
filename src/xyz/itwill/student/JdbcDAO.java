package xyz.itwill.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

//DBCP(DataBase Connection Pool) 관련 인스턴스를 생성하여 Connection 인스턴스를 반환하거나
//JDBC 관련 인스턴스를 전달받아 제거하는 기능을 제공하는 클래스
// => DBMS 관련 DAO 클래스에서 상속받아 사용하기 위한 클래스
public class JdbcDAO {
	private static PoolDataSource _pds;
	
	static {
		_pds = PoolDataSourceFactory.getPoolDataSource();
		try {
			_pds.setConnectionFactoryClassName("oracle.jdbc.driver.OracleDriver");
			_pds.setURL("jdbc:oracle:thin:@localhost:1521:xe");
			_pds.setUser("scott");
			_pds.setPassword("tiger");
			_pds.setInitialPoolSize(3);
			_pds.setMaxPoolSize(5);
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public Connection getConnection() {
		Connection con = null;
		try {
			con = _pds.getConnection();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return con;
	}
	
	public void close(Connection con) {
		try {
			if(con != null) con.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public void close(Connection con, PreparedStatement pstmt) {
		try {
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
}
