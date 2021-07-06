package mySharedCounterArray_lab3_2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class MySharedCounterArrayGlobalSharedLockInner {
  
    static int end = 1000;
    static int[] array = new int[end]; // Shared struct(array)
    static int numThreads = 10; // Total number of threads
    
    // Global Lock variable
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
     static void check_array () {
    	 
    	 int i, errors = 0;

    	 System.out.println ("Checking...");

    	 for (i = 0; i < end; i++) {
    		 if (array[i] != numThreads*i) {
   	     	 	errors++;
   	     	 	System.out.printf("%d: %d should be %d\n", i, array[i], numThreads*i);
    		 }         
    	 }
    	 System.out.println (errors+" errors.");
     }


    // Inner Thread Class
    static class CounterThread extends Thread {
  	
	   // Constructor
       public CounterThread() {
       }
  	
	   // Thread code
       public void run() {
  
            for (int i = 0; i < end; i++) {
            	for (int j = 0; j < i; j++) {
            		// Critical Section
            		lock.lock(); // Lock shared struct "array[]". Only the current thread could change value of "array[]"
            		try {
            			array[i]++;	
            		} finally {
            			lock.unlock() ; // Unlock shared struct "array[]". Let other threads have access and change the value of "array[]"
            		}
            	} 
            }            	
      }
	  
   }
   
}
  
