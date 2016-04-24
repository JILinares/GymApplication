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
		//InputMember im = new InputMember();
		JDialog[] dR = {dl,mm}; //References to all dialogues
			//May have been redundant, as login and main menu are the only persistent dialogs
		
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
		
		//TODO: viewMemember dialog set, prompt for individual member
		Member selected = selectMember(members);
		InputMember im = new InputMember(selected,true);
		im.setTitle("Gym: View Member");
		im.setVisible(true);
		im.dispose();
	}
	
	//each of these update functions return FALSE upon user canceling input (pressing the exit button in the title bar))
	static boolean createMember(HashMap<String,Member> members)
	{	
		System.out.println("create path taken; " + members.size() + " members passed in");
		InputMember im = new InputMember();
		im.setTitle("Gym: Add Member");
		im.setVisible(true);
		im.dispose();
		//TODO: add new member if user did not cancel
		return false;

	}
	
	static boolean updateMember(HashMap<String,Member> members)
	{	
		System.out.println("update path taken; " + members.size() + " members passed in");
		//TODO: prompt for specific member
		Member selected = selectMember(members);
		
		//TODO: update member if user did not cancel
		InputMember im = new InputMember(selected,false);
		im.setTitle("Gym: Update Member");
		im.setVisible(true);
		im.dispose();
		//TODO: update key and rehash
		
		return false;
	}
	
	//Select a trainer or return Null if canceled
	static Member selectMember(HashMap<String,Member> members)
	{
		Set<String> keys = members.keySet();
		String selection = (String) JOptionPane.showInputDialog(null, "Enter the member's ID number:", "Gym",
				JOptionPane.OK_OPTION | JOptionPane.QUESTION_MESSAGE, null, keys.toArray(new String[0]), null);
		return members.get(selection);
	}

	
	//assign one or more classes
	static boolean assignClass(HashMap<String,Member> members, ArrayList<model.Class> classes)
	{	//TODO: assignClass dialog set
		Member selected = selectMember(members);
		//TODO: if null/cancel (left out for dummy demo purposes)
		ClassSelector ds = new ClassSelector(selected,classes);
		ds.setVisible(true);
		ds.dispose();
		
		System.out.println("class path taken");
		return false;
	}
	
	static boolean assignTrainer(HashMap<String,Member> members, HashMap<String,Trainer> trainers)
	{	//TODO: assignTrainer dialog set
		
		Member m = selectMember(members);
		//TODO: if m != null
		
		Set<String> keys = members.keySet();
		//TODO: remove "full" trainers from keyset
		//TODO: append remaining time slots for trainer to each key
		
		String selection = (String) JOptionPane.showInputDialog(null, "Select a Trainer:", "Gym",
				JOptionPane.OK_OPTION | JOptionPane.QUESTION_MESSAGE, null, keys.toArray(new String[0]), null);
		//TODO: if user did not cancel
		Trainer t = trainers.get(selection);
		//m.setTrainer(t);
		
		System.out.println("trainer path taken");
		return false;
	}
}
