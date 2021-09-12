package prodNconsONESemMux;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;

public class Buffer {
	
	private int[] contents;
	private final int size;
	private int front, back;
    private volatile int counter = 0;
    private Semaphore mutex = new Semaphore(1);
	private Semaphore bufferFull = new Semaphore(1);
    private Semaphore bufferEmpty; 

	// Constructor
    public Buffer(int s) { 
    	this.size = s;
        contents = new int[size];
		for (int i=0; i<size; i++) {
			contents[i] = 0;	
		}
		this.front = 0;
        this.back = size - 1;
        this.bufferEmpty = new Semaphore(size);
     }

	 // Put an item into buffer - This method need't lock, because there is only ONE producer
	 public void put(int data) {

		try {
			bufferEmpty.acquire();
		} catch (InterruptedException e) { }
		try {
            mutex.acquire();
       } catch (InterruptedException e) { }
             back = (back + 1) % size;
			 contents[back] = data;
             counter++;
			 System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " Loc " + back + " Count = " + counter);
			 mutex.release(); 
			 bufferFull.release(); 
	}

	// Get an item from buffer
	public int get() { 
		
		int data = 0;
		try {
			bufferFull.acquire();
		} catch (InterruptedException e) { } 
		
			data = contents[front];
			System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " Loc " + front + " Count = " + (counter-1));
            front = (front + 1) % size;
			counter--;
			bufferEmpty.release();
        return data;
	}
}

	
			
	
