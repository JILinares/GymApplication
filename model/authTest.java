package model;

public class authTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String current = System.getProperty("user.dir");
		//System.out.println(current);
		AuthLogin logins[] = AuthLogin.readFile();
		AuthLogin success = AuthLogin.checkLogin(logins, "Testuser1", "TestUser1");
		if (success != null)
			{System.out.println(success.getUsername() + " is a valid login");}
	}

}
