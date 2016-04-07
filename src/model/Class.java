//Jose Linares jlinare3@masonlive.gmu.edu G# G00855944

package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Class {

	private String name;
	private String description;
	private int capacity;
	private int enrollment;
	private String[] members;
	private static int classCount = 0;

	public Class(String name, String description, int capacity, int enrollment, String[] members) {
		this.name = name;
		this.description = description;
		this.capacity = capacity;
		this.enrollment = enrollment;
		this.members = members;
		classCount++;
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
	
	public static int getCount(){
		return classCount;
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
	
	public static void writeFile(Class[] classes){
		 BufferedWriter bw = null;
	        try{
	        	 bw = new BufferedWriter(new FileWriter("classes.txt", false));
	        	for(int i = 0;classes[i] != null; i++){
		            bw.write(classes[i].toString());
		            bw.newLine();
	        	}
	        }catch(FileNotFoundException e){
	            System.out.println("File can not be created");
	        } catch (IOException ex) {
	            System.out.println("Error in Input/Output");
	        }finally{
	            try {
	                bw.close();
	            } catch (IOException ex) {
	        }
	    }
	}

	@Override
	public String toString() {
		String mem = "";
		for(int i = 0; i < capacity;i++){
			mem = mem + this.members[i] + " ";
		}
		return name + " " + description + " " + capacity
				+ " " + enrollment + " " + mem;
	}
	
	
}
