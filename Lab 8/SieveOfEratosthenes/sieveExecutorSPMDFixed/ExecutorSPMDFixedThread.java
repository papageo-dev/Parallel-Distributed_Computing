package sieveExecutorSPMDFixed;

class ExecutorSPMDFixedThread implements Runnable {
	
	private boolean [] table;
	private int myid;
	private int tableSize;
	private int blockSize;

	// Constructor
	public ExecutorSPMDFixedThread(boolean [] prime, int id, int s, int block) {
		
		this.table = prime;
		this.myid = id;
		this.tableSize = s;
		this.blockSize = block;	
	}

	// Thread code
	public void run() {
		
	    int myfrom = 0;
		int myto = 0;

		// Initialize blocks (SPMD)
		myfrom = myid * blockSize;
		myto = myfrom + blockSize;
		int limit = (int)Math.sqrt(tableSize) + 1;
		if (myto > limit) myto = limit;

		// Any thread calculate a block(a piece of total calculation) from "myfrom" to "myto"
		for (int p=myfrom; p<myto; p++) {
			
			if (p>=2) {
				// If prime[p] is not changed, then it is a prime
				if (table[p] == true) {
					// Update all multiples of p
					for (int i = p*p; i <= tableSize; i += p) {
						table[i] = false;	
					}	
				}
			}
		}
		
	}
	
	
}