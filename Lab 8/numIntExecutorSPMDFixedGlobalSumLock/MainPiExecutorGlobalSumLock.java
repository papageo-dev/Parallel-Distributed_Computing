package numIntExecutorSPMDFixedGlobalSumLock;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.*;
import java.util.*;

class MainPiExecutorGlobalSumLock
{
	public static double sumPi; // Global shared counter
    public static Lock lock = new ReentrantLock(); // Global Lock variable
	
	public static void main(String[] args)
	{
		int numSteps = 0; // Number of steps/ Problem's size
	    int blockSize = 0; // Problem's size per block
	        
		// CMD input
		if (args.length != 2) {
	    	System.out.println("Usage: java ThreadGroupSqrt <number of steps> <block size>");
	    	System.exit(1);
		}

		try {
			numSteps = Integer.parseInt(args[0]);
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
                
        int numTasks = numSteps / blockSize;
        if ((numSteps % blockSize) != 0) numTasks++;
                
 
		// Get current time
        long start = System.currentTimeMillis();

		// Task creation 
		ExecutorSPMDFixedThread tasks[] = new ExecutorSPMDFixedThread[numTasks];
		
		ExecutorService executor = Executors.newFixedThreadPool(cores);
		
		// Thread creation and execution
		for (int i = 0; i < numTasks; i++) {
	    	tasks[i] = new ExecutorSPMDFixedThread(i, numSteps, blockSize);
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

        //Print Results
	    System.out.printf("sequential program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , sumPi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(sumPi - Math.PI));

	}
}