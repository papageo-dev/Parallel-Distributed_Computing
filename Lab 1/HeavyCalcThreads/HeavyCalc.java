package HeavyCalcThreads;


public class HeavyCalc {

	public static void main(String[] args) {
		
        int n = 5; // Number of threads
        Thread[] threads = new Thread[n]; // Threads' array

        // Create and start threads
        for (int i = 0; i < n; ++i) {
            System.out.println("In main: create and start thread " + i);
            threads[i] = new ThreadCalc(i, n); // Arguments: 1. ThreadID, 2. Total number of threads
        }
        
        // Wait all threads to terminate
        for (int i = 0; i < n; ++i) {
            try {
                threads[i].join();
            }
            catch (InterruptedException e) {
                System.err.println("this should not happen...");
            }
        }
        // Info message
        System.out.println("In main: threads all done!");
    }
}


class ThreadCalc extends Thread {

    // Variables
    private int myID;
    private int totalThreads;

    // Constructor
    public ThreadCalc(int myID, int totalThreads) {
        this.myID = myID;
        this.totalThreads = totalThreads;
    }

    // Thread code
    public void run() {
    	
        System.out.println("Hello from thread " + myID + " out of " + totalThreads);
        
		// Each thread's calculation
        heavyCalc();
        
		// Exit message
        System.out.println("Thread " + myID + " exits");
		
		/* Tests (with 4 physical cores):
		 *		Threads = 4 - Time = ~10s
         * 		Threads = 10 - Time = ~25s
         *      Threads = 30 - Time = ~1m */
    } 
    
	// Method to calculate the sqrt of a number(x)
    private void heavyCalc() {
    	
    	 for (long j=0; j<100000; j++)
        	 for (long i=0; i<100000; i++) {
        		 double x = ((double)i);
        		 x = Math.sqrt(4.0/(1.0+x*x));
        	 }
    	 }
   
	}
