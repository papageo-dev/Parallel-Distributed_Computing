package ProducerConsumerRMI_Sem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferImplementation extends UnicastRemoteObject implements Interface {

	private static final long serialVersionUID = 1;
	
	// Variable declaration
	private int[] contents;
	private int size;
	private int front, back;
	// Semaphores
	private Semaphore mutex = new Semaphore(1);
	private Semaphore bufferFull = new Semaphore(0);
    private Semaphore bufferEmpty; 
    // Shared counter between Consumers and Producers
    private static volatile int counter = 0;
		
	public BufferImplementation(int s) throws RemoteException {
		this.size = s;
        contents = new int[size];
		for (int i=0; i<size; i++) {
			contents[i] = 0;
		}
		this.front = 0;
        this.back = size-1;	
        this.bufferEmpty = new Semaphore(size);
	}

	
	// Method to put(produce) an item into buffer
	public void put(int data) throws RemoteException {
		
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

	
	// Method to get(consume) an item from buffer
	public int get() throws RemoteException {
		
		int data = 0;
		try {
			bufferFull.acquire();
			} catch (InterruptedException e) { } 
		try {
             mutex.acquire();
            } catch (InterruptedException e) { }
		data = contents[front];
		System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " Loc " + front + " Count = " + (counter-1));	    front = (front + 1) % size;	
	    counter--;
		mutex.release();		
		bufferEmpty.release();
        return data;
        
	}

	
}
