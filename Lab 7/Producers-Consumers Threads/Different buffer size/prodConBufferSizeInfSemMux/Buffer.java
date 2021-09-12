package prodConBufferSizeInfSemMux;

import java.util.concurrent.Semaphore;

//"Infinity" Buffer size, Lock-Conditions

public class Buffer {
	
	private int[] contents;
	//private final int size;
	private final int infBufferS;
	private int front, back;
    private volatile int counter = 0;
    private Semaphore mutexCount = new Semaphore(50000);
	private Semaphore bufferFull = new Semaphore(0);
    private Semaphore bufferEmpty = new Semaphore(1); 

	// Constructor
    public Buffer(int s) { 
    	this.infBufferS = s;
        contents = new int[infBufferS];
		for (int i=0; i<infBufferS; i++) {
			contents[i] = 0;	
		}
		this.front = 0;
        this.back = - 1; //Change back to "-1"
     }

	 // Put an item into buffer
	 public void put(int data) {

		 //Lock the Critical Section(shared counter) - Only ONE producer can change counter
		 try {
				bufferEmpty.acquire();
	     } catch (InterruptedException e) { }
			 //Produce an item and increase shared counter by 1
             back = (back + 1); //Needn't "% size" 
			 contents[back] = data;
             counter++;
			 System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " Loc " + back + " - Count = " + counter);
			 //Unlock CS and Signal other threads(Producers)
			 bufferFull.release();
			 //Unlock CS and Signal other threads(Consumers)
			 bufferEmpty.release();
			 
	}

	// Get an item from buffer
	public int get() { 
		
		int data = 0;
		//Lock Critical Section(Shared Counter)
		try {
			bufferFull.acquire();
		} catch (InterruptedException e) { } 
		try {
			mutexCount.acquire();
		} catch (InterruptedException e) { } 
        	//Consume an item and decrease shared counter by 1
			data = contents[front];
			System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " Loc " + front + " Count = " + (counter-1));
            front = (front + 1);
			counter--;
			//Buffer will never be full. Needn't this signal
			//Unlock CS and signal other threads(Consumers)
			bufferEmpty.release();
        return data;
	}
}

	
			
	
