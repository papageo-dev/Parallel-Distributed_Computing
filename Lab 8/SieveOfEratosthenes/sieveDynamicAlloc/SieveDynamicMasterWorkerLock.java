package sieveDynamicAlloc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SieveDynamicMasterWorkerLock {
	
	static int size; // Total calculation size
	static int nThreads; // Total threads number
	static int tasksAssigned = -1; // Shared variable for task assign 
	static Lock lock = new ReentrantLock(); // Shared Lock Variable

	public static void main(String[] args) {
		
		// Initialize number of threads and problem size
		size = 10000000; 
		nThreads = 8; 
 
		// Initialize prime[]
        boolean[] prime = new boolean[size+1];

		for (int i = 0; i < size+1; i++) {
			prime[i] = true; 
		}

		// Get current time 
        long start = System.currentTimeMillis();
        
		// Create Threads
		Thread[] threads = new Thread[nThreads];
		for (int i = 0; i < threads.length; ++i)
		{
			threads[i] = new Thread(new Worker(prime,i));
		}		

		// Threads execution
		for (int i = 0; i < threads.length; ++i)
		{
			threads[i].start();
		}

		// Wait for threads to terminate 
		for (int i = 0; i < threads.length; ++i)
		{
			try {
				threads[i].join();
			}
			catch (InterruptedException e) {
				System.err.println("this should not happen");
			}
		}
		
		// Get current time and calculate elapsed time
        long elapsedTimeMillis = System.currentTimeMillis()-start;

        // Count prime numbers
        int count = 0;
		for (int i = 0; i < size+1; i++) {
			if (prime[i] == true) {
				//System.out.println(i); 
				count++;
			}
		}
			
		// Print result and info
		System.out.println("\nNumber of primes = " + count); 
		System.out.println(" - Number of threads = " + nThreads);
		System.out.println(" - Time in ms = " + elapsedTimeMillis);
		
	}

	// Äistribution of work/tasks
	private static int getTask()
	{
	        lock.lock();
	        try {
	        	//Declaration of limit
	        	int limit = (int)Math.sqrt(size) + 1;
	        	// Return tasks until task value reaches limit bound
	        	if (++tasksAssigned < limit) 
	        		return tasksAssigned;
	        	else
	        		return -1;
	        } finally {
	        	lock.unlock() ;
	        }			
	}


	// Thread Class
	private static class Worker implements Runnable {

		private int myID;
		private boolean[] table;

		public Worker(boolean[] array, int myID)
		{
			this.myID = myID;
			table = array;
		}

		//Thread Code
		public void run()
		{
			int p;

			// Check if there is a task/element
			while ((p = getTask()) >= 0)
			{
				if (p>=2) {
					System.out.println("Thread " + myID + " is working on task " + p);
					// If prime[p] is not changed, then it is a prime
					if (table[p] == true) {
						// Update all multiples of p
						for (int i = p*p; i <= size; i += p) {
							table[i] = false;	
						}	
					}
				}   
			}
			System.out.println("worker " + myID + " done");
		}
	}
}