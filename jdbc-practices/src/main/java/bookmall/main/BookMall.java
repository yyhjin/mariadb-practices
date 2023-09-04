package bookmall.main;

import bookmall.dao.MemberDao;

public class BookMall {

	public static void main(String[] args) {
		MemberDao memberDao = new MemberDao();
		memberDao.insert(memberVo1);
		memberDao.insert(memberVo2);
		
		System.out.println("## 회원");

		System.out.println("## 카테고리");
		
		System.out.println("## 상품");
		
		System.out.println("## 카트");

		System.out.println("## 주문");
		
		System.out.println("## 주문 도서");
	
	}

}
