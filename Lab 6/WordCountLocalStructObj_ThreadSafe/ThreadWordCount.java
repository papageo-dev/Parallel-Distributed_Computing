package WordCountLocalStructObj_ThreadSafe;

import java.util.Hashtable;

public class ThreadWordCount extends Thread {

	int tID;
	String[] words;
	int size;
	private int myStart;
    private int myStop;
    
    // Create a local WordObj objext
    private WordObj localWordObj;
    
    // Create local struct - Thread Safe
    Hashtable<String, Integer> localMap = new Hashtable<String, Integer>();
    
    // Constructor
	public ThreadWordCount(int i, int numThreads, int size, String[] words, WordObj myWordObj) {
		
		this.tID = i;
		this.words = words;
		this.size = size;
		
		//Initialize object variable
		this.localWordObj = myWordObj;
		
		myStart = tID * (size / numThreads);
        myStop = myStart + (size / numThreads);
        if (tID == (numThreads - 1)) myStop = size;
		
	}
	
	// Thread Code
	public void run() {
		
        // Count Words' appearances in text file
        for (int wordCounter = myStart; wordCounter < myStop; wordCounter++) {
            String key = words[wordCounter].toLowerCase();
            if (key.length() > 0) {
                if (localMap.get(key) == null) {
                	//Add word count to local struct
                	localMap.put(key, 1);
                }
                else {
                    int value = localMap.get(key).intValue();
                		// Increase value and add word count to local map
                        value++;
                        localMap.put(key, value);
                }
            }
        }
        // Add local struct results to global struct results
        localWordObj.add(localMap);
	}
	
	
}
