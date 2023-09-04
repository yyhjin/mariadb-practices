package hr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hr.dao.vo.EmployeesVo;

public class EmployeesDao {

	public List<EmployeesVo> findByName(String keyword) {
		List<EmployeesVo> result = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC Driver Class 로딩
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기
			String url = "jdbc:mariadb://192.168.0.175:3306/employees?charset=utf8";
			conn = DriverManager.getConnection(url, "hr", "hr");
			
			// 3. Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4. SQL 실행
			String sql = "select emp_no, first_name, last_name"
						+ " from employees"
						+ " where first_name like '%" + keyword + "%'"
						+ " and last_name like '%" + keyword + "%'";
			
			// ResultSet은 select일 때만 사용 가능
			rs = stmt.executeQuery(sql);
			
			// 5. 결과 처리
			while(rs.next()) {
				Long empNo = rs.getLong(1);  // 첫번째 컬럼의 값
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				
				EmployeesVo vo = new EmployeesVo();
				vo.setEmpNo(empNo);
				vo.setFirstName(firstName);
				vo.setLastName(lastName);
				
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
	
}
