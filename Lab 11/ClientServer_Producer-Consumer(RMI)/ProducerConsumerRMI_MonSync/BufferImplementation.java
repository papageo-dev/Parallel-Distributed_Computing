package ProducerConsumerRMI_MonSync;

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
    
	// Constructor	
	public BufferImplementation(int s) throws RemoteException {
    	this.size = s;
        contents = new int[size];
		for (int i=0; i<size; i++) {
			contents[i] = 0;		
		}
		this.front = 0;
        this.back = -1;
	}

	
	// Method to put(produce) an item into buffer
	public synchronized void put(int data) throws RemoteException {
		
		while (counter == size) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		back = (back + 1) % size;
		contents[back] = data;
        counter++;
		System.out.println("Prod: Item " + data + " added in loc " + back + ". Count = " + counter);
		if (counter==size) {
			System.out.println("The buffer is full");
		}
		notifyAll();
	
	}

	
	// Method to get(consume) an item from buffer
	public synchronized int get() throws RemoteException {
		
		while (counter == 0) {
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
		int data = contents[front];
        front = (front + 1) % size;
		counter--;
		System.out.println("   Cons: Item " + data + " removed from loc " + front + ". Count = " + counter);		
		if (counter==0) {
			System.out.println("The buffer is empty");	
		}
		notifyAll();
		return data;
      
	}

	
}
