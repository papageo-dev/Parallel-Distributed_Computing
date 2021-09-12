package arrayListConcurrentLock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RemoveThread extends Thread {
	
	private final Lock lock = new ReentrantLock();
	
	private ArrayList<Integer> list;
	 
    public RemoveThread(ArrayList<Integer> list) {
        this.list = list;
    }
 
    public void run() {
        
        while (true) {
    		delay(4000);
        	lock.lock();
        	try {

            	list.remove(1);
            	System.out.println("Remove done");	
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
