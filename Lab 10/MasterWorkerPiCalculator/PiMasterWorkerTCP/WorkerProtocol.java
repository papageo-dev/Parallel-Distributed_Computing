package PiMasterWorkerTCP;

import java.net.*;
import java.io.*;

public class WorkerProtocol {
	
	private int numWorkers;
        
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
		
		// Local variable, for each worker's result
	    double mySum = 0.0;
		
		double step = 1.0 / (double)range;
		
		for (int i=start; i<stop; i++) {
			double x = ((double)i+0.5)*step;
            mySum += 4.0/(1.0+x*x);
		}
		mySum = (mySum*step);
			
		String theOutput = String.valueOf(mySum);	
		
		return theOutput;
	}
}