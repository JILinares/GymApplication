//@Author Jonathan Eddy jeddy6@gmu.edu G00801804
package GUI;
import model.*;

import java.io.File;
import java.util.*;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class GymApp {
	
	public static void main(String[] args) {
		//for simplicity/quick typing, singleton classes are given acronyms
		Login dl = new Login(); //dialogue login
		MainMenu mm = new MainMenu(); //main menu		
		JDialog[] dR = {dl,mm}; //References to all dialogues
		
		AuthLogin l = null; //returned login from dialogue
		boolean modified = false; //the big state variable, upon which Members.txt is written
		
		//objects read from files
		HashMap<String,Trainer> tH = (new File("trainer.txt").exists()) ?
				Trainer.readFile() : new HashMap<String,Trainer>();
		ArrayList<model.Class> cA = (new File("classes.txt").exists()) ? 
				model.Class.readFile() : new ArrayList<model.Class>();
		
		//should work even if passed empty map and array list
		HashMap<String,Member> mH = (new File("members.txt").exists()) ?
				Member.readFile(tH, cA) : new HashMap<String,Member>();
		
		//validate login
		dl.setVisible(true); //modal dialogue, will not continue untile dispose is called
		l = dl.getLogin();
		
		//make clear who is logged in
		String title = "Gym (" + (null!=l ? l.getUsername() : "") + ")";	mm.setTitle(title);		
		
		while(null != l)// so long as there is a valid login, continue
		{	 
			mm.setVisible(true); 
			String action =	mm.getAction(); //either Create, View, Update, Class, Trainer or Exit
			if ("Exit".equals(action)) {break;} //or l=null, same difference
			
			//for compatibility with java 1.6, does NOT use switch on string
			switch(action.charAt(0)) //first character is decent enough'hash' 
			{
				case 'C':	if("Create".equals(action))
								{modified = createMember(mH);}
					else	if("Class".equals(action))
								{modified = assignClass(mH,cA);}
					break;
				case 'V':	viewMember(mH);
					break;
				case 'U':	modified = updateMember(mH);
					break;
				case 'T': 	modified = assignTrainer(mH,tH);
					break;
			}
		}
		
		for(JDialog d : dR){d.dispose();} //clean up resources
		
		if(modified)
		{	Member.writeFile(mH);	}
	}

	static void viewMember(HashMap<String,Member> members)
	{	System.out.println("update path taken; " + members.size() + " members passed in");
		if(0==members.size())
			{JOptionPane.showMessageDialog(
					null, "No members to view", "Gym",
					JOptionPane.WARNING_MESSAGE);
		}
		//TODO: viewMemember dialog
	}
	
	//each of these update functions return FALSE upon user canceling input (pressing the exit button in the title bar))
	static boolean createMember(HashMap<String,Member> members)
	{	//TODO: createMember dialog
		System.out.println("create path taken; " + members.size() + " members passed in");
		return false;
	}
	
	static boolean updateMember(HashMap<String,Member> members)
	{	//TODO: updateMember dialog set
		System.out.println("update path taken; " + members.size() + " members passed in");
		return false;
	}
	

	
	//assign one or more classes
	static boolean assignClass(HashMap<String,Member> members, ArrayList<model.Class> classes)
	{	//TODO: assignClass dialog set
		System.out.println("class path taken");
		return false;
	}
	
	static boolean assignTrainer(HashMap<String,Member> members, HashMap<String,Trainer> trainers)
	{	//TODO: assignTrainer dialog set
		System.out.println("trainer path taken");
		return false;
	}
}
