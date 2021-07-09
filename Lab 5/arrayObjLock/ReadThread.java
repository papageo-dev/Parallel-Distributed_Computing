package arrayObjLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReadThread extends Thread {
	
	private Lock lock = new ReentrantLock();
	
	private int[] list;
    private Locklist[] locklist;
 
    public ReadThread(int[] list, Locklist[] locklist) {
        this.list = list;
        this.locklist = locklist;
    }
 
    public void run() {
 
        while (true) {
            delay(100);
            System.out.println("List :");
            for (int i=0; i<5; i++) {
            	// Lock CS
            	lock.lock();
            	try {
                	System.out.print(list[i]+" ");
                	delay(100);
            	} finally {
            		// Unlock CS
            		lock.unlock();
            	}
            }	
            System.out.println();	
        }
    }
    
    public static void delay (int d) {
	  	try {
			Thread.sleep((int)(Math.random()*d));
		} catch (InterruptedException e) { }
	}

}
