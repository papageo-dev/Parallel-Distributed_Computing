package sieveStaticSPMD;

public class StaticSPMDThread extends Thread {
	
	private int tID;
	private int size;
	private boolean[] prime;
	private int limit;
	private int myStart;
    private int myStop;
    
	
	public StaticSPMDThread(int i, int numThreads, int size, boolean prime[]) {
		
		this.tID = i;
		this.size = size;
		this.prime = prime;
		
		// Break calculation into pieces/blocks
		limit = (int)Math.sqrt(size) + 1;
		myStart = tID * (limit / numThreads);
		// If thread ID is 0, increase myStart until 2
		while (myStart<2) {
			myStart++;
		}
        myStop = myStart + (limit / numThreads);
        if (tID == (numThreads - 1)) myStop = limit;
	}
	
	// Thread Code
	public void run() {
		
		// Calculate a static block of total calculation, from myStart to myStop
		for (int p = myStart; p <= myStop; p++) {
			// If prime[p] is not changed, then it is a prime
			if (prime[p] == true) {
				// Update all multiples of p
				for (int i = p*p; i <= size; i += p) {
					prime[i] = false;	
				}	
			}
		}	
	}

}
