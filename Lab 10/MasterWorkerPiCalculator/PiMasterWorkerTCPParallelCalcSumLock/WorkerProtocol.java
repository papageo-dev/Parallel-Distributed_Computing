package PiMasterWorkerTCPParallelCalcSumLock;

import java.net.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.io.*;

public class WorkerProtocol {
	
	private int numWorkers;
	
	private int numThreads = Runtime.getRuntime().availableProcessors();
	private int numSteps = 10000;
	// Create variable to store Pi value
	public static double pi = 0.0;
	// Create Lock variable
	public static Lock lock = new ReentrantLock();
        
    public WorkerProtocol(int num) {
    	numWorkers = num;
    }	

	public String compute(String theInput) throws IOException {
	
		String[] splited = theInput.split("\\s+");
		int range = Integer.parseInt(splited[0]);
		int id = Integer.parseInt(splited[1]);
		
		System.out.println("Worker "+ id +" calculates");
		
		int block = range / numWorkers;
		int start = id * block;
		int stop = start + block;
		if (id == numWorkers-1) stop = range;
		
		// create threads
		ThreadParallerCalcLock threads[] = new ThreadParallerCalcLock[numThreads];
				
		// thread execution   
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new ThreadParallerCalcLock(i, numThreads, numSteps, start, stop, block);
			threads[i].start();
		}

		// wait for threads to terminate            
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {}
		}
			
		String theOutput = String.valueOf(pi);	
		
		return theOutput;
	}
}