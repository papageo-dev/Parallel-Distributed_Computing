package mySharedMatVec_lab3_1;


public class MySharedMatVecInner {
	
	// Initialize shared counter
	static double sum = 0;

    public static void main(String[] args) {

    	int numThreads = 16; // Total number of threads
    	int size = 10000; // Problem size

    
    	// Initialize arrays
    	double[][] a = new double[size][size];
    	for(int i = 0; i < size; i++)
    		for(int j = 0; j < size; j++)
    			a[i][j] = 1;

    	double[] x = new double[size];
    	double[] y = new double[size];
    	for(int i = 0; i < size; i++) {
    		x[i] = 1;
    		y[i] = 0;
    	}

    	// Get current time
    	long start = System.currentTimeMillis();

    	// Create threads
    	CounterThread counterThreads[] = new CounterThread[numThreads];
	
    	// Thread execution 
    	for (int i = 0; i < numThreads; i++) {
    		counterThreads[i] = new CounterThread(i, numThreads, a, x, y, size);
    		// Arguments: 1. Thread ID, 2. Total number of threads, 3,4,5. Shared structs(arrays), 6. Problem size
    		counterThreads[i].start();
    	}
    	
		// Wait for threads to terminate 
    	for (int i = 0; i < numThreads; i++) {
    		try {
    			counterThreads[i].join();
    		}
    		catch (InterruptedException e) {}
    	} 
        
    	// Get current time and calculate elapsed time
    	long elapsedTimeMillis = System.currentTimeMillis()-start;
    	System.out.println("time in ms = " + elapsedTimeMillis);
		
		// Print total sum of all threads' calculations
    	System.out.println("Sum = " + sum); 
		
    }
    

    // Inner Thread class
    static class CounterThread extends Thread {
	  	
    	int threadID;
    	private double [][] table; 
    	private double [] x;
    	private double [] y;
	
    	private int myStart;
    	private int myStop;
	    
    	// Constructor
    	public CounterThread(int tid, int numThreads, double[][] array, double[] x, double[] y, int size2) {
    	
    		this.threadID = tid;
    		this.table = array;
    		this.x = x;
        	this.y = y;
	    
			// Break the problem into pieces (SPMD)
        	this.myStart = threadID * (size2 / numThreads);
        	this.myStop = myStart + (size2 / numThreads);
        	if (threadID == (numThreads - 1)) myStop = size2;

		}
	
    	// Thread Code
    	public void run() {
	  
    		for (int i=myStart; i < myStop; i++) {
    			for (int j = myStart; j < myStop; j++) {
    				sum = sum + table[i][j]*x[j];
    			}
    			y[i] = sum;
			}
		}
    	
	}
    
    
}
