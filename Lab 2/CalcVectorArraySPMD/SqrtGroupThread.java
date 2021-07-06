package pdc_lab_2_2021_2_3_2;


class SqrtGroupThread extends Thread
{
	private double [][] table; 
	private double [] vecTable;
	
	private int myStart; 
    private int myStop; 
    

	// Constructor
	public SqrtGroupThread(int myId, int numThreads, double[][] array, int size, double[] vecTable)
	{
		this.table = array;
		this.vecTable = vecTable;
		
		// Break problem into blocks (SPMD)
		this.myStart = myId * (size / numThreads);
        this.myStop = myStart + (size / numThreads);
        if (myId == (numThreads - 1)) myStop = size;
        
       
	}

	// Thread code
	public void run()
	{
		// Each thread calculates a piece/block of total calculation
	    for (int i=myStart; i<myStop; i++) {
	        double sum = 0;
	        for (int j = 0; j < vecTable.length; j++) {
	            sum = sum + table[i][j]*vecTable[j];
	        }
			// Add thread's result to total result
	        MyMatMul.result += sum;
	    }
	}
}