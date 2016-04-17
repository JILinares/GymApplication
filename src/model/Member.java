//Jose Linares jlinare3@masonlive.gmu.edu G# G00855944

package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;

public class Member {
	
	private String fName;//First Name
	private String lName;//Last Name
	private String id;//MemberID
	private String email;
	private String phone;//Phone number
	private String street;//Street Address
	private String city;
	private String state;
	private String zip;
	private HashMap<String,Trainer> trainers;//HashMap of Trainers, Trainer ID serves as the key
	private Class[] classes;//Array of Classes
	private static String[] idArray = new String[500];//Array of Member IDs
	private static int idCount = 0;
	
	//This constructor is used when creating a new member
	public Member(String fName, String lName, String email, String phone, String street, String city,
			String state, String zip, HashMap<String,Trainer> trainers, Class[] classes) {
		this.fName = fName;
		this.lName = lName;
		this.id = generateID();
		this.email = email;
		this.phone = phone;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.trainers = trainers;
		this.classes = classes;
		idArray[idCount] = this.id;
		idCount++;
	}
	
	//This constructor is used when reading file contents and creating a member from  thise contents
	public Member(String fName, String lName, String id, String email, String phone, String street, String city,
			String state, String zip, HashMap<String,Trainer> trainers, Class[] classes) {
		this.fName = fName;
		this.lName = lName;
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.trainers = trainers;
		this.classes = classes;
		idArray[idCount] = this.id;
		idCount++;
	}


	public String getfName() {
		return fName;
	}

	public boolean setfName(String fName) {
		String firstName = fName.trim();
	    if(firstName.equals("")){
	       return false;
	    }
	    else{
	       for(int x = 0; x < firstName.length(); x++){
	          if(Character.isLetter(firstName.charAt(x)) == false){
	             return false;
	          }
	       }
	    }
	    this.fName = firstName;
	    return true;
	}

	public String getlName() {
		return lName;
	}

	public boolean setlName(String lName) {
		String lastName = lName.trim();
	    if(lastName.equals("")){
	       return false;
	    }
	    else{
	       for(int x = 0; x < lastName.length(); x++){
	          if(Character.isLetter(lastName.charAt(x)) == false){
	             return false;
	          }
	       }
	    }
	    this.lName = lastName;
	    return true;
	}
	      
	  

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public boolean setEmail(String email) {
		String aEmail = email.trim();
	    boolean go = false;
	    for(int x = 0; x < aEmail.length(); x++){
	       if(aEmail.charAt(x)==('@')){
	          go = true;
	       }
	    }
	    if (go = true){
	       for(int x = 0; x <aEmail.length(); x++){
	          if(aEmail.charAt(x)==('.')){
	             this.email = aEmail;
	             return true;
	          }
	       }      
	    }
	    return go;
	}

	public String getPhone() {
		return phone;
	}

	public boolean setPhone(String phone) {
		String aPhone = phone.trim();
	    if(aPhone.length() != 12){
	       return false;
	    }
	    else {
	       if((aPhone.charAt(3)!=('-'))||(aPhone.charAt(7)!=('-'))){
	          return false;
	       }
	       else {
	          for(int x =0; x < 3; x++){
	             if(Character.isLetter(aPhone.charAt(x)) == true){
	                return false;
	             }
	          }
	          for(int x =4; x < 7; x++){
	             if(Character.isLetter(aPhone.charAt(x)) == true){
	                return false;
	             }
	          }
	          for(int x =8; x < 12; x++){
	             if(Character.isLetter(aPhone.charAt(x)) == true){
	                return false;
	             }
	          }
	       }
	    }
	    this.phone = phone;
	    return true;
	}

	public String getStreet() {
		return street;
	}

	public boolean setStreet(String street) {
		String str = street.trim();
	      if(str.equals("")){
	         return false;
	      }
	      else{
	         for(int x = 0; x < str.length(); x++){
	            if(Character.isLetter(str.charAt(x)) == false){
	               return false;
	            }
	         }
	      }
	      this.street = str;
	      return true;
	}

	public String getCity() {
		return city;
	}

