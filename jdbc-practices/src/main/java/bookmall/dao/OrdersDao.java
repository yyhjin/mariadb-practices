package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bookmall.vo.OrdersBookVo;
import bookmall.vo.OrdersVo;

public class OrdersDao {

	private Connection getConnection() throws SQLException {
		Connection conn = null;
			
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://192.168.0.175:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패 : " + e);
		} 

		return conn;
	}
	
	public List<OrdersVo> findAllOrders() {
		List<OrdersVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			String sql =
					"select orders_manage_no, name, email, address, total_price" +
					" from orders";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String ordersManageNo = rs.getString(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String address = rs.getString(4);
				int totalPrice = rs.getInt(5);
				
				OrdersVo vo = new OrdersVo();
				vo.setOrdersManageNo(ordersManageNo);
				vo.setName(name);
				vo.setEmail(email);
				vo.setAddress(address);
				vo.setTotalPrice(totalPrice);
				
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("OrdersDao Orders Select Error: " + e);
		} finally {
			try {
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
	
	public int findOrdersNoByManageNo(String manageNo) {
		OrdersVo result = new OrdersVo();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			String sql =
					"select no"
					+ " from orders"
					+ " where orders_manage_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, manageNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int orderNo = rs.getInt(1);	
				result.setNo(orderNo);
			}
			
		} catch (SQLException e) {
			System.out.println("OrdersDao Orders Select Error: " + e);
		} finally {
			try {
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
		
		return result.getNo();
	}
	
	public int findOrdersPriceByNo(int ordersNo) {
		OrdersVo result = new OrdersVo();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			String sql =
					"select total_price"
					+ " from orders"
					+ " where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ordersNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int totalPrice = rs.getInt(1);	
				result.setTotalPrice(totalPrice);
			}
			
		} catch (SQLException e) {
			System.out.println("OrdersDao OrdersPrice Select Error: " + e);
		} finally {
			try {
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
		
		return result.getTotalPrice();
	}
	
	public List<OrdersBookVo> findAllOrdersBook() {
		List<OrdersBookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql =
					"select book_no, title, count" +
							" from orders_book";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bookNo = rs.getInt(1);
				String title = rs.getString(2);
				int count = rs.getInt(3);
				
				OrdersBookVo vo = new OrdersBookVo();
				vo.setBookNo(bookNo);
				vo.setTitle(title);
				vo.setCount(count);
				
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("OrdersDao OrdersBook Select Error: " + e);
		} finally {
			try {
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

	public List<OrdersBookVo> findOrdersBookByOrderNo(int ordersNo) {
		List<OrdersBookVo> result = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql =
					"select price"
					+ " from orders_book"
					+ " where orders_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ordersNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int price = rs.getInt(1);
				
				OrdersBookVo vo = new OrdersBookVo();
				vo.setPrice(price);
				
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("OrdersDao OrdersBookByOrderNo Select Error: " + e);
		} finally {
			try {
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
	
	public int insertOrders(OrdersVo vo) {
		int resultOrderNo = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			// 고유 주문번호 : 날짜시간+주문자번호
			// 날짜 형식 : 20230811150000
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");			
			String ordersManageNo = sdf.format(new Date()) + vo.getmemberNo();
			vo.setOrdersManageNo(ordersManageNo);
			
			// total_price는 일단 비워두고 Insert
			String sql = "insert into orders(member_no, orders_manage_no, name, email, address)" +
					" values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getmemberNo());
			pstmt.setString(2, vo.getOrdersManageNo());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress());
				
			int count = pstmt.executeUpdate();
			
			if(count == 1) {
				resultOrderNo = findOrdersNoByManageNo(ordersManageNo);
			}
			
		} catch (SQLException e) {
			System.out.println("OrdersDao Insert Error: " + e);
		} finally {
			try {
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
		
		return resultOrderNo;
	}
	
	public boolean ordersBookInsert(OrdersBookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql =
					"insert into orders_book(orders_no, book_no, title, count, price)" +
					" values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getOrdersNo());
			pstmt.setInt(2, vo.getBookNo());
			pstmt.setString(3, vo.getTitle());
			pstmt.setInt(4, vo.getCount());
			pstmt.setInt(5, vo.getPrice());
				
			int count = pstmt.executeUpdate();
			
			result = count==1;
			
			if(count == 1) {
				updateTotalPrice(vo.getOrdersNo(), vo.getPrice());
			}
			
		} catch (SQLException e) {
			System.out.println("OrdersDao OrdersBook Insert Error: " + e);
		} finally {
			try {
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

	public boolean updateTotalPrice(int ordersNo, int price) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "update orders"
					+ " set total_price = total_price + ?"
					+ " where no = ?";
			pstmt = conn.prepareStatement(sql);
			
			// 추가된 price 더해서 업데이트
			pstmt.setInt(1, price);
			pstmt.setLong(2, ordersNo);
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
			
		} catch (SQLException e) {
			System.out.println("OrdersDao Update Error: " + e);
		} finally {
			try {
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
