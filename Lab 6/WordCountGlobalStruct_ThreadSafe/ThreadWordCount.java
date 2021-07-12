package WordCountGlobalStruct_ThreadSafe;

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
                if (MyWordCount.map.get(key) == null) {
                	MyWordCount.map.put(key, 1);
                }
                else {
                    int value = MyWordCount.map.get(key).intValue();
                    value++;
                    MyWordCount.map.put(key, value);
                }
            }
        }
        
	}
	
	
}
