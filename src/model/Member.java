package model;

public class Member {
	
	private String fName;
	private String lName;
	private int id;
	private String email;
	private String phone;
	private String street;
	private String city;
	private String state;
	private String zip;
	private Member[] members;
	private Class[] classes;
	
	public Member(String fName, String lName, int id, String email, String phone, String street, String city,
			String state, String zip, Member[] members, Class[] classes) {
		this.fName = fName;
		this.lName = lName;
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.members = members;
		this.classes = classes;
	}
	
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Member[] getMembers() {
		return members;
	}
	public void setMembers(Member[] members) {
		this.members = members;
	}
	public Class[] getClasses() {
		return classes;
	}
	public void setClasses(Class[] classes) {
		this.classes = classes;
	}
	
	

}
