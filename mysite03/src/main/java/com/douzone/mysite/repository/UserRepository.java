package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo vo) {
		sqlSession.insert("user.insert", vo);
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			String sql = "insert into user values(null, ?, ?, password(?), ?, now())";
//			pstmt = conn.prepareStatement(sql);
//			
//		
//			pstmt.setString(1, vo.getName());
//			pstmt.setString(2, vo.getEmail());
//			pstmt.setString(3, vo.getPassword());
//			pstmt.setString(4, vo.getGender());
//			
//			pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				
//				if(conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}


	public UserVo findByEmailAndPassword(String email, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("e", email);
		map.put("p", password);
		
		return sqlSession.selectOne("user.findByEmailAndPassword", map);
//		return sqlSession.selectOne("user.findByEmailAndPassword", vo);
		
	}

	public UserVo findByNo(long no) {
		return sqlSession.selectOne("user.findByNo", no);
	}

	public UserVo findByEmail(String email) {
		return sqlSession.selectOne("user.findByEmail", email);
	}
	
	public void update(UserVo vo) {
		sqlSession.selectOne("user.update", vo);
//		UserVo result = null;
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			conn = dataSource.getConnection();
//			
//			if("".equals(vo.getPassword())) {
//				String sql = "update user set name = ?,gender = ? where no = ?";
//				pstmt = conn.prepareStatement(sql);
//				
//			
//				pstmt.setString(1, vo.getName());
//				pstmt.setString(2, vo.getGender());
//				pstmt.setLong(3, vo.getNo());
//			} else {
//				String sql = "update user set name = ?, password = password(?), gender = ? where no = ?";
//				pstmt = conn.prepareStatement(sql);
//				
//			
//				pstmt.setString(1, vo.getName());
//				pstmt.setString(2, vo.getPassword());
//				pstmt.setString(3, vo.getGender());
//				pstmt.setLong(4, vo.getNo());
//			}
//			
//			
//			
//			
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				result = new UserVo();
//				
//				String name = rs.getString(1);
//				String email = rs.getString(2);
//				String password = rs.getString(3);
//				String gender = rs.getString(4);
//				
//				result.setName(name);
//				result.setEmail(email);
//				result.setPassword(password);
//				result.setGender(gender);
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("error:" + e);
//		} finally {
//			try {
//				if(rs != null) {
//					rs.close();
//				}
//				
//				if(conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
}
