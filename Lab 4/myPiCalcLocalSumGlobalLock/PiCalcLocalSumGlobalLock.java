package myPiCalcLocalSumGlobalLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* 
 * Pi calculation, using global variable for total result and global Lock variable.
 * This IS an efficient solution/method, because using one "lock()"/"unlock()", per thread.
 */

public class PiCalcLocalSumGlobalLock {
		
	public static double sumPi = 0.0; // Global shared counter to calculate total result
	public static Lock lock = new ReentrantLock(); // Global Lock variable

	public static void main(String[] args) {
		
		int numSteps = 0;   // Problem size
		int numThreads = 0; // Total number of threads
		
		// CMD input
		if (args.length != 2) {
		    	System.out.println("Usage: java ThreadParArray <vector size> <number of threads>");
		    	System.exit(1);
		}

		try {
			numSteps = Integer.parseInt(args[0]);
		    numThreads = Integer.parseInt(args[1]);
		}
		catch (NumberFormatException nfe) {
		   	System.out.println("Integer argument expected");
		    System.exit(1);
		}
	    if (numThreads == 0) numThreads = Runtime.getRuntime().availableProcessors();


		// Get current time
	    long start = System.currentTimeMillis();

		// Create threads
	    PiSumThread threads[] = new PiSumThread[numThreads];
			
		// Thread execution   
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new PiSumThread(i, numThreads, numSteps);
			threads[i].start();
		}

		// Wait for threads to terminate and collect result        
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
	           	} catch (InterruptedException e) {}
		}

			
		// Get current time and calculate elapsed time
	    long elapsedTimeMillis = System.currentTimeMillis()-start;
	    System.out.println("time in ms = "+ elapsedTimeMillis);

	    // Print results
	    System.out.printf("sequential program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , sumPi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(sumPi - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (elapsedTimeMillis - start) / 1000);

	}
		
}
