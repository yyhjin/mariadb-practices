package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertTest02 {

	public static void main(String[] args) {
		boolean result = insertDepartment("QA팀");
		System.out.println(result?"성공":"실패");
	}

	private static boolean insertDepartment(String name) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.175:3306/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb123");
			
			// 3. SQL 준비
			String sql = "insert "
						+ "into dept "
						+ "values (null, ?)";
			pstmt = conn.prepareStatement(sql);
			
			// 4. 값 binding
			pstmt.setString(1, name);
						
			// 5. SQL 실행
			int count = pstmt.executeUpdate();  // 생성자에 sql을 넣으면 binding한 sql이 아니라, 바인딩 전의 데이터가 들어감
			
			// 6. 결과 처리
			result = count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				// 6. 자원정리
				// 사용했던 자원의 반대 순서대로 close
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
