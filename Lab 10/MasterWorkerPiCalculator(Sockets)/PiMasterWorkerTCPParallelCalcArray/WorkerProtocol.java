package PiMasterWorkerTCPParallelCalcArray;

import java.net.*;
import java.io.*;

public class WorkerProtocol {
	
	private int numWorkers;
	private int numThreads = Runtime.getRuntime().availableProcessors();
	private int numSteps = 10000;
        
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
		
		// Initialize struct(array) for threads' calculations
		double[] tsum = new double[numThreads];
		for(int i = 0; i < numThreads; i++)
			tsum[i] = 0.0;
		
		// create threads
		ThreadParallerCalc threads[] = new ThreadParallerCalc[numThreads];
		
		// thread execution   
		for (int i = 0; i < numThreads; i++) {
			threads[i] = new ThreadParallerCalc(i, numThreads, tsum, numSteps, start, stop, block);
			threads[i].start();
		}

		// wait for threads to terminate            
		for (int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {}
		}

		// Pi total result
		double pi = 0.0;
		// Collect threads' results
		for (int i = 0; i < numThreads; i++) {
			//System.out.println(tsum[i]);
			pi = pi + tsum[i];
		}
			
		String theOutput = String.valueOf(pi);	
		
		return theOutput;
	}
}