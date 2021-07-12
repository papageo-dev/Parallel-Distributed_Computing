package WordCount_TreeMap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.TreeMap;


public class WordCount {
	
    public static void main(String args[]) throws FileNotFoundException, IOException {
    
    	// CMD input file
        if (args.length != 1) {
		System.out.println("WordCount  <file name>");
                System.exit(1);
        }
        
        // Read words from file and add to array "words"
        String fileString = new String(Files.readAllBytes(Paths.get(args[0])));//, StandardCharsets.UTF_8);
        String[] words = fileString.split("[ \n\t\r.,;:!?(){}]");    
        
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        // HashMap<String, Integer> map = new HashMap<String, Integer>();
            
        // Get current time
        long start = System.currentTimeMillis();
        
        // Count Words' appearances in text file
        for (int wordCounter = 0; wordCounter < words.length; wordCounter++) {
            String key = words[wordCounter].toLowerCase();
            if (key.length() > 0) {
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

        // Get every word's appearance times from struct and print result
        for (String name: map.keySet()) {
                String key = name.toString();
                String value = map.get(name).toString();
    		System.out.println(key + "\t " + value);
        }
        
        // Get current time and calculate elapsed time
        long elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("time in ms = "+ elapsedTimeMillis);
    }
}
