package HeavyCalcThreads;


public class HeavyCalcAnonymousClass {

	public static void main(String[] args) {
		
        int n = 5; // Number of threads
        Thread[] threads = new Thread[n]; // Threads' array

        // Create and start threads
        for (int i = 0; i < n; ++i) {
            System.out.println("In main: create and start thread " + i);
            
            // Create anonymous Threads and execute
            threads[i] = new Thread() {
            	 public void run() {
            	        for (long j=0; j<100000; j++)
            	       	 for (long a=0; a<100000; a++) {
            	       		 double x = ((double)a);
            	       		 x = Math.sqrt(4.0/(1.0+x*x));
            	       	 }
            	    } 
            };
            threads[i].start(); 
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