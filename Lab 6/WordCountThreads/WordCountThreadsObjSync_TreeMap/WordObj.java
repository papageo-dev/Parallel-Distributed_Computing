package WordCountThreadsObjSync_TreeMap;

import java.util.TreeMap;


public class WordObj {
	
	// Struct
    private TreeMap<String, Integer> map = new TreeMap<String, Integer>(); 
    
	// Constructor
	public WordObj(TreeMap<String, Integer> map) {
		this.map = map;
	}
	
	// Synchronized method to add value to Struct by key
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
