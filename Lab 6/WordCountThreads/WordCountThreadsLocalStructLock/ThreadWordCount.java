package WordCountThreadsLocalStructLock;

import java.util.TreeMap;

public class ThreadWordCount extends Thread {

	int tID;
	String[] words;
	int size;
	private int myStart;
    private int myStop;
    
    // Local Struct - (Change One of Structs)
    private TreeMap<String, Integer> localMap = new TreeMap<String, Integer>();
    // private HashMap<String, Integer> localMap = new HashMap<String, Integer>();
    
    // Constructor
	public ThreadWordCount(int i, int numThreads, int size, String[] words) {
		
		this.tID = i;
		this.words = words;
		this.size = size;
		
		myStart = tID * (size / numThreads);
        myStop = myStart + (size / numThreads);
        if (tID == (numThreads - 1)) myStop = size;
		
	}
	
	public void run() {
		
        // Count Words' appearances in current piece/block of the text file
        for (int wordCounter = myStart; wordCounter < myStop; wordCounter++) {
            String key = words[wordCounter].toLowerCase();
            if (key.length() > 0) {
                if (localMap.get(key) == null) {
                	// Add word count to local struct
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
        
        // Add thread's calculation to my final Global Struct
        for (String name: localMap.keySet()) {
            String myKey = name.toString();
            int myValue = localMap.get(myKey).intValue();
            MyWordCount.lock.lock();
            try {
                MyWordCount.map.put(myKey, myValue);	
            } finally {
                MyWordCount.lock.unlock();
            }
        }
                   
    }
        
	
	
}
