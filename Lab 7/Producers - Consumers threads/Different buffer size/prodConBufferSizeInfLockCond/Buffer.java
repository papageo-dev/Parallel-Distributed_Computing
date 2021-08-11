package prodConBufferSizeInfLockCond;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;


//"Infinity" Buffer size, Lock-Conditions

public class Buffer {
	
	private int[] contents;
	//private final int size;
	private final int infBufferS;
	private int front, back;
    private volatile int counter = 0;
	private Lock lock = new ReentrantLock();
	//private final Condition bufferFull = lock.newCondition();
    private final Condition bufferEmpty = lock.newCondition();

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

		 //Lock the Critical Section(shared counter)
		 lock.lock(); 
		 try {
			 //Needn't this loop, because buffer's size is unlimited.
			 /*
			 while (counter == size) {
				 //System.out.println("The buffer is full");
                 try {
                	 bufferFull.await(); 
                	 } catch (InterruptedException e) { }
             }
             */
			 //Produce an item and increase shared counter by 1
             back = (back + 1); //Needn't "% size" 
			 contents[back] = data;
             counter++;
			 System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " Loc " + back + " - Count = " + counter);
			 //Signal other threads(Consumers)
			 bufferEmpty.signalAll(); 
		 } finally {
			 lock.unlock(); //Unlock CS
         }
	}

	// Get an item from buffer
	public int get() { 
		
		int data = 0;
		//Lock Critical Section(Shared Counter)
		lock.lock();
        try { 
        	//While buffer is empty(==0), wait
        	while (counter == 0) {
        		//System.out.println("The buffer is empty");
                try {
                	bufferEmpty.await();
                	} catch (InterruptedException e) { }
            }
        	//Consume an item and decrease shared counter by 1
			data = contents[front];
			System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " Loc " + front + " Count = " + (counter-1));
            front = (front + 1);
			counter--;
			//Buffer will never be full. Needn't this signal
			//bufferFull.signalAll(); 
		} finally { 
			lock.unlock(); //Unlock CS
        }
        return data;
	}
}

	
			
	
