//Jose Linares jlinare3@masonlive.gmu.edu G# G00855944

package model;

import java.io.BufferedWriter;
import java.util.regex.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ArrayList;

public class Member {
	
	private String fName; 	public String getfName() {return fName;}//First Name
	private String lName; 	public String getlName() {return lName;}//Last Name
	private int id; 		public int getId() {return id;};//MemberID
	private String email;	public String getEmail() {return email;}
	private String phone;	public String getPhone() {return phone;}//Phone number
	private String street;	public String getStreet() {return street;}//Street Address
	private String city;	public String getCity() {return city;}
	private String state;	public String getState() {return state;}
	private String zip;		public String getZip() {return zip;}
	private HashMap<String,Trainer> trainers;//HashMap of Trainers, Trainer ID serves as the key
	private ArrayList<Class> classes;//Array of Classes
	//private static String[] idArray = new String[500];//Array of Member IDs
	private static HashSet<Integer> usedIds = new HashSet<Integer>(500);
	
	//private static int idCount = 0;
	private static Pattern singleWordMatcher = Pattern.compile("\\w+");

	
	//This constructor is used when creating a new member
	public Member(String fName, String lName, String email, String phone, String street, String city,
			String state, String zip, HashMap<String,Trainer> trainers, ArrayList<Class> classes) {

		StringBuilder exceptions = new StringBuilder();
		this.id = generateID();
		if (!(setfName(fName) && setlName(lName)))
			{exceptions.append("name");}
		
		if(!setEmail(email))
		{	if(exceptions.length() > 0) {exceptions.append(", ");}
			exceptions.append("email");}
		
		if(!setPhone(phone))
		{	if(exceptions.length() > 0) {exceptions.append(", ");}
			exceptions.append("phone");}
		
		if(!(	setStreet(street) &&
				setCity(city) &&
				setZip(zip) ))
		{	if(exceptions.length() > 0) {exceptions.append(", ");}
			exceptions.append("full address");}
//		this.city = city;
		
		if(exceptions.length() > 0)
		{	throw new IllegalArgumentException("the following feild(s) are invalid\n" + exceptions);	}
		
		this.state = state;
//		this.zip = zip;
//		this.trainers = trainers;
//		this.classes = classes;
		//idArray[idCount] = this.id;
		//idCount++;
	}
	
	//This constructor is used when reading file contents and creating a member from  those contents
	protected Member(String fName, String lName, int id, String email, String phone, String street, String city,
			String state, String zip, HashMap<String,Trainer> trainers, ArrayList<Class> classes) {
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
		//idArray[idCount] = this.id;
		//idCount++;
		usedIds.add(id);
	}


	public boolean setfName(String fName) {
		String firstName = fName.trim();
		if(!singleWordMatcher.matcher(firstName).matches())
			{return false;}
		
		this.fName = firstName;
	    return true;
	}

	public boolean setlName(String lName) {
		String lastName = lName.trim();
		if(!singleWordMatcher.matcher(lastName).matches())
			{return false;}
		
	    this.lName = lastName;
	    return true;
	}
	      
	public void setId(int id) {
		if(!usedIds.contains(id))
			this.id = id;
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

	private static Pattern streetMatcher = Pattern.compile("\\d+ \\w+ \\w+");
	public boolean setStreet(String street) {
		String str = street.trim();
	      if(str.equals("")){
	         return false;
	      }
			//this would only store the streetname, no address or street type;
	      //limit! does not take in room number;
	      //	      else{
//	         for(int x = 0; x < str.length(); x++){
//	            if(Character.isLetter(str.charAt(x)) == false){
//	               return false;
//	            }
//	         }
//	      }

	      if(!streetMatcher.matcher(str).matches()) 
	      	{return false;}		
	      this.street = str;
	      return true;
	}

	public boolean setCity(String city) {
		String cit = city.trim();
	      if(cit.equals(""))
	      { return false;}
		
	      if(!singleWordMatcher.matcher(cit).matches())
			{return false;}
	      this.city = cit;
	      return true;
	}

	public void setState(String state) {	
		//String st = state.trim();
		//if(!singleWordMatcher.matcher(st).matches())
		//{return false;}
		this.state = state;
	}

	static Pattern zipMatcher = Pattern.compile("\\d{5}");
	public boolean setZip(String zCode) {
		  zip = zCode.trim();
		  if(!zipMatcher.matcher(zCode).matches())
				{return false;}
	      this.zip = zip;
	      return true;
	}

	public HashMap<String,Trainer> getTrainers() {
		return trainers;
	}

	public void setTrainers(HashMap<String,Trainer> trainers) {
		this.trainers = trainers;
	}

	public ArrayList<Class> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<Class> classes) {
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
	public int generateID(){
		//the runtime on this is potentially infinite
		int randomInt;
		boolean unique = true;
		//insanity check: if hashset is huge, give up
		if (usedIds.size() > 250000)
			{return -1; }
		Random random = new Random();
		do{
			randomInt = random.nextInt(999999) + 100000;
			
			if(usedIds.isEmpty()){
				unique = true;
			}else{
				unique = usedIds.contains(randomInt);
			}
		}while(unique == false);
		usedIds.add(randomInt);
		return randomInt;
		
	}
	
	//Reads a file and gets all contents into a HashMap of members.
	public static HashMap<String,Member> readFile(HashMap<String,Trainer> trainers, ArrayList<Class> classList){
		HashMap<String,Member> members = new HashMap<String,Member>();
		String filename = "members.txt";
		File file = new File(filename);
		String key;
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()){ //same issue as in Trainer
				String line = scan.nextLine();
				Scanner br = new Scanner(line);
					
				String id = br.next();
				String fName = br.next();
				String lName = br.next();
				String email = br.next();
				String phone = br.next();
				String street = br.next(); //THIS WILL NOT WORK
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
				ArrayList<Class> classes = new ArrayList<>();
				while(br.hasNext()){
					key = br.next();
					for(int i = 0; i < Class.getCount(); i++){
						if(key.equals(classList.get(i).getName())){
							classes.add(classList.get(i));
						}
					}
				}	
				Member member = new Member(fName,lName,Integer.parseInt(id),email,phone,street,city,state,zip,trainerMap,classes);
				members.put(id, member);
				br.close();
			}
			scan.close();
			
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
		if(this.classes.size() != 0){
			for(int i = 0; i < Class.getCount();i++){
				cString = cString + this.classes.get(i).getName() + " ";
			}
		}
		return id + " " + fName + " " + lName + " " + email
				+ " " + phone + " " + street + " " + city + " "
				+ state + " " + zip + " " + tString + " break "
				+ cString;
	}
	
	
}
	