package myPiCalcLocalGetMain;

/* 
 *	Pi calculation, using a get() method or reference to thread's local variable to collect threads' results.
 *  This is NOT a "correct" solution, because is based on Java's garbage collector speed.
 *  It does NOT guarantee that the thread's calculated value will be returned correctly to main(), after join().
 */

public class PiCalcLocalGetMain {

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
		for (int i = 0; i < numThreads; i++) 
		{
			threads[i] = new PiSumThread(i, numThreads, numSteps);
			threads[i].start();
		}

	    // Initialize variable to collect total Pi result
        double piSum = 0.0;      
        
		// Wait for threads to terminate and collect result
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
				// Add thread's result to total result(piSum)
				// 1st method - Reference to local variable mySum
				// sum = sum + threads[i].mySum;
				// 2nd method - Use method getSum() (OOP)
				piSum += threads[i].getSum();
           		} catch (InterruptedException e) {}
		}

		
		// Get current time and calculate elapsed time
        long elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("time in ms = "+ elapsedTimeMillis);

        // Print results
        System.out.printf("sequential program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , piSum);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(piSum - Math.PI));
        System.out.printf("time to compute = %f seconds\n", (double) (elapsedTimeMillis - start) / 1000);

	}

}
