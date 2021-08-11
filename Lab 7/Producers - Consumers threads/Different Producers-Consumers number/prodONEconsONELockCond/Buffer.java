package prodONEconsONELockCond;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

//One Producer - One Consumer, Lock-Condition

public class Buffer {
	
	private int[] contents;
	private final int size;
	private int front, back;
    private volatile int counter = 0;
	private Lock lock = new ReentrantLock();
	//Need only a lock condition, that won't let producer and consumers change shared counter at the same time
	private final Condition notEmpty = lock.newCondition();

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

	 // Put an item into buffer
	 public void put(int data) {

		 //Lock CS. Only producer have access and can change shared counter
		 lock.lock(); 
		 try {
			 while (counter == size) {
				 //System.out.println("The buffer is full");
                 try {
                	 notEmpty.await(); 
                	 } catch (InterruptedException e) { }
             }
             back = (back + 1) % size;
			 contents[back] = data;
             counter++;
			 System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " Loc " + back + " Count = " + counter);
			 //Signal other thread(consumer)
			 notEmpty.signalAll();
		 } finally {
			 //Unlock CS
			 lock.unlock();
         }
	}

	// Get an item from buffer
	public int get() { 
		
		int data = 0;
		//Lock CS. Only producer have access and can change shared counter
		lock.lock();
        try { 
        	while (counter == 0) {
        		//System.out.println("The buffer is empty");
                try {
                	notEmpty.await();
                	} catch (InterruptedException e) { }
            }
			data = contents[front];
			System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " Loc " + front + " Count = " + (counter-1));
            front = (front + 1) % size;
			counter--;
			//Signal other thread(producer)
			notEmpty.signalAll();
		} finally { 
			//Unlock CS
			lock.unlock() ;
        }
        return data;
	}
}

	
			
	
