package hr.dao.vo;

public class EmployeesVo {
	private Long empNo;
	private String birthDate;
	private String firstName;
	private String lastName;
	private String hireDate;
	private String gender;
	
	public Long getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Long empNo) {
		this.empNo = empNo;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Override
	public String toString() {
		return "EmployeesVo [empNo=" + empNo + ", birthDate=" + birthDate + ", firstName=" + firstName + ", lastName="
				+ lastName + ", hireDate=" + hireDate + ", gender=" + gender + ", getEmpNo()=" + getEmpNo()
				+ ", getBirthDate()=" + getBirthDate() + ", getFirstName()=" + getFirstName() + ", getLastName()="
				+ getLastName() + ", getHireDate()=" + getHireDate() + ", getGender()=" + getGender() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
}
