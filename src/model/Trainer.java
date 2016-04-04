package model;

public class Trainer {
	
	private String fName;
	private String lName;
	private String id;
	private int capacity;
	private int enrollment;
	private int[] members;
	
	public Trainer(String fName, String lName, String id, int capacity, int enrollment, int[] members) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.id = id;
		this.capacity = capacity;
		this.enrollment = enrollment;
		this.members = members;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(int enrollment) {
		this.enrollment = enrollment;
	}

	public int[] getMembers() {
		return members;
	}

	public void setMembers(int[] members) {
		this.members = members;
	}
	
	
	

}
