package mySharedMatVec_lab3_1;


public class MySharedSqrtThreadObj {
	
	public static void main(String[] args) {
	
		int numThreads = 4; // Total number of threads
		int size = 1000000; // Problem size
	
		// Initialize shared array
		double[] a = new double[size];
	
		// Initialize shared object sharedArray
		SharedArray sharedArray = new SharedArray(size, a);
                   
		// Get current time
		long start = System.currentTimeMillis();
	
		// Create threads
		Thread1 threads[] = new Thread1[numThreads];
	
		// Thread execution 
		for(int i = 0; i < numThreads; i++) 
		{
			threads[i] = new Thread1(i, numThreads, sharedArray, size);
			// Arguments: 1. Thread ID, 2. Total number of threads, 3. Shared struct(array), 4. Problem size
			threads[i].start();
		}

		// Wait for threads to terminate        
		for(int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
	           	} catch (InterruptedException e) {}
		}
		
		// Get current time and calculate elapsed time
     	long elapsedTimeMillis = System.currentTimeMillis()-start;
     	System.out.println("time in ms = "+ elapsedTimeMillis);
		
	}
	
}


// Shared Object Class
class SharedArray {
	
	int size;
	double[] array;
    
	// Constructor
    public SharedArray (int size, double[] array) {
    	this.size = size;
    	this.array = array;
    	createTable(); // Add elements to array[]
    }
    
    // Methos to create array
    public void createTable() {
    	for (int i = 0; i < size; i++) {
    		array[i] = i;
    	}
    }
    
    // Method to calculate square root for given integer
    public void calcSqrt(int i) {
    	array[i] = Math.sqrt(array[i]);
    }    
    
}
	
	

class Thread1 extends Thread {

	private SharedArray table;
	int size;
	
	private int myStart;
    private int myStop;
    
	// Constructor
	public Thread1(int myId, int numThreads, SharedArray a, int size) {
		this.table=a;
		this.size = size;
		
		// Break problem into blocks/pieces (SPMD)
		myStart = myId * (size / numThreads);
        myStop = myStart + (size / numThreads);
        if (myId == (numThreads - 1)) myStop = size;
	}

	// Thread code
	public void run() {
		// Calculate square root of array's elements, from "myStart" to "myStop"
		for (int i=myStart; i<myStop; i++) {
			table.calcSqrt(i);
		}
	}	
	
}
