package arrayListConcurrentSync;

import java.util.ArrayList;

public class RemoveThread extends Thread {
	
	private ArrayList<Integer> list;
	 
    public RemoveThread(ArrayList<Integer> list) {
        this.list = list;
    }
 
    public void run() {
        
        while (true) {
            delay(4000);
            synchronized(list) {
            	list.remove(1);
            	System.out.println("Remove done");
            }	
        }
    }
    
    public static void delay (int d) {
	  	try {
			Thread.sleep((int)(Math.random()*d));
		} catch (InterruptedException e) { }
	}

}
