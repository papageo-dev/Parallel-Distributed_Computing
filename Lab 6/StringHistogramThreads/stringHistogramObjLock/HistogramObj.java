package stringHistogramObjLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HistogramObj {
	
	private final Lock lock = new ReentrantLock(); //Local Lock variable
	int histogram[];

	// Constructor
	public HistogramObj(int[] histogram) {
		this.histogram = histogram;
	}
	
	// Method to increase counter of text's position
	public void plusOne(int txt) {
		// Lock Critical Section
		lock.lock();
		try {
			// Critical Section
			histogram[txt] ++;	
		} finally {
			// Unlock Critical Section
			lock.unlock();
		}
	}

}
