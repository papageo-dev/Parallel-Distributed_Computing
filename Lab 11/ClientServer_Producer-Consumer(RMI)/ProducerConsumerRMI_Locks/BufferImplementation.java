package ProducerConsumerRMI_Locks;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferImplementation extends UnicastRemoteObject implements Interface {

	private static final long serialVersionUID = 1;
	
	// Variable declaration
    private int[] contents;
    private final int size;
    private int front, back;
    // Shared counter between Consumers and Producers
    private static volatile int counter = 0;
    // Lock variables
    private Lock lock = new ReentrantLock();
    private final Condition bufferFull = lock.newCondition();
    private final Condition bufferEmpty = lock.newCondition();
		
	public BufferImplementation(int s) throws RemoteException {
    	this.size = s;
        contents = new int[size];
		for (int i=0; i<size; i++) {
			contents[i] = 0;	
		}
		this.front = 0;
        this.back = size - 1;
	}

	
	// Method to put(produce) an item into buffer
	public void put(int data) throws RemoteException {
		
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

	
	// Method to get(consume) an item from buffer
	public int get() throws RemoteException {
		
		int data = 0;
		lock.lock();
        try { 
        	while (counter == 0) {
        		//System.out.println("The buffer is empty");
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
