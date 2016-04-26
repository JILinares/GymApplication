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
		HashMap<String,Trainer> tH = (new File("trainers.txt").exists()) ?
				Trainer.readFile() : new HashMap<String,Trainer>();
		ArrayList<model.Class> cA = (new File("classes.txt").exists()) ? 
				model.Class.readFile() : new ArrayList<model.Class>();
		
		//should work even if passed empty map and array list
		HashMap<Integer,Member> mH = (new File("members.txt").exists()) ?
				Member.readFile(tH, cA) : new HashMap<Integer,Member>();
		
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

	static void viewMember(HashMap<Integer,Member> members)
	{	System.out.println("update path taken; " + members.size() + " members passed in");
		if(0==members.size())
			{JOptionPane.showMessageDialog(
					null, "No members to view", "Gym",
					JOptionPane.WARNING_MESSAGE);
				return;
			}
		
		
		//TODO: viewMemember dialog set, prompt for individual member
		Member selected = selectMember(members);
		InputMember im = new InputMember(selected,true);
		im.setTitle("Gym: View Member");
		im.setVisible(true);
		im.dispose();
	}
	
	//each of these update functions return FALSE upon user canceling input (pressing the exit button in the title bar))
	static boolean createMember(HashMap<Integer,Member> members)
	{	
		System.out.println("create path taken; " + members.size() + " members passed in");
		InputMember im = new InputMember();
		im.setTitle("Gym: Add Member");
		im.setVisible(true);
		im.dispose();
		//TODO: add new member if user did not cancel
		Member m = im.getMember();
		if(null!=m){members.put(m.getId(), m); return true;};
		return false;

	}
	
	//updates a selected member reference
	static boolean updateMember(HashMap<Integer,Member> members)
	{	
		System.out.println("update path taken; " + members.size() + " members passed in");
		//prompt for specific member
		Member selected = selectMember(members);
		
		//TODO: update member if user did not cancel
		if(null != selected){
			InputMember im = new InputMember(selected,false);
			im.setTitle("Gym: Update Member");
			im.setVisible(true);
			im.dispose();
			
			return true;
		}
		
		return false;
	}
	
	//Select a trainer or return Null if canceled
	/*ugly way of doing it; requires extracting key from string
	 and recreates a list that does not change every function call*/
	static Member selectMember(HashMap<Integer,Member> members)
	{
		//Set<Integer> keys = members.keySet();
		String[] options = getMemberOptions(members);
		String selection = (String) JOptionPane.showInputDialog(null, "Enter the member's ID number:", "Gym",
				JOptionPane.OK_OPTION | JOptionPane.QUESTION_MESSAGE, null, options, null);
		String key = selection.substring(0, selection.indexOf(' ',0));
		return members.get(Integer.parseInt(key));
	}

	
	//assign one or more classes
	static boolean assignClass(HashMap<Integer,Member> members, ArrayList<model.Class> classes)
	{	//TODO: assignClass dialog set
		Member selected = selectMember(members);
		//TODO: if null/cancel (left out for dummy demo purposes)
		ClassSelector ds = new ClassSelector(selected,classes);
		ds.setVisible(true);
		ds.dispose();
		
		System.out.println("class path taken");
		return false;
	}
	
	static boolean assignTrainer(HashMap<Integer,Member> members, HashMap<String,Trainer> trainers)
	{	//TODO: assignTrainer dialog set
		System.out.println("trainer path taken");

		Member m = selectMember(members);
		//TODO: if m != null
		
		//Set<String> keys = trainers.keySet();
		String[] options = getTrainerOptions(trainers);
		//TODO: remove "full" trainers from keyset
		
		String selection = (String) JOptionPane.showInputDialog(null, "Select a Trainer:", "Gym",
				JOptionPane.OK_OPTION | JOptionPane.QUESTION_MESSAGE, null, options, null);
		// if user did not cancel
		if (null==selection){return false;};
		
		String id = selection.substring(0, selection.indexOf(' ', 0));
		if(id==null){return false;}
		
		Trainer t = trainers.get(id);
		if(t!=null)
			{m.setTrainer(id,trainers); return true;}
		
		return false;
	}
	
	//produces formated strings from hashmap of trainer
	/*ugly way of doing it; requires extracting key from string
	*/
	static String[] getTrainerOptions(HashMap<String,Trainer> map)
	{
		String[] retval = new String[map.size()];
		Set<Map.Entry<String, Trainer>> set = map.entrySet();
		Iterator<Map.Entry<String, Trainer>> iterator = set.iterator();
		
		
		for(int i=0; iterator.hasNext(); i++)
		{	Map.Entry<String, Trainer> entry = iterator.next(); 
			StringBuilder line = new StringBuilder(entry.getKey());
			Trainer t = entry.getValue();
			line.append(
					String.format(" %s %s (%d enrolled of %d)", 
							t.getfName(),
							t.getlName(),
							t.getEnrollment(),
							t.getCapacity())
					);
			retval[i] = line.toString();
		}
		
		return retval;
	}
	
	static String[] getMemberOptions(HashMap<Integer,Member> map)
	{
		String[] retval = new String[map.size()];
		Set<Map.Entry<Integer,Member>> set = map.entrySet();
		Iterator<Map.Entry<Integer,Member>> iterator = set.iterator();
		
		
		for(int i=0; iterator.hasNext(); i++)
		{	Map.Entry<Integer, Member> entry = iterator.next(); 
			StringBuilder line = new StringBuilder(String.valueOf(entry.getKey()));
			Member m = entry.getValue();
			line.append(
			String.format(" %s %s", m.getfName(), m.getlName() ) );
			retval[i] = line.toString();
		}
		
		return retval;
	}
	
	
}
