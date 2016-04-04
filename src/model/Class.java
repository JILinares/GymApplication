package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Class {

	private String name;
	private String description;
	private int capacity;
	private int enrollment;
	private String[] members;

	public Class(String name, String description, int capacity, int enrollment, String[] members) {
		this.name = name;
		this.description = description;
		this.capacity = capacity;
		this.enrollment = enrollment;
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

	public int getEnrollment() {
		return enrollment;
	}

	public void setEnrollment(int enrollment) {
		this.enrollment = enrollment;
	}

	public String[] getMembers() {
		return members;
	}

	public void setMembers(String[] members) {
		this.members = members;
	}
	
	public static Class[] readFile(){
		Class[] classes = new Class[15];
		String filename = "classes.txt";
		File file = new File(filename);
		int mCount = 0;
		int cCount = 0;
		try {
			Scanner scan = new Scanner(file);
			String line = scan.nextLine();
			Scanner br = new Scanner(line);
			while(scan.hasNextLine()){
				String name = br.next();
				String description = br.next();
				int capacity = br.nextInt();
				String[] members = new String[capacity];
				int enrollment = br.nextInt();
				while(br.hasNext()){
					members[mCount] = br.next();
					mCount++;
				}
				Class cl = new Class(name,description,capacity,enrollment,members);
				classes[cCount] = cl;
				cCount++;
			}
			scan.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return classes;
	}
	
	
}
