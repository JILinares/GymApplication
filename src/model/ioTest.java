package model;
import java.util.*;

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
		
	}

}
