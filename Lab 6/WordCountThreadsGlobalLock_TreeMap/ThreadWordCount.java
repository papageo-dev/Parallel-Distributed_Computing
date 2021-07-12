package WordCountThreadsGlobalLock_TreeMap;

public class ThreadWordCount extends Thread {

	int tID;
	String[] words;
	int size;
	private int myStart;
    private int myStop;
    
    // Constructor
	public ThreadWordCount(int i, int numThreads, int size, String[] words) {
		
		this.tID = i;
		this.words = words;
		this.size = size;
		
		// SPMD
		myStart = tID * (size / numThreads);
        myStop = myStart + (size / numThreads);
        if (tID == (numThreads - 1)) myStop = size;
		
	}
	
	public void run() {
		
        // Count Words' appearances in text file
        for (int wordCounter = myStart; wordCounter < myStop; wordCounter++) {
            String key = words[wordCounter].toLowerCase();
            if (key.length() > 0) {
                if (MyWordCount.map.get(key) == null) {
                	// Lock Critical Section. Lock global struct "map"
                	MyWordCount.lock.lock();
                	try {
                		// Add a word count
                		MyWordCount.map.put(key, 1);
                	} finally {
                		// Unlock Critical Section
                		MyWordCount.lock.unlock();
                	}
                }
                else {
                    int value = MyWordCount.map.get(key).intValue();
                    // Lock Critical Section. Lock global struct "map"
                	MyWordCount.lock.lock();
                	try {
                		// Increase value and add word count
                        value++;
                        MyWordCount.map.put(key, value);
                	} finally {
                		// Unlock Critical Section
                		MyWordCount.lock.unlock();
                	}
                }
            }
        }
        
	}
	
	
}
