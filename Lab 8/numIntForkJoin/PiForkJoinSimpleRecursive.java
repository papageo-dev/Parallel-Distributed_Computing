package numIntForkJoin;


public class PiForkJoinSimpleRecursive {

    public static void main(String[] args) {

        /* Get number of logical cores from runtime */
        int numSteps = 0;
        int limit = 0;
          
        if (args.length != 2) {
    		System.out.println("Usage: java SimpleRecursive <number of steps> <limit>");
    		System.exit(1);
        }

        try {
        	numSteps = Integer.parseInt(args[0]);
        	limit = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException nfe) {
        	System.out.println("Integer argument expected");
    		System.exit(1);
        }
        if (numSteps <= 0) {
            System.out.println("size should be positive integer");
    		System.exit(1);
        }
        if (limit == 0) {
        	limit =100;
        }
        
        //Initialize step
        double step = 1.0 / (double)numSteps;
        
        // Get current time
        long start = System.currentTimeMillis();

        /* Create and start thread 0 */
        Recursive mythread = new Recursive(0, numSteps, limit, step);
        mythread.start();        

        double threadSum = 0;
		//Collect result from threads
        try {
            mythread.join();
            threadSum = mythread.myResult;
        } catch (InterruptedException e) {}

        // Calculate total result
        double piSum = threadSum*step;
        
        // Get current time and calculate elapsed time
        long elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("time in ms = "+ elapsedTimeMillis);

        // Print Results
	    System.out.printf("sequential program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , piSum);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(piSum - Math.PI));
    }
}

class Recursive extends Thread {

        private long myFrom;
        private long myTo;
        private long myLimit;
        private double step;
        public  double myResult;
       
	    // Constructor
        public Recursive(long myFrom, long mid, long myLimit, double step) {
        	this.myFrom = myFrom;
            this.myTo = mid;
            this.myLimit = myLimit;
            this.myResult = 0;
            this.step = step;
		}

		public void run() {
               	//System.out.printf("Starting thread %d %d \n",myFrom, myTo);
				
                // Do recursion until limit is reached
			    long workload = myTo - myFrom;
                
			    // If workload NOT reaches the limit
                if (workload <= myLimit) {
                	// Local result variable
	                myResult = 0;
	                
	                for (long i=myFrom; i<myTo; i++) {
	                	// Do calculation and add to myResult
	                	double x = ((double)i+0.5)*step;
	                	myResult += 4.0/(1.0+x*x);
	                }
	        		//If limit reaches, split workload and create Right and Left thread
                } else {
                      long mid = myFrom + workload / 2;
                      //System.out.printf("L %d Dd %d \n",myFrom, mid, myLimit);
                      Recursive threadL = new Recursive(myFrom, mid, myLimit, step);
                      threadL.start();
                      //System.out.printf("R %d Dd %d \n", mid, myTo, myLimit);
                      Recursive threadR = new Recursive(mid, myTo, myLimit, step);
                      threadR.start();
                      // Wait threads(R,L) to terminate and collect results to "myResult"
                      try {
            		  threadL.join();
            		  myResult = threadL.myResult;
            		  threadR.join();
            		  myResult += threadR.myResult;
            	      } catch (InterruptedException e) {}
              }
       }

}

