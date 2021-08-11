package prodONEconsONEsemMux;

import java.util.concurrent.Semaphore;


//One Producer - One Consumer, Lock-Condition

public class Buffer {
	
	private int[] contents;
	private final int size;
	private int front, back;
    private volatile int counter = 0;
	//Need only a semaphore, that won't let producer and consumers change shared counter at the same time
	private Semaphore mutex;
	//private Semaphore bufferFull = new Semaphore(0);
    //private Semaphore bufferEmpty; 

	// Constructor
    public Buffer(int s) { 
    	this.size = s;
        contents = new int[size];
		for (int i=0; i<size; i++) {
			contents[i] = 0;	
		}
		this.front = 0;
        this.back = size - 1;
        this.mutex = new Semaphore(size);
     }

	 // Put an item into buffer
	 public void put(int data) {

		 //Lock CS. Only producer have access and can change shared counter
		 try {
			mutex.acquire();
		 } catch (InterruptedException e) { }
             back = (back + 1) % size;
			 contents[back] = data;
             counter++;
			 System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " Loc " + back + " Count = " + counter);
			 //Signal other thread(consumer) and left lock
			 mutex.release();
	}

	// Get an item from buffer
	public int get() { 
		
		int data = 0;
		//Lock CS. Only producer have access and can change shared counter
		try {
			mutex.acquire();
		} catch (InterruptedException e) { }
			data = contents[front];
			System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " Loc " + front + " Count = " + (counter-1));
            front = (front + 1) % size;
			counter--;
			//Signal other thread(producer) and left lock
			mutex.release();
        return data;
	}
}

	
			
	
