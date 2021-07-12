package stringMatchGlobalCounterLock;

public class ThreadMatch extends Thread {
	
	private int tID;
	private char[] text; 
	private char[] pattern; 
	private char[] match; 
	private int m; 
	private int myStart;
    private int myStop;
	
	// Constructor
	public ThreadMatch(int i, int numThreads, char[] text, char[] pattern, char[] match, int m, int matchLength) {
		this.tID = i;
		this.text = text;
		this.pattern = pattern;
		this.match = match;
		this.m = m;
		
		// SPMD
		myStart = tID * (matchLength / numThreads);
        myStop = myStart + (matchLength / numThreads);
        if (tID == (numThreads - 1)) myStop = matchLength;
		
	}

	// Thread Code
	public void run() {
		// Calculate a piece/block of total calculation
		for (int j = myStart; j < myStop; ++j) {
        	int i;
      		for (i = 0; i < m && pattern[i] == text[i + j]; ++i);
      		if (i >= m) {
         		match[j] = '1';
         		// Lock Critical Section. Only one thread have access to change the shared counter "matchCount"
         		BruteForceStringMatch.lock.lock();
         		try {
         			// Critical Section
                    BruteForceStringMatch.matchCount++;

         		} finally {
         			// Unlock Critical Section.
         			BruteForceStringMatch.lock.unlock(); 
         		}
            }        
        }
	}

}
