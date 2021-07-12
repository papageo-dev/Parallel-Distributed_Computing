package stringMatchObjSync;

public class ThreadMatch extends Thread {
	
	private int tID;
	private char[] text; 
	private char[] pattern; 
	private char[] match; 
	private int m; 
	private int myStart;
    private int myStop;
    
    // Local Object
    private MyObj localMatchCount;
	
	// Constructor
	public ThreadMatch(int i, int numThreads, char[] text, char[] pattern, char[] match, int m, int matchLength, MyObj matchCountObj) {
	
		// Initialize Object
		this.localMatchCount = matchCountObj;
		
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

	//Thread Code
	public void run() {
		// Calculate a piece/block of total calculation
		for (int j = myStart; j < myStop; ++j) {
        	int i;
      		for (i = 0; i < m && pattern[i] == text[i + j]; ++i);
      		if (i >= m) {
         		match[j] = '1';
         		localMatchCount.plusOne();
            }        
        }
	}

}
