package model;

public class Member {
	
	private String fName;
	private String lName;
	private int id;
	private String email;
	private String phone;
	private String street;
	private String city;
	private String state;
	private String zip;
	private Trainer[] trainers;
	private Class[] classes;
	
	public Member(String fName, String lName, int id, String email, String phone, String street, String city,
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
	      
	  

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public void setZip(String zip) {
		this.zip = zip;
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
	
	
	
}
	