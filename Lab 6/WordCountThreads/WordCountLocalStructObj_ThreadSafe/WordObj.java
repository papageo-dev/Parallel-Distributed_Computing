package WordCountLocalStructObj_ThreadSafe;

import java.util.Hashtable;

public class WordObj {
	
	// Struct - Thread Safe
	// Needn't lock(s) or synchronized methods, because Hashtable is thread-safe struct
    private Hashtable<String, Integer> map = new Hashtable<String, Integer>(); 
    
	// Constructor
	public WordObj(Hashtable<String, Integer> map) {
		this.map = map;
	}
	
    // Method to add thread's calculation to my final Global Struct
	public void add(Hashtable<String, Integer> localMap) {
		
        for (String name: localMap.keySet()) {
            String myKey = name.toString();
            int myValue = localMap.get(myKey).intValue();
            map.put(myKey, myValue);	
        }
        
	}
	

}
