package pdc_lab_2_2021_2_3_2;


public class MyMatMul {

	// Global shared variable to store total result of all threads 
	static double result = 0;
	
	public static void main(String[] args) {
		 
		int size = 0; // Problem size
	    int numThreads = 0; // Total threads number
	    
	    // Initialize vector size
	    int vecSize = 10000;
	        
	    // CMD input 
		if (args.length != 2) {
	    		System.out.println("Usage: java ThreadParSqrt <vector size> <number of threads>");
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
        if (numThreads == 0) numThreads = Runtime.getRuntime().availableProcessors();
		
        
        // Initialize array 
        double[][] a = new double[size][size];
             
         for (int i = 0; i < size; i++)
             for (int j = 0; j < size; j++)
             	a[i][j] = 1;
             
         double[] x = new double[size];
         double[] y = new double[size];
             
         for (int i = 0; i < size; i++) {
        	 x[i] = 1;
             y[i] = 0;
         }
             
         /* Initialize vector */
         double[] vecTable = new double[vecSize];
             
         for (int i=0; i<vecSize; i++) {
        	 vecTable[i] +=i;
         }
  
		// Get current time
        long start = System.currentTimeMillis();

		// Create threads
		SqrtGroupThread threads[] = new SqrtGroupThread[numThreads];
		
		// Thread execution   
		for(int i = 0; i < numThreads; i++) 
		{
			threads[i] = new SqrtGroupThread(i, numThreads, a, size, vecTable);
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

        // Print final result
        System.out.println("Result = " + result);

	}

}
