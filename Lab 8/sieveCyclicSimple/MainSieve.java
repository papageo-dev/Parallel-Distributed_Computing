package sieveCyclicSimple;

public class MainSieve {
	
	public static void main(String[] args) {
		
		int size = 0;
		int numThreads = 0;
		
		//Insert size and numThreads from cmd
        if (args.length != 2) {
        	System.out.println("Usage: java SieveOfEratosthenes <size> <number of threads>");
        	System.exit(1);
		}

		try { 
			size = Integer.parseInt(args[0]);
			numThreads = Integer.parseInt(args[1]);
		}
		catch (NumberFormatException nfe) {
	   		System.out.println("Integer argument expected");
	    	System.exit(1);
		}
		
        if (size <= 0) {
        	System.out.println("size should be positive integer");
        	System.exit(1);
		}
        if (numThreads == 0) numThreads = Runtime.getRuntime().availableProcessors();

        
        // Create prime[]
        boolean[] prime = new boolean[size+1];

        // Initialize prime[]
		for (int i = 0; i < size+1; i++) {
			prime[i] = true; 
		}
                       			
		// Get current time 
        long start = System.currentTimeMillis();
        
        // Create threads
        CyclicSimpleThread threads[] = new CyclicSimpleThread[numThreads];
        
        // Thread execution   
     	for (int i = 0; i < numThreads; i++) {
     		threads[i] = new CyclicSimpleThread(i, numThreads, size, prime);
     		threads[i].start();
     	}
     	
     	// Wait for threads to terminate            
     	for (int i = 0; i < numThreads; i++) {
     		try {
     			threads[i].join();
     			} catch (InterruptedException e) {}
     	}
               
        // Get current time and calculate elapsed time
        long elapsedTimeMillis = System.currentTimeMillis()-start;

        int count = 0;
		for (int i = 0; i < size+1; i++) {
			if (prime[i] == true) {
				//System.out.println(i); 
				count++;
			}
		}
			
		// Print result and info
		System.out.println("Number of primes = " + count); 
		System.out.println(" - Number of threads = " + numThreads);
		System.out.println(" - Time in ms = " + elapsedTimeMillis);
	}

}
