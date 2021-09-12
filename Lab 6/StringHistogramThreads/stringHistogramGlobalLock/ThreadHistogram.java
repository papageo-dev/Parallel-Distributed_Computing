package stringHistogramGlobalLock;

public class ThreadHistogram extends Thread {
	
	int tID;
	int n;
	char[] text;
	private int myStart;
    private int myStop;

    // Constructor
	public ThreadHistogram(int i, int numThreads, int n, char[] text) {
		
		this.tID = i;
		this.n = n;
		this.text = text;
		
		// SPMD
		myStart = tID * (n / numThreads);
        myStop = myStart + (n / numThreads);
        if (tID == (numThreads - 1)) myStop = n;
	}
	
	// Thread Code
	public void run() {
		// Calculate a piece/block of total calculation
		for (int i = myStart; i < myStop; i++) {
			// Lock Critical Section. Lock global array "histogram[]"
			StringHistogram.lock.lock();
			try {
				// Increase counter at current letter's position of "histogram[]"
				StringHistogram.histogram[(int)text[i]] ++;	
			} finally {
				// Unlock Critical Section.
				StringHistogram.lock.unlock();
			}
		}
	}

}