	public boolean setCity(String city) {
		String cit = city.trim();
	      if(cit.equals("")){
	         return false;
	      }
	      else{
	         for(int x = 0; x < cit.length(); x++){
	            if(Character.isLetter(cit.charAt(x)) == false){
	               return false;
	            }
	         }
	      }
	      this.city = cit;
	      return true;
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

	public boolean setZip(String zCode) {
		String zip = zCode.trim();
	      if(zip.equals("") || zip.length() != 5){
	         return false;
	      }
	      else{
	         for(int x = 0; x < zip.length(); x++){
	            if(Character.isDigit(zip.charAt(x)) == false){
	               return false;
	            }
	         }
	      }
	      this.zip = zip;
	      return true;
	}

	public HashMap<String,Trainer> getTrainers() {
		return trainers;
	}

	public void setTrainers(HashMap<String,Trainer> trainers) {
		this.trainers = trainers;
	}

	public Class[] getClasses() {
		return classes;
	}

	public void setClasses(Class[] classes) {
		this.classes = classes;
	}
	
	public int setTrainer(Trainer trainer){
		int enroll = trainer.getEnrollment();
		trainer.setEnrollment(enroll++);
		return trainer.getEnrollment();
	}
	//Registers a member to a class. Increments the enrollment
	public int register(Class cl){
		int register = cl.getEnrollment();
		cl.setEnrollment(register++);
		return register;
		
	}
	
	//Generates an ID. Checks into an array if the ID already exist. If so, a new ID is generated until one is available.
	public String generateID(){
		int randomInt;
		boolean unique = true;
		Random random = new Random();
		do{
			randomInt = random.nextInt(999999) + 100000;
			if(idCount == 0){
				unique = true;
			}else{
				for(int i = 0; i < idCount; i++){
					if(randomInt == Integer.parseInt(idArray[i])){
						unique = false;
						i = idCount;
					}
				}
			}
		}while(unique == false);
		return String.valueOf(randomInt);
		
	}
	
	//Reads a file and gets all contents into a HashMap of members.
	public static HashMap<String,Member> readFile(HashMap<String,Trainer> trainers, Class[] classArray){
		HashMap<String,Member> members = new HashMap<String,Member>();
		String filename = "members.txt";
		File file = new File(filename);
		int cCount = 0;
		String key;
		try {
			Scanner scan = new Scanner(file);
			String line = scan.nextLine();
			Scanner br = new Scanner(line);
			while(scan.hasNextLine()){
				String id = br.next();
				String fName = br.next();
				String lName = br.next();
				String email = br.next();
				String phone = br.next();
				String street = br.next();
				String city = br.next();
				String state = br.next();
				String zip = br.next();
				HashMap<String,Trainer> trainerMap = new HashMap<String,Trainer>();
				while(br.hasNext() && !br.next().equals("break")){
					key = br.next();
					if(trainers.containsKey(key)){
						trainerMap.put(key, trainerMap.get(key));
					}
				}
				br.next();
				Class[] classes = new Class[10];
				while(br.hasNext()){
					key = br.next();
					for(int i = 0; i < Class.getCount(); i++){
						if(key.equals(classArray[i].getName())){
							classes[cCount] = classArray[i];
						}
					}
				}	
				Member member = new Member(fName,lName,id,email,phone,street,city,state,zip,trainerMap,classes);
				members.put(id, member);
			}
			scan.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return members;
	}
	
	//Write the contents inside a HashMap into a file
	public static void writeFile(HashMap<String,Member> members){
		 BufferedWriter bw = null;

	        try{
	        	bw = new BufferedWriter(new FileWriter("members.txt", false));
	        	Set<String> keys = members.keySet();
		        for(String i:keys){
		        	bw.write(members.get(i).toString());
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
		String tString = "";
		String cString = "";
		if(this.trainers.size() != 0){
			Set<String> keys = this.trainers.keySet();
			for(String i:keys){
				tString = tString + this.trainers.get(i).getId() + " ";
			}
		}
		if(classes.length != 0){
			for(int i = 0; i < Class.getCount();i++){
				cString = cString + this.classes[i].getName() + " ";
			}
		}
		return id + " " + fName + " " + lName + " " + email
				+ " " + phone + " " + street + " " + city + " "
				+ state + " " + zip + " " + tString + " break "
				+ cString;
	}
	
	
}
	