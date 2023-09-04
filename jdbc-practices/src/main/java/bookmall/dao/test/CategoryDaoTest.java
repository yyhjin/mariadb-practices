package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		
		// insert Test
		CategoryVo vo = new CategoryVo();
		vo.setName("소설");
		insertTest(vo);

		vo.setName("IT");
		insertTest(vo);
		
		vo.setName("예술");
		insertTest(vo);
		
		
		// select Test
		findAllTest();

	}

	private static void findAllTest() {
		List<CategoryVo> list = new CategoryDao().findAll();
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertTest(CategoryVo vo) {
//		MemberDao dao = new MemberDao()
//				.insert(vo);
//				.insert(bookVo);
		
		new CategoryDao().insert(vo);
		
	}

}
