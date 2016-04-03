package model;

public class Class {

	private String name;
	private String description;
	private int capacity;
	private int[] members;

	public Class(String name, String description, int capacity, int[] members) {
		this.name = name;
		this.description = description;
		this.capacity = capacity;
		this.members = members;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
