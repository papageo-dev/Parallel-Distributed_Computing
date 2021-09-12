package arrayObjLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AddThread extends Thread {
	
	private Lock lock = new ReentrantLock();
	
	private int[] list;
    private Locklist[] locklist;
 
    public AddThread(int[] list, Locklist[] locklist) {
        this.list = list;
        this.locklist = locklist;
    }
 
    public void run() {
        
        int counter = 0;
        while (true) {
            delay(5000);
            // Lock CS
            lock.lock();
            try {
            	list[counter]++;
            	System.out.println("Add done");
            	counter = (counter+1)%5;
            } finally {
            	// Unlock CS
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
