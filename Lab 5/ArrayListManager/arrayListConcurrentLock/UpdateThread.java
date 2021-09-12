package arrayListConcurrentLock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UpdateThread extends Thread {
	
	private final Lock lock = new ReentrantLock();
	
	private ArrayList<Integer> list;
	 
    public UpdateThread(ArrayList<Integer> list) {
        this.list = list;
    }
 
    public void run() {
        
        while (true) {
    		delay(3000);
        	lock.lock();
        	try {
            	list.set(1, list.get(1)+1);
            	System.out.println("Update done");	
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
