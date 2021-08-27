package sieveCyclicSimple;

public class CyclicSimpleThread extends Thread {
	
	private int myRank;
	private int size;
	private boolean[] prime;
	private int limit;
	private int numThreads;
    
	
	public CyclicSimpleThread(int rank, int numThreads, int size, boolean prime[]) {
		this.myRank = rank;
		this.numThreads = numThreads;
		this.size = size;
		this.prime = prime;
		limit = (int)Math.sqrt(size) + 1;
	}
	
	//Thread Code
	public void run() {
		
		//Calculate a block of total calculation, from myRank to limit
		//Any thread calculates its block after p threads
		for (int p = myRank; p <= limit; p+=numThreads) {
			//Check if thread's rank is 2 or higher
			if (p>=2) {
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

}
