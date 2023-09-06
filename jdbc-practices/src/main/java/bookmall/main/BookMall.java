package bookmall.main;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrdersDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrdersBookVo;
import bookmall.vo.OrdersVo;

public class BookMall {

	public static void main(String[] args) {		
		insertMember();
		insertCategory();
		insertBook();
		insertCart();
		int ordersNo = insertOrders();
		insertOrdersbook(ordersNo);

		System.out.println("## 회원");
		findAllMember();
		System.out.println();

		System.out.println("## 카테고리");
		findAllCategory();
		System.out.println();
		
		System.out.println("## 상품");
		findAllBook();
		System.out.println();
		
		System.out.println("## 카트");
		findAllCart();
		System.out.println();

		System.out.println("## 주문");
		findAllOrders();
		System.out.println();
		
		System.out.println("## 주문 도서");
		findAllOrdersbook();

		System.out.println();
	
	}

	private static void findAllOrdersbook() {
		List<OrdersBookVo> list = new OrdersDao().findAllOrdersBook();
		for(OrdersBookVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertOrdersbook(int ordersNo) {
		// #1
		OrdersBookVo ordersBookVo = new OrdersBookVo();
		ordersBookVo.setOrdersNo(ordersNo);
		ordersBookVo.setBookNo(3);
		ordersBookVo.setCount(2);
		
		BookVo bookVo = new BookDao().findByNo(ordersBookVo.getBookNo());
		ordersBookVo.setTitle(bookVo.getTitle());
		ordersBookVo.setPrice(bookVo.getPrice() * ordersBookVo.getCount());
		
		new OrdersDao().ordersBookInsert(ordersBookVo);
		
		// #2
		ordersBookVo.setOrdersNo(ordersNo);
		ordersBookVo.setBookNo(1);
		ordersBookVo.setCount(1);

		bookVo = new BookDao().findByNo(ordersBookVo.getBookNo());
		ordersBookVo.setTitle(bookVo.getTitle());
		ordersBookVo.setPrice(bookVo.getPrice() * ordersBookVo.getCount());
		
		new OrdersDao().ordersBookInsert(ordersBookVo);
	}

	private static void findAllOrders() {
		List<OrdersVo> list = new OrdersDao().findAllOrders();
		for(OrdersVo vo : list) {
			System.out.println(vo);
		}
	}

	private static int insertOrders() {
		OrdersVo ordersVo = new OrdersVo();
		ordersVo.setmemberNo(1);
		ordersVo.setName("둘리");
		ordersVo.setEmail("dolly@naver.com");
		ordersVo.setAddress("수원시 장안구");
		return new OrdersDao().insertOrders(ordersVo);
	}

	private static void findAllCart() {
		List<CartVo> list = new CartDao().findAll();
		for(CartVo vo : list) {
			BookVo bookVo = new BookDao().findByNo(vo.getBookNo());
			vo.setTitle(bookVo.getTitle());
			vo.setPrice(bookVo.getPrice()*vo.getCount());
			System.out.println(vo);
		}
	}

	private static void insertCart() {
		CartVo vo = new CartVo();
		vo.setMemberNo(1);
		vo.setBookNo(1);
		vo.setCount(2);
		new CartDao().insert(vo);
				
		vo.setMemberNo(2);
		vo.setBookNo(2);
		vo.setCount(1);
		new CartDao().insert(vo);
	}

	private static void findAllBook() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertBook() {
		BookVo vo = new BookVo();
		vo.setTitle("트와일라잇");
		vo.setPrice(25000);
		vo.setCategoryNo(1);
		new BookDao().insert(vo);
		
		vo.setTitle("Do it! 점프 투 자바");
		vo.setPrice(18900);
		vo.setCategoryNo(2);
		new BookDao().insert(vo);
		
		vo.setTitle("새롭게 읽는 서양미술사");
		vo.setPrice(27000);
		vo.setCategoryNo(3);
		new BookDao().insert(vo);
	}

	private static void findAllCategory() {
		List<CategoryVo> list = new CategoryDao().findAll();
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertCategory() {
		CategoryVo vo = new CategoryVo();
		vo.setName("소설");
		new CategoryDao().insert(vo);

		vo.setName("IT");
		new CategoryDao().insert(vo);
		
		vo.setName("예술");
		new CategoryDao().insert(vo);
	}

	private static void insertMember() {
		MemberVo vo = new MemberVo();
		vo.setName("둘리");
		vo.setPhone("010-0000-0000");
		vo.setEmail("enffl@gmail.com");
		vo.setPassword("enffl");
		new MemberDao().insert(vo);
		
		vo.setName("또치");
		vo.setPhone("010-1111-1111");
		vo.setEmail("Ehcl@gmail.com");
		vo.setPassword("Ehcl");
		new MemberDao().insert(vo);
	}

	private static void findAllMember() {
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo vo : list) {
			System.out.println(vo);
		}
	}
	
}
