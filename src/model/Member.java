//Jose Linares jlinare3@masonlive.gmu.edu G# G00855944

package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Member {
	
	private String fName;
	private String lName;
	private String id;
	private String email;
	private String phone;
	private String street;
	private String city;
	private String state;
	private String zip;
	private Trainer[] trainers;
	private Class[] classes;
	private static String[] idArray = new String[500];
	private static int idCount = 0;
	
	public Member(String fName, String lName, String email, String phone, String street, String city,
			String state, String zip, Trainer[] trainers, Class[] classes) {
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
	
	
	public Member(String fName, String lName, String id, String email, String phone, String street, String city,
			String state, String zip, Trainer[] trainers, Class[] classes) {
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

	public Trainer[] getTrainers() {
		return trainers;
	}

	public void setTrainers(Trainer[] trainers) {
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
	
	public int register(Class cl){
		int register = cl.getEnrollment();
		cl.setEnrollment(register++);
		return register;
		
	}
	
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
	
	public static Member[] readFile(Trainer[] trainerArray, Class[] classArray){
		Member[] members = new Member[500];
		String filename = "members.txt";
		File file = new File(filename);
		int tCount = 0;
		int cCount = 0;
		int aCount = 0;
		String temp;
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
				Trainer[] trainers  = new Trainer[10];
				while(br.hasNext() && br.next() != "break"){
					temp = br.next();
					for(int i = 0; i < trainerArray[0].getCount(); i++){
						if(temp.equals(trainerArray[i].getId())){
							trainers[tCount] = trainerArray[i];
							tCount++;
						}
					}
				}
				String brk = br.next();
				Class[] classes = new Class[10];
				while(br.hasNext()){
					temp = br.next();
					for(int i = 0; i < classArray[0].getCount(); i++){
						if(temp.equals(classArray[i].getName())){
							classes[cCount] = classArray[i];
							tCount++;
						}
					}
				}	
				Member member = new Member(fName,lName,id,email,phone,street,city,state,zip,trainers,classes);
				members[aCount] = member;
				aCount++;
			}
			scan.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return members;
	}
	
	public static void writeFile(Member[] members){
		 BufferedWriter bw = null;
	        try{
	        	 bw = new BufferedWriter(new FileWriter("members.txt", false));
	        	for(int i = 0;members[i] != null; i++){
		            bw.write(members[i].toString());
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
		if(trainers.length != 0){
			for(int i = 0; i < trainers[0].getCount();i++){
				tString = tString + this.trainers[i].getId() + " ";
			}
		}
		if(classes.length != 0){
			for(int i = 0; i < classes[0].getCount();i++){
				cString = cString + this.classes[i].getName() + " ";
			}
		}
		return id + " " + fName + " " + lName + " " + email
				+ " " + phone + " " + street + " " + city + " "
				+ state + " " + zip + " " + tString + " break "
				+ cString;
	}
	
	
}
	