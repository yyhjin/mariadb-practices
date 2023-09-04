package bookshop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bookshop.vo.BookVo;

public class MemberDao {

	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.175:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb123");
			

			stmt = conn.createStatement();
			//3. SQL 준비
			String sql =
					"select b.no, b.title, b.rent, a.name" +
					" from book b, author a" +
					" where b.author_no = a.no";
			
			//5. SQL 실행
			rs = stmt.executeQuery(sql);
			
			// 5. 결과 처리
			while(rs.next()) {
				int no = rs.getInt(1);
				String title = rs.getString(2);
				String rent = rs.getString(3);
				String authorName = rs.getString(4);
				
				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setRent(rent);
				vo.setAuthorName(authorName);
				
				result.add(vo);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
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

	public boolean updateRent(BookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.175:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb123");
			
			//3. SQL 준비
			String sql =
					"update book" +
					" set rent = ?" +
					" where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			//4. binding
			pstmt.setString(1, vo.getRent());
			pstmt.setInt(2, vo.getNo());
				
			//5. SQL 실행
			int count = pstmt.executeUpdate();
			
			// 5. 결과 처리
			result = count==1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
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
