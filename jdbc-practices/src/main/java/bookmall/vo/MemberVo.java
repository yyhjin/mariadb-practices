package bookmall.vo;

public class MemberVo {
	private int no;
	private String name;
	private String phone;
	private String email;
	private String password;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "회원 이름: " + name + ", 전화번호: " + phone + ", 이메일: " + email + ", 비밀번호: "
				+ password;
	}
	
}
