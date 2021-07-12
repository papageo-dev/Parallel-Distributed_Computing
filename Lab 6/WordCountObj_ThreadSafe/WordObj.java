package WordCountObj_ThreadSafe;

import java.util.Hashtable;


public class WordObj {
	
	
	// Struct
    private Hashtable<String, Integer> map = new Hashtable<String, Integer>(); 
    
	// Constructor
	public WordObj(Hashtable<String, Integer> map) {
		this.map = map;
	}
	
	// Method to add value to Struct by key
	public void add(String key) {
		
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
