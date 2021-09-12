package arrayListConcurrentSync;

import java.util.ArrayList;
import java.util.Iterator;

public class ReadThread extends Thread {
	
	 private ArrayList<Integer> list;
	 
	    public ReadThread(ArrayList<Integer> list) {
	        this.list = list;
	    }
	 
	    public void run() {
	 
	        while (true) {
	            delay(100);
	            synchronized(list) { 
	            	String output = "\n Current ArrayList :";
	            	// must be in synchronized block 
	            	Iterator<Integer> iterator = list.iterator(); 
	            	Integer next;
	            	while (iterator.hasNext()) {
	            		next = iterator.next();
	            		output += " " + next;
	            		delay(100);
	            	}	
	            	System.out.println(output);
	            }	
	        }
	    }	
	    
	    public static void delay (int d) {
		  	try {
				Thread.sleep((int)(Math.random()*d));
			} catch (InterruptedException e) { }
		}
} 

