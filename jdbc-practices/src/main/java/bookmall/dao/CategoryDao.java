package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CategoryVo;

public class CategoryDao {

	public List<CategoryVo> findAll() {
		List<CategoryVo> result = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.175:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");

			stmt = conn.createStatement();
			//3. SQL 준비
			String sql =
					"select name" +
					" from category";
			
			//5. SQL 실행
			rs = stmt.executeQuery(sql);
			
			// 5. 결과 처리
			while(rs.next()) {
				String name = rs.getString(1);
				
				CategoryVo vo = new CategoryVo();
				vo.setName(name);
				
				result.add(vo);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		} catch (SQLException e) {
			System.out.println("CategoryDao Select Error: " + e);
		} finally {
			try {
				// 6. 자원정리
				// 사용했던 자원의 반대 순서대로 close
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}

	public boolean insert(CategoryVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.175:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
			
			//3. SQL 준비
			String sql =
					"insert into category(name)" +
					" values(?)";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, vo.getName());
				
			//5. SQL 실행
			int count = pstmt.executeUpdate();
			
			// 5. 결과 처리
			result = count==1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		} catch (SQLException e) {
			System.out.println("CategoryDao Insert Error: " + e);
		} finally {
			try {
				// 6. 자원정리
				// 사용했던 자원의 반대 순서대로 close
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
}
