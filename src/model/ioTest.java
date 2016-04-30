package model;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.*;

//import javax.swing.JOptionPane;
import javax.swing.*;

public class ioTest {

	public static void main(String[] args) {
		HashMap<String,Trainer> t = Trainer.readFile();
		Set<String> K = t.keySet();
		System.out.print("Keys: ");
		for(String k : K) {System.out.printf("%3s ", k);} 
		System.out.println('\n');//double newline
		
		for(Map.Entry<String,Trainer> e : t.entrySet())
		{
			System.out.println(e.getValue());
		}
		
//		Set<String> keys = trainers.keySet();
		//TODO: remove "full" trainers from keyset
		//TODO: append remaining time slots for trainer to each key
		ArrayList<model.Class> c = new ArrayList<model.Class>();
		
		HashMap<Integer,Member> M = Member.readFile(t, c);
		
		System.out.println('\n');//double newline
		
		for(Map.Entry<Integer,Member> m :M.entrySet() )
		{
			System.out.println(m.getValue());
		}
		
		
		//TODO: if user did not cancel
		//Trainer t = trainers.get(selection);
	}

}
