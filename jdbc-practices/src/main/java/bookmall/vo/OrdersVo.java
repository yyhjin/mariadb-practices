package bookmall.vo;

public class OrdersVo {
	private int no;
	private int memberNo;
	private String ordersManageNo;
	private String name;
	private String email;
	private String address;
	private int totalPrice;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getmemberNo() {
		return memberNo;
	}
	public void setmemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getOrdersManageNo() {
		return ordersManageNo;
	}
	public void setOrdersManageNo(String ordersManageNo) {
		this.ordersManageNo = ordersManageNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Override
	public String toString() {
		return "주문번호: " + ordersManageNo + ", 주문자 이름: " + name + ", 주문자 이메일: " + email
				+ ", 배송지: " + address + ", 결제금액: " + totalPrice;
	}
	
}
