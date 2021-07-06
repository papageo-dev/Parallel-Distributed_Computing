package HeavyCalcThreads;


/* This program creates a given number of threads and subThreads by condition */

public class ForkBomb {
	
public static void main(String[] args) {
		
        int n = 5; // Number of threads
        Thread[] threads = new Thread[n]; // Threads' array

        // Create and start threads
        for (int i = 0; i < n; ++i) {
            System.out.println("In main: create and start thread " + i);
            threads[i] = new ThreadCalc1(i, n); // Arguments: 1. ThreadID, 2. Total number of threads
            threads[i].start(); 
        }
        
        // Wait all threads to terminate
        for (int i=0; i<n; ++i) {
            try {
                threads[i].join();
            }
            catch (InterruptedException e) {
                System.err.println("Error in threads processing...");
            }
        }
        // Info message
        System.out.println("In main: All threads terminates!");
    }
}


class ThreadCalc1 extends Thread {

    // Variables
    private int myID;
    private int totalThreads;

    // Constructor
    public ThreadCalc1(int myID, int totalThreads) {
        this.myID = myID;
        this.totalThreads = totalThreads;
    }

    // Thread code
    public void run() {
    	
    	System.out.println("Hello from thread " + myID + " out of " + totalThreads);

        // If isn't the last thread, create a subThread
        if (myID != totalThreads) {
        	System.out.println("Thread " + myID + " is starting a subThread with ID: " + (myID+1));
            Thread subThread = new Thread(new ThreadCalc((myID+1), totalThreads));
            subThread.start();
            
			// Wait subThreads to terminate
            try {
  		  subThread.join();
  	      } 
            catch (InterruptedException e) {
          	  System.err.println("Error in subThreads processing...");
            }
            System.out.println("In run(): subThread " + (myID+1) + " of thead " + myID + " exits");
        } 
        else
        {
        	System.out.println("Thread " + myID + " exits"); 
        }
    }
    
}
    	
    
  