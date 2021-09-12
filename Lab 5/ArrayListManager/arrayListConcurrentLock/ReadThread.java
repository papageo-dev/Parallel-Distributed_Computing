package arrayListConcurrentLock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadThread extends Thread {
	
	 private final Lock lock = new ReentrantLock();
	
	 private ArrayList<Integer> list;
	 
	    public ReadThread(ArrayList<Integer> list) {
	        this.list = list;
	    }
	 
	    public void run() {
	 
	        while (true) {
        		delay(100);
	        	lock.lock();
	        	try {
	        		String output = "\n Current ArrayList :";
		            // must be in synchronized block 
		            Iterator<Integer> iterator = list.iterator(); 
		            Integer next;
		            while (iterator.hasNext()) {
		            	next = iterator.next();
		            	output += " " + next;
		            	//delay(100);
		            }	
		            System.out.println(output);
	        	} finally {
	        		lock.unlock();
	        	} 
	        }
	    }	
	    
	    public static void delay (int d) {
		  	try {
				Thread.sleep((int)(Math.random()*d));
			} catch (InterruptedException e) { }
		}
} 

