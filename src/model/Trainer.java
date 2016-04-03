package model;

public class Trainer {
	
	private String fName;
	private String lName;
	private int id;
	private int capacity;
	private int[] members;
	
	public Trainer(String fName, String lName, int id, int capacity, int[] members) {
		this.fName = fName;
		this.lName = lName;
		this.id = id;
		this.capacity = capacity;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int[] getMembers() {
		return members;
	}

	public void setMembers(int[] members) {
		this.members = members;
	}
	
	
	
	

}
