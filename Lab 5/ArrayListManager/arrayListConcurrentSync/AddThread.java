package arrayListConcurrentSync;

import java.util.ArrayList;

public class AddThread extends Thread {
	
	private ArrayList<Integer> list;
	 
    public AddThread(ArrayList<Integer> list) {
        this.list = list;
    }
 
    public void run() {
       
        while (true) {
            delay(5000);
            synchronized(list) {
            	list.add(1);
            	System.out.println("Add done");
            }	
        }
    }
    
    public static void delay (int d) {
	  	try {
			Thread.sleep((int)(Math.random()*d));
		} catch (InterruptedException e) { }
	}

}
