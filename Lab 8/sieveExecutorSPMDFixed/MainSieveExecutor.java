package sieveExecutorSPMDFixed;

import java.util.concurrent.Executors;
import java.util.concurrent.*;

class MainSieveExecutor {
	
	public static void main(String[] args) {
		
		int size = 0;
	    int blockSize = 0;
	        
	    // Initialize total size and block size from cmd
		if (args.length != 2) {
	    		System.out.println("Usage: java ThreadGroupSqrt <vector size> <block size>");
	    		System.exit(1);
		}

		try {
			size = Integer.parseInt(args[0]);
	    	blockSize = Integer.parseInt(args[1]);
		}
		catch (NumberFormatException nfe) {
	   		System.out.println("Integer argument expected");
	    		System.exit(1);
		}
		
		// Number of CPUs
		int cores = Runtime.getRuntime().availableProcessors();
		System.out.println("cores = " + cores);
		
        if (blockSize == 0) blockSize = 1;
               
        // Initialize total number of tasks
        int numTasks = size / blockSize;
        if ((size % blockSize) != 0) numTasks++;
                
		// Shared data structure(prime[]) initialization   
        boolean[] prime = new boolean[size+1];
		for (int i = 0; i < size+1; i++) {
			prime[i] = true; 
		}

		// for debugging 
		/*for(int i = 0; i < size; i++) 
			System.out.println(prime[i]);*/
  
		// Get current time
        long start = System.currentTimeMillis();

		// Task creation 
		ExecutorSPMDFixedThread tasks[] = new ExecutorSPMDFixedThread[numTasks];
		
		ExecutorService executor = Executors.newFixedThreadPool(cores);
		
		// Create threads/tasks and execute
        for (int i = 0; i < numTasks; i++) {
	    	tasks[i] = new ExecutorSPMDFixedThread(prime, i, size, blockSize);
            //System.out.println("A new task has been added in the queue: " + i);
            executor.execute(tasks[i]);
        }
        
       	try { 
       		executor.shutdown();
    	   	executor.awaitTermination(20, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
    			System.err.println("tasks interrupted");
		}
		finally {
			if (!executor.isTerminated()) {
        		System.err.println("cancel non-finished tasks");
    		}	
    		executor.shutdownNow();
       	}

		// Get current time and calculate elapsed time
        long elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("time in ms = "+ elapsedTimeMillis);

		// for debugging  
		/*for(int i = 0; i < size; i++) 
			System.out.println(prime[i]);*/
        
        // Count primes
        int count = 0;
		for (int i = 0; i < size+1; i++) {
			if (prime[i] == true) {
				//System.out.println(i); 
				count++;
			}
		}
			
		// Print total number of primes
		System.out.println("number of primes " + count); 

	}
}