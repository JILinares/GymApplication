//Jose Linares jlinare3@masonlive.gmu.edu G# G00855944

package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Trainer {
	
	private String fName;//First Name
	private String lName;//Last Name
	private String id;//Member ID
	private int capacity;//Trainer's capacity for students
	private int enrollment;//Trainer's student enrollment
	private String[] members;//IDs of all students
	private static int trainerCount = 0;//The Number of Trainers
	
	public Trainer(String fName, String lName, String id, int capacity, int enrollment, String[] members) {
		super();
		this.fName = fName;;
		this.lName = lName;
		this.id = id;
		this.capacity = capacity;
		this.enrollment = enrollment;
		this.members = members;
		trainerCount++;
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
	
	public boolean isFull(){return capacity == enrollment;}
	public boolean isEmpty(){return 0 == enrollment;}
	
	public boolean addMember(){
		if(isFull()){return false;}
			enrollment++;
		return true;
	}
	
	public void removeMember()
	{	if(!isEmpty()){enrollment--;}
	}
	
	public String[] getMembers() {
		return members;
	}

	public void setMembers(String[] members) {
		this.members = members;
	}
	
	public static int getCount(){
		return trainerCount;
	}
	
	//Reads file into a HashMap and then returns the HashMap.
	public static HashMap<String,Trainer> readFile(){
		HashMap<String,Trainer> trainers = new HashMap<String,Trainer>();
		String filename = "trainers.txt";
		File file = new File(filename);
//		int mCount = 0;
		try {
			Scanner scan = new Scanner(file);
			
			while(scan.hasNextLine()){
				//removed bug: INFINITE LOOP does not read next line within loop
				String line = scan.nextLine();
				Scanner br = new Scanner(line);
				String id = br.next();
				String fName = br.next();
				String lName = br.next();
				int capacity = br.nextInt();
//				String[] members = new String[capacity];
//				int enrollment = br.nextInt();
//				while(br.hasNext()){
//					members[mCount] = br.next();
//					mCount++;
//				}
				Trainer trainer = new Trainer(fName,lName,id,capacity,0,null);
				trainers.put(id, trainer);
				
				//line = scan.nextLine();
				br.close();
			}
			scan.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return trainers;
	}
	
	//Take a HashMap of Trainers and writes contents into a file
	//JPE+++unneeded
	
//	public static void writeFile(HashMap<String,Trainer> trainers){
//		 BufferedWriter bw = null;
//	        try{
//	        	 bw = new BufferedWriter(new FileWriter("trainers.txt", false));
//	        	 Set<String> keys = trainers.keySet();
//	        	for(String i:keys){
//		            bw.write(trainers.get(i).toString());
//		            bw.newLine();
//	        	}
//	        }catch(FileNotFoundException e){
//	            System.out.println("File can not be created");
//	        } catch (IOException ex) {
//	            System.out.println("Error in Input/Output");
//	        }finally{
//	            try {
//	                bw.close();
//	            } catch (IOException ex) {
//	        }
//	    }
//	}

	@Override
	public String toString() {
		String mem = "";
		for(int i = 0; i < this.enrollment;i++){
			mem = mem + this.members[i] + " ";
		}
		return id + " " + fName + " " + lName + " " + capacity
				+ " " + enrollment + " " + mem;
	}
	
	

}
