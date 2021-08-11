package prodNconsONELockCond;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Buffer {
	
	private int[] contents;
	private final int size;
	private int front, back;
    private volatile int counter = 0;
	private Lock lock = new ReentrantLock();
	private final Condition bufferFull = lock.newCondition();
    private final Condition bufferEmpty = lock.newCondition();

	// Constructor
    public Buffer(int s) { 
    	this.size = s;
        contents = new int[size];
		for (int i=0; i<size; i++) {
			contents[i] = 0;	
		}
		this.front = 0;
        this.back = size - 1;
     }

	 // Put an item into buffer - This method need't lock, because there is only ONE producer
	 public void put(int data) {

		 lock.lock(); 
		 try {
			 while (counter == size) {
				 //System.out.println("The buffer is full");
                 try {
                	 bufferFull.await(); 
                	 } catch (InterruptedException e) { }
             }
          
             back = (back + 1) % size;
			 contents[back] = data;
             counter++;
			 System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " Loc " + back + " Count = " + counter);
			 
			 bufferEmpty.signalAll();
		 } finally {
			 lock.unlock() ;
         }
	}

	// Get an item from buffer
	public int get() { 
		
		int data = 0;
		lock.lock();
        try { 
        	while (counter == 0) {
        		System.out.println("The buffer is empty");
                try {
                	bufferEmpty.await();
                	} catch (InterruptedException e) { }
            }
			data = contents[front];
			System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " Loc " + front + " Count = " + (counter-1));
            front = (front + 1) % size;
			counter--;
			bufferFull.signalAll();
		} finally { 
			lock.unlock() ;
        }
        return data;
	}
}

	
			
	
