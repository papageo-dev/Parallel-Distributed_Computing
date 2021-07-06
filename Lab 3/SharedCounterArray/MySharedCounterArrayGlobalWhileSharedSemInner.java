package mySharedCounterArray_lab3_2;

import java.util.concurrent.Semaphore;


public class MySharedCounterArrayGlobalWhileSharedSemInner {
  
	// Global variables
    static int end = 10;
    static int counter = 0; // Shared Counter
    static int[] array = new int[end]; // Shared struct(array)
    static int numThreads = 4;
    
    // Initialize semaphore
    static Semaphore sem = new Semaphore(1);

    public static void main(String[] args) {

    	// Create threads
        CounterThread threads[] = new CounterThread[numThreads];
	
        // Thread execution
        for (int i = 0; i < numThreads; i++) {
        	threads[i] = new CounterThread();
        	threads[i].start();
        }

        // Wait all threads to terminate
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
   	     	if (array[i] != 1) {
   	     		errors++;
   	        	System.out.printf("%d: %d should be 1\n", i, array[i]);
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
  
            while (true) {
            	
            	try {
            		sem.acquire(); /* Check if semaphore's value is > 0 (sem>0):
            		 				* True: sem=sem-1 -> Lock and run the code in critical section
            		 				* False: Wait for other thread to quit and try again to lock and run the code in critical section
            		 				*/
            		} catch (InterruptedException e) { }
            		// Critical Section
            		// Check counter's value
        			if (counter >= end) break;
                	array[counter]++;
                	counter++;
                	sem.release(); // Unlock the critical section (sem = sem + 1). Other thread can run the code in critical section
            } 
       }            	
    }
    
    
}
  
