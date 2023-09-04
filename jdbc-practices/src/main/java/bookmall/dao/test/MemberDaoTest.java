package bookmall.dao.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {

	public static void main(String[] args) {
		
		// insert Test
		MemberVo vo = new MemberVo();
		vo.setName("둘리");
		vo.setPhone("010-0000-0000");
		vo.setEmail("enffl@gmail.com");
		vo.setPassword("enffl");
		insertTest(vo);
		
		vo.setName("또치");
		vo.setPhone("010-1111-1111");
		vo.setEmail("Ehcl@gmail.com");
		vo.setPassword("Ehcl");
		insertTest(vo);
		
		// select Test
		findAllTest();

	}

	private static void findAllTest() {
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void insertTest(MemberVo vo) {
//		MemberDao dao = new MemberDao()
//				.insert(vo);
//				.insert(bookVo);
		
		new MemberDao().insert(vo);
		
	}

}
