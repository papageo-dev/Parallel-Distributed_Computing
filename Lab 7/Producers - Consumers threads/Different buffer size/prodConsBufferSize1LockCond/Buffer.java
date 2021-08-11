package prodConsBufferSize1LockCond;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

//Buffer size = 1, Lock-Conditions

public class Buffer {
	
	private int contents; //Change to int. Needn't array, because buffer can have one item.
    private volatile int counter = 0; //Change counter
	private Lock lock = new ReentrantLock();
	private final Condition bufferFull = lock.newCondition();
    private final Condition bufferEmpty = lock.newCondition();

	// Constructor
    public Buffer() { 
        this.contents = 0; //Initialize buffer's contents
     }

	 // Put an item into buffer
	 public void put(int data) {

		 lock.lock(); //Lock shared counter (CS)
		 try {
			 // While buffer have an item...
			 while (counter == 1) {
				 //System.out.println("The buffer is full!");
                 try {
                	 //Producer can't produce more and wait
                	 bufferFull.await(); 
                	 } catch (InterruptedException e) { }
             }
			 
			 //Produce an item
			 contents = data;
			 //Change buffer
			 counter = 1;
			 System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " - Count = " + counter);
			 
			 //Tell other threads(consumers)
			 bufferEmpty.signalAll();
		 } finally {
			 lock.unlock(); //Unlock shared counter (CS)
         }
	}

	// Get an item from buffer
	public int get() { 
		
		int data = 0;
		lock.lock(); //Lock shared counter (CS)
        try { 
        	//While buffer haven't got item
        	while (counter == 0) {
        		//System.out.println("The buffer is empty!");
                try {
                	//Consumer will wait
                	bufferEmpty.await();
                	} catch (InterruptedException e) { }
            }
        	
        	//Consume an item
			data = contents;
			//Change buffer condition
			counter = 0;
			System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " - Count = " + counter);
			
			//Tell other threads(producers)
			bufferFull.signalAll();
		} finally { 
			lock.unlock(); //Unlock shared counter (CS)
        }
        return data;
	}
}

	
			
	
