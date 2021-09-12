package WordCountObj_ThreadSafe;

public class ThreadWordCount extends Thread {

	int tID;
	String[] words;
	int size;
	private int myStart;
    private int myStop;
    
    // Create a local WordObj objext
    private WordObj localWordObj;
    
    // Constructor
	public ThreadWordCount(int i, int numThreads, int size, String[] words, WordObj myWordObj) {
		
		this.tID = i;
		this.words = words;
		this.size = size;
		
		// Initialize object variable
		this.localWordObj = myWordObj;
		
		// SPMD
		myStart = tID * (size / numThreads);
        myStop = myStart + (size / numThreads);
        if (tID == (numThreads - 1)) myStop = size;
		
	}
	
	// Thread Code
	public void run() {
		
        // Count Words' appearances in current piece/block of the text file
        for (int wordCounter = myStart; wordCounter < myStop; wordCounter++) {
            String key = words[wordCounter].toLowerCase();
            if (key.length() > 0) {
            	localWordObj.add(key);
            }
        }
        
	}
	
	
}
