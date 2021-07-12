package stringHistogramObjSync;

public class ThreadHistogram extends Thread {
	
	int tID;
	int n;
	char[] text;
	private int myStart;
    private int myStop;
    
    // Create a HistogramObj variable
    private HistogramObj myObj;

    // Constructor
	public ThreadHistogram(int i, int numThreads, int n, char[] text, HistogramObj obj) {
		
		this.tID = i;
		this.n = n;
		this.text = text;
		
		// Initialize object variable
		this.myObj = obj;
		
		// SPMD
		myStart = tID * (n / numThreads);
        myStop = myStart + (n / numThreads);
        if (tID == (numThreads - 1)) myStop = n;
	}
	
	// Thread Code
	public void run() {
		// Calculate a piece/block of total calculation
		for (int i = myStart; i < myStop; i++) {
			int txt = (int)text[i];
			myObj.plusOne(txt);	
		}
	}

}
