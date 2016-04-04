package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trainer {
	
	private String fName;
	private String lName;
	private String id;
	private int capacity;
	private int enrollment;
	private String[] members;
	
	public Trainer(String fName, String lName, String id, int capacity, int enrollment, String[] members) {
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

	public String[] getMembers() {
		return members;
	}

	public void setMembers(String[] members) {
		this.members = members;
	}
	
	public static Trainer[] readFile(){
		Trainer[] trainers = new Trainer[15];
		String filename = "trainers.txt";
		File file = new File(filename);
		int mCount = 0;
		int tCount = 0;
		try {
			Scanner scan = new Scanner(file);
			String line = scan.nextLine();
			Scanner br = new Scanner(line);
			while(scan.hasNextLine()){
				String id = br.next();
				String fName = br.next();
				String lName = br.next();
				int capacity = br.nextInt();
				String[] members = new String[capacity];
				int enrollment = br.nextInt();
				while(br.hasNext()){
					members[mCount] = br.next();
					mCount++;
				}
				Trainer trainer = new Trainer(fName,lName,id,capacity,enrollment,members);
				trainers[tCount] = trainer;
				tCount++;
			}
			scan.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return trainers;
	}
	
	

}
