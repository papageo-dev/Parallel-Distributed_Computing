package arrayListConcurrentSync;

import java.util.ArrayList;

public class UpdateThread extends Thread {
	
	private ArrayList<Integer> list;
	 
    public UpdateThread(ArrayList<Integer> list) {
        this.list = list;
    }
 
    public void run() {
        
        while (true) {
            delay(3000);
            synchronized(list) {
            	list.set(1, list.get(1)+1);
            	System.out.println("Update done");
            }	
        }
    }
    
    public static void delay (int d) {
	  	try {
			Thread.sleep((int)(Math.random()*d));
		} catch (InterruptedException e) { }
	}

}
