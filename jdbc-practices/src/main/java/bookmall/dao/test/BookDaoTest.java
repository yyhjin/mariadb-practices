package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		
		// insert Test
		BookVo vo = new BookVo();
		vo.setTitle("트와일라잇");
		vo.setPrice(25000);
		vo.setCategoryNo(1);
		insertTest(vo);
		
		vo.setTitle("Do it! 점프 투 자바");
		vo.setPrice(18900);
		vo.setCategoryNo(2);
		insertTest(vo);
		
		vo.setTitle("새롭게 읽는 서양미술사");
		vo.setPrice(27000);
		vo.setCategoryNo(3);
		insertTest(vo);
		
		// select Test
		findAllTest();

	}

	private static void findAllTest() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertTest(BookVo vo) {
//		MemberDao dao = new MemberDao()
//				.insert(vo);
//				.insert(bookVo);
		
		new BookDao().insert(vo);
		
	}

}
