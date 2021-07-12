package WordCountThreadsObSync_HashMap;

import java.util.HashMap;

public class WordObj {
	
	// Struct
    private HashMap<String, Integer> map = new HashMap<String, Integer>(); 
    
	// Constructor
	public WordObj(HashMap<String, Integer> map) {
		this.map = map;
	}
	
	// Symchronized mthod to add value to Struct by key
	public synchronized void add(String key) {
		
        if (map.get(key) == null) {
        	map.put(key, 1);
        }
        else {
            int value = map.get(key).intValue();
            value++;
            map.put(key, value);
        }
     
	}
	

}
