package stringMatchObjLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyObj {
	
	private final Lock lock = new ReentrantLock(); // Local Lock variable
	int sharedCounter; // Object's shared counter
    
	// Constructor
    public MyObj (){
    	this.sharedCounter = 0;
    }

    // Method to increase shared counter's value
    public void plusOne () {
    	// Lock Critical Section. Only one thread can access and change "sharedCounter"
    	lock.lock();
    	try {
    		// Critical Section. Increase shared counter.
    		this.sharedCounter++;
    	} finally {
    		// Unlock Critical Section. Other threads will try to get lock and access the critical section.
        	lock.unlock();
    	}
    }

    // Return current value of shared counter
	public int getCounter() {
		return sharedCounter;
	}

}
