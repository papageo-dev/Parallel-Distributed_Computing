package WordCountThreadsGlobalLock_HashMap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.locks.ReentrantLock;


import java.util.HashMap;

public class MyWordCount {
	
	// Global Struct
    public static HashMap<String, Integer> map = new HashMap<String, Integer>();
    // Global Lock variable
    public static ReentrantLock lock = new ReentrantLock();
	
    public static void main(String args[]) throws FileNotFoundException, IOException {
    	
    	int numThreads = 4; // Total threads
    
    	// CMD input file
        if (args.length != 1) {
		System.out.println("WordCount  <file name>");
                System.exit(1);
        }
        
        // Read words from file and add to array "words"
        String fileString = new String(Files.readAllBytes(Paths.get(args[0])));//, StandardCharsets.UTF_8);
        String[] words = fileString.split("[ \n\t\r.,;:!?(){}]");
        int n = words.length;
            
        // Get current time
        long start = System.currentTimeMillis();
        
        // Create threads
        ThreadWordCount threads[] = new ThreadWordCount[numThreads];
        
        // Thread execution   
     	for (int i = 0; i < numThreads; i++) {
     		threads[i] = new ThreadWordCount(i, numThreads, n, words);
     		threads[i].start();
     	}
     	
     	// Wait for threads to terminate            
     	for (int i = 0; i < numThreads; i++) {
     		try {
     			threads[i].join();
     			} catch (InterruptedException e) {}
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
