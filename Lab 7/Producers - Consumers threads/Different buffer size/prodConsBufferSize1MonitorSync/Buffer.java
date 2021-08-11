package prodConsBufferSize1MonitorSync;

import java.util.concurrent.Semaphore;

//Buffer size = 1, Semaphore(s)

public class Buffer {
	
	private int contents; //Change to int. Needn't array, because buffer can have one item.
    private volatile int counter = 0; //Change counter

	// Constructor
    public Buffer() { 
        this.contents = 0; //Initialize buffer's contents
     }

	 // Put an item into buffer
	 public synchronized void put(int data) {
		 
		 //While buffer is full(==1), wait
		 while (counter == 1) {
			try {
				wait();
			} catch (InterruptedException e) {}
		 }
		 //Produce an item
		 contents = data;
		 //Change buffer
		 counter = 1;
		 System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " - Count = " + counter);
		 /*
		 if (counter == 1) {
			 System.out.println("Buffer is full!");
		 }
		 */
			
		 //Signal other threads
		 notifyAll();
	}

	// Get an item from buffer
	public synchronized int get() { 
		
		int data = 0;
		
		//While buffer is empty(==0), wait
		 while (counter == 0) {
			try {
				wait();
			} catch (InterruptedException e) {}
		 }
        	
         //Consume an item
		 data = contents;
		 //Change buffer condition
		 counter = 0;
		 System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " - Count = " + counter);
		 /*
		 if (counter == 0) {
			 System.out.println("Buffer is empty!");
		 }
		 */
		 
		 //Signal other threads
		 notifyAll();
			
		 return data;
	}
}

	
			
	
