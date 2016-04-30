package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

public class AuthLogin {
	private String username, pwd; //username and password
	public String getUsername(){return username;}
	protected AuthLogin(String username, String pwd)
		{this.username=username;
		 this.pwd=pwd;}
	
	//check an individual password
	protected boolean comparePwd(String password)
		{if(pwd==null || password == null) return false;
		 return pwd.equals(password);} //not comparing against hash for the time being
	
	public static AuthLogin[] readFile(){ 
		int authCount = 0;
		LinkedList<String> lines = new LinkedList<String>();
		String filename = "staffmembers.txt";
		File file = new File(filename);
		try{
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine())
				{lines.add(scan.nextLine()); authCount++;}
			
			scan.close();
		}catch(FileNotFoundException e)
			{e.printStackTrace();}
		
		if(lines.isEmpty()) return null;
		
		AuthLogin[] logins = new AuthLogin[authCount];
		String line=null;
		String un=null,pw=null;
		String parts[] = null;
		int lQuote,rQuote = 0; //left and right quote positions
		//for now, format of staffmembers.txt is two quoted strings separated by a comma.
		//todo: support escaped strings, enforce regex on username
		for(int i=0; i < authCount && !lines.isEmpty(); i++)
		{	line=lines.removeFirst();
			parts = line.split("\"\\s*,\\s*\"",2);
			if (parts.length < 2) {i--;continue;}
			
			lQuote = parts[0].indexOf('\"'); rQuote = parts[1].lastIndexOf('\"');
			
			if(lQuote == -1 || rQuote == -1) {i--;continue;}
			un = parts[0].substring(lQuote+1); pw=parts[1].substring(0,rQuote);
			logins[i]=new AuthLogin(un,pw);
		}
		
		return logins;
	}
	
	//for the time being, only does a linear search
	//stub function for eventual binary search
	public static boolean isSorted(AuthLogin[] logins){return false;}
	
	public static AuthLogin checkLogin(AuthLogin[] logins, 
			String username, String password)
	{	
			for(AuthLogin login : logins)
			{	if(login==null){break;}
				if(login.getUsername().equals(username))
				{if (login.comparePwd(password)) {return login;}}
			}
	
		return null;
	}
	
}
