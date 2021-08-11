package prodConsBufferSize1SemMux;

import java.util.concurrent.Semaphore;

//Buffer size = 1, Semaphore(s)

public class Buffer {
	
	private int contents; //Change to int. Needn't array, because buffer can have one item.
    private volatile int counter = 0; //Change counter
    //private Semaphore mutex = new Semaphore(1);
	private Semaphore bufferFull = new Semaphore(0);
    private Semaphore bufferEmpty = new Semaphore(1); 

	// Constructor
    public Buffer() { 
        this.contents = 0; //Initialize buffer's contents
     }

	 // Put an item into buffer
	 public void put(int data) {

		 try {
			 bufferEmpty.acquire();
			 } catch (InterruptedException e) { }
		 /*
		 try {
			 mutex.acquire();
			 } catch (InterruptedException e) { }
		 */
			 
			 //Produce an item
			 contents = data;
			 //Change buffer
			 counter = 1;
			 System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " - Count = " + counter);
			 
			 //Unlock CS and signal other threads(Consumers)
			 //mutex.release(); 
			 bufferFull.release();
	}

	// Get an item from buffer
	public int get() { 
		
		int data = 0;
		try {
			 bufferFull.acquire();
			 } catch (InterruptedException e) { }
		/* 
		try {
			 mutex.acquire();
			 } catch (InterruptedException e) { }
		*/
        	
        	//Consume an item
			data = contents;
			//Change buffer condition
			counter = 0;
			System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " - Count = " + counter);
			
			//Unlock CS and signal other threads(Producers)
			//mutex.release(); 
			bufferEmpty.release();
			
			return data;
	}
}

	
			
	
