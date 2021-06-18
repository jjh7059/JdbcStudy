package xyz.itwill.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

//DBCP(DataBase Connection Pool) ���� �ν��Ͻ��� �����Ͽ� Connection �ν��Ͻ��� ��ȯ�ϰų�
//JDBC ���� �ν��Ͻ��� ���޹޾� �����ϴ� ����� �����ϴ� Ŭ����
// => DBMS ���� DAO Ŭ�������� ��ӹ޾� ����ϱ� ���� Ŭ����
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
