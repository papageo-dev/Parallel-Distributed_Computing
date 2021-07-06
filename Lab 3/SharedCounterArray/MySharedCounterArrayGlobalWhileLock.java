package mySharedCounterArray_lab3_2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySharedCounterArrayGlobalWhileLock {
  
    static int end = 10000; // End of thread's calculation
    static int counter = 0; // Shared counter
    static int[] array = new int[end]; // Shared struct(array)
    static int numThreads = 4; // Total number of threads
    
    // Shared Lock variable
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

		// Create threads
        CounterThread threads[] = new CounterThread[numThreads];
		
		// Threads execution
        for (int i = 0; i < numThreads; i++) {
        	threads[i] = new CounterThread();
        	threads[i].start();
        }

		// Wait threads to terminate
        for (int i = 0; i < numThreads; i++) {
        	try {
        		threads[i].join();
        	}
        	catch (InterruptedException e) {}
        } 
        
        // Check array for errors
        check_array ();
     }
     
    // Method to check for errors in shared struct(array)
    static void check_array ()  {
    	
    	int i, errors = 0;

    	System.out.println ("Checking...");

        for (i = 0; i < end; i++) {
   	     	if (array[i] != 1) {
   	     		errors++;
   	     		System.out.printf("%d: %d should be 1\n", i, array[i]);
   	     	}         
        }
        System.out.println (errors+" errors.");
	}


    // Thread Class
    static class CounterThread extends Thread {
  	
       public CounterThread() {
       }
  	
       // Thread Code
       public void run() {
  
            while (true) {
            	
            	// Lock the critical section
            	lock.lock();
            	try {
            		if (counter >= end)  break;
                	array[counter]++; //Change shared struct's value
                	counter++; // Change shared counter's value
            	} finally {
            		// Unlock the critical section
            		lock.unlock();
            	}
            	
            	
            } 
       }            	
    }
    
}
  
