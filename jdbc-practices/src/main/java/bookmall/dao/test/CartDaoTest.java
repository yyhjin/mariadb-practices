package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		
		// insert Test
		CartVo vo = new CartVo();
		vo.setMemberNo(1);
		vo.setBookNo(1);
		vo.setCount(2);
		insertTest(vo);
				
		vo.setMemberNo(2);
		vo.setBookNo(2);
		vo.setCount(1);
		insertTest(vo);
		
		// select Test
		findAllTest();

	}

	private static void findAllTest() {
		List<CartVo> list = new CartDao().findAll();
		for(CartVo vo : list) {
			BookVo bookVo = new BookDao().findByNo(vo.getBookNo());
			vo.setTitle(bookVo.getTitle());
			vo.setPrice(bookVo.getPrice()*vo.getCount());
			System.out.println(vo);
		}
	}

	private static void insertTest(CartVo vo) {
		new CartDao().insert(vo);		
	}

}
