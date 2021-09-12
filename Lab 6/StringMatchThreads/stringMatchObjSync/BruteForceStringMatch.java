package stringMatchObjSync;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BruteForceStringMatch {

    public static void main(String args[]) throws IOException {
    	
    	int numThreads = 4; // Total threads
    
    	// Initialize text file and pattern from cmd
        if (args.length != 2) {
        	System.out.println("BruteForceStringMatch  <file name> <pattern>");
            System.exit(1);
        }

        String fileString = new String(Files.readAllBytes(Paths.get(args[0])));//, StandardCharsets.UTF_8);
        char[] text = new char[fileString.length()]; 
        int n = fileString.length();
        for (int i = 0; i < n; i++) { 
            text[i] = fileString.charAt(i); 
        } 
 
        String patternString = new String(args[1]);                                                
        char[] pattern = new char[patternString.length()]; 
        int m = patternString.length(); 
        for (int i = 0; i < m; i++) { 
            pattern[i] = patternString.charAt(i); 
        }

        // Initialize array with matches, between text file and pattern
        int matchLength = n - m;
        char[] match = new char[matchLength+1]; 
        for (int i = 0; i <= matchLength; i++) { 
            match[i] = '0'; 
        }
        
        // Create an object
        MyObj matchCountObj = new MyObj();
        
        // Get current time
        long start = System.currentTimeMillis();
        
        // Create threads
        ThreadMatch threads[] = new ThreadMatch[numThreads];
        
        // Thread execution   
     	for (int i = 0; i < numThreads; i++) {
     		threads[i] = new ThreadMatch(i, numThreads, text, pattern, match, m, matchLength, matchCountObj);
     		threads[i].start();
     	}
     	
     	// Wait for threads to terminate            
     	for (int i = 0; i < numThreads; i++) {
     		try {
     			threads[i].join();
     			} catch (InterruptedException e) {}
     	}
        
        // Print position of matches and number of total matches in text file
        for (int i = 0; i < matchLength; i++) { 
            if (match[i] == '1') {
            	System.out.print(i+" ");
            }
        }
        System.out.println();
        System.out.println("Total matches: " + matchCountObj.getCounter());
        
        // Get current time and calculate elapsed time
        long elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("time in ms = "+ elapsedTimeMillis);
        
   }
}