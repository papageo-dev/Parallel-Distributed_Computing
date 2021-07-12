package stringHistogramObjSync;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class StringHistogram {

    public static void main(String args[]) throws IOException {
    	
    	int numThreads = 4; // Total threads
    	
    	int alphabetSize = 256; // Alphabet total size
        int[] histogram = new int[alphabetSize];  // Array to store histogram of given Alphabet
    	
        // Create a HistogramObj variable
    	HistogramObj obj = new HistogramObj(histogram);

    	// Initialize text file from cmd
        if (args.length != 1) {
		System.out.println("StringHistogram <file name>");
                System.exit(1);
        }
        
        String fileString = new String(Files.readAllBytes(Paths.get(args[0])));//, StandardCharsets.UTF_8);
        char[] text = new char[fileString.length()]; 
        int n = fileString.length();
        for (int i = 0; i < n; i++) { 
            text[i] = fileString.charAt(i); 
        } 
 
        // Initialize histogram array 
        for (int i = 0; i < alphabetSize; i++) { 
            histogram[i] = 0; 
        }
        
        // Get current time
        long start = System.currentTimeMillis();
        
        // Create threads
        ThreadHistogram threads[] = new ThreadHistogram[numThreads];
        
        // Thread execution   
     	for (int i = 0; i < numThreads; i++) {
     		threads[i] = new ThreadHistogram(i, numThreads, n, text, obj);
     		threads[i].start();
     	}
     	
     	// Wait for threads to terminate            
     	for (int i = 0; i < numThreads; i++) {
     		try {
     			threads[i].join();
     			} catch (InterruptedException e) {}
     	}
        
     	// Print sum for every letter/number/symbol in text
        for (int i = 0; i < alphabetSize; i++) { 
            System.out.println((char)i+": " + histogram[i]);
        }
        
        // Get current time and calculate elapsed time
        long elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("time in ms = "+ elapsedTimeMillis);
   }
    
}



