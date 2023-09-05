package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.OrdersDao;
import bookmall.vo.BookVo;
import bookmall.vo.OrdersBookVo;
import bookmall.vo.OrdersVo;

public class OrdersDaoTest {

	public static void main(String[] args) {
		
		// insert Test
		/* 1. memberNo로 사용자 데이터 조회
		 * 2. 사용자 데이터 orderVo에 넣고 total = null로 insert, orderNo 반환
		 * 3. 반환받은 orderNo로 order_book 생성 (book과 join하여 price 계산)
		 * 4. 2에서 생성한 orderVo에 total값 수정
		 */
		
//		OrdersVo ordersVo = new OrdersVo();
//		ordersVo.setmemberNo(1);
//		ordersVo.setName("둘리");
//		ordersVo.setEmail("dolly@naver.com");
//		ordersVo.setAddress("수원시 장안구");
//		int ordersNo = insertOrdersTest(ordersVo);
		int ordersNo = 1;
		
		OrdersBookVo ordersBookVo = new OrdersBookVo();
		ordersBookVo.setOrdersNo(ordersNo);
		ordersBookVo.setBookNo(3);
		ordersBookVo.setCount(2);
		insertOrdersBookTest(ordersBookVo);
		
		ordersBookVo.setOrdersNo(ordersNo);
		ordersBookVo.setBookNo(1);
		ordersBookVo.setCount(1);
		insertOrdersBookTest(ordersBookVo);
		
		// select Test
		findAllOrdersTest();
		findAllOrdersBookTest();

	}

	private static void findAllOrdersTest() {
		List<OrdersVo> list = new OrdersDao().findAllOrders();
		for(OrdersVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void findAllOrdersBookTest() {
		List<OrdersBookVo> list = new OrdersDao().findAllOrdersBook();
		for(OrdersBookVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertOrdersBookTest(OrdersBookVo vo) {		
		BookVo bookVo = new BookDao().findByNo(vo.getBookNo());
		vo.setTitle(bookVo.getTitle());
		vo.setPrice(bookVo.getPrice() * vo.getCount());
		
		new OrdersDao().ordersBookInsert(vo);
	}

}
