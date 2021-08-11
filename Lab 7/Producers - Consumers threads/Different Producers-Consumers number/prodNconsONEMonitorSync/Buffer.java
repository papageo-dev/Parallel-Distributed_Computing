package prodNconsONEMonitorSync;

public class Buffer {
	
	private int[] contents;
	private final int size;
	private int front, back, counter = 0;

	// Constructor
    public Buffer(int s) { 
    	this.size = s;
        contents = new int[size];
		for (int i=0; i<size; i++) {
			contents[i] = 0;		
		}
		this.front = 0;
        this.back = -1;
     }

	// Put an item into buffer
	public synchronized void put(int data) {
		
		while (counter == size) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		back = (back + 1) % size;
		contents[back] = data;
        counter++;
		System.out.println("Prod " + Thread.currentThread().getName() + " - Item " + data + " added in loc " + back + ". Count = " + counter);
		if (counter==size) {
			System.out.println("The buffer is full");
		}
		notifyAll();
	}

	// Get an item from buffer
	public synchronized int get() {
		
		while (counter == 0) {
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
		int data = contents[front];
        front = (front + 1) % size;
		counter--;
		System.out.println("Cons " + Thread.currentThread().getName() + " - Item " + data + " removed from loc " + front + ". Count = " + counter);		
		if (counter==0) {
			System.out.println("The buffer is empty");	
		}
		notifyAll();
		return data;
	}
	
}

	
			
	
