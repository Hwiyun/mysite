package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.douzone.mysite.vo.UserVo;

public class UserDao {

	public void insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into user values(null, ?, ?, password(?), ?, now())";
			pstmt = conn.prepareStatement(sql);
			
		
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://192.168.10.110:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "select no, name from user where email= ? and password = password(?)";
			pstmt = conn.prepareStatement(sql);
			
		
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = new UserVo();
				
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				result.setNo(no);
				result.setName(name);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public UserVo findByNo(long no) {
		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "select no, name, email, password from user where no = ?";
			pstmt = conn.prepareStatement(sql);
			
		
			pstmt.setLong(1, no);
			
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = new UserVo();
				
				Long number = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String password = rs.getString(4);
				
				result.setNo(number);
				result.setName(name);
				result.setEmail(email);
				result.setPassword(password);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public void update(UserVo vo) {
		UserVo result = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			if("".equals(vo.getPassword())) {
				String sql = "update user set name = ?,gender = ? where no = ?";
				pstmt = conn.prepareStatement(sql);
				
			
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setLong(3, vo.getNo());
			} else {
				String sql = "update user set name = ?, password = password(?), gender = ? where no = ?";
				pstmt = conn.prepareStatement(sql);
				
			
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPassword());
				pstmt.setString(3, vo.getGender());
				pstmt.setLong(4, vo.getNo());
			}
			
			
			
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = new UserVo();
				
				String name = rs.getString(1);
				String email = rs.getString(2);
				String password = rs.getString(3);
				String gender = rs.getString(4);
				
				result.setName(name);
				result.setEmail(email);
				result.setPassword(password);
				result.setGender(gender);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
