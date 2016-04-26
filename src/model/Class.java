//Jose Linares jlinare3@masonlive.gmu.edu G# G00855944

package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Class {

	private String name;//Name of class
	private String description;//Description of the class
	private int capacity;//The max capacity of the class
	private int enrollment;//Current enrollment
	private ArrayList<String> members;//All IDs of students in the class
	private static int classCount = 0;//Total number of 

	public Class(String name, String description, int capacity, int enrollment, ArrayList<String> members) {
		this.name = name;
		this.description = description;
		this.capacity = capacity;
		this.enrollment = enrollment;
		this.members = members;
		classCount++;
	}
	
	public Class(String name, String description, int capacity, int enrollment) {
		this.name = name;
		this.description = description;
		this.capacity = capacity;
		this.enrollment = enrollment;
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

	public ArrayList<String> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}
	
	public static int getCount(){
		return classCount;
	}
	
	//Reads a file and returns contents inside an array of Class objects
	public static ArrayList<Class> readFile(){
		ArrayList<Class> classes = new ArrayList<>();
		String filename = "classes.txt";
		File file = new File(filename);
		int capacity, enrollment;
		try {
			Scanner scan = new Scanner(file);
			scan.useDelimiter(",");
			//String line = scan.nextLine();
			//Scanner br = new Scanner(line);
			while(scan.hasNextLine()){
				String name = scan.next();
				//System.out.println(name);
				String description = scan.next();
				//System.out.println(description);
				capacity = scan.nextInt();
				//System.out.println(capacity);
				enrollment = scan.nextInt();
				//System.out.println(enrollment);
				Class cl = new Class(name,description,capacity,enrollment);
				classes.add(cl);
			}
			scan.close();
			//br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return classes;
	}
	
	//Writes the contents inside a Class array into a file
	public static void writeFile(ArrayList<Class> classes){
		 BufferedWriter bw = null;
	        try{
	        	 bw = new BufferedWriter(new FileWriter("classes.txt", false));
	        	for(int i = 0;i < classes.size(); i++){
		            bw.write(classes.get(i).toString());
		            if(i != classes.size() - 1){
		            	bw.write(",");
		            }
		           // bw.newLine();
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
		//String mem = "";
		//for(int i = 0; i < members.size();i++){
		//	mem = mem + this.members.get(i) + " ";
		//}
		return name + "," + description + "," + capacity
				+ "," + enrollment;
	}
	
	
}
