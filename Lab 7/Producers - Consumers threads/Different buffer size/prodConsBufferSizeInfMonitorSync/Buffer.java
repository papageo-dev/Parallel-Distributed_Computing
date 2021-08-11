package prodConsBufferSizeInfMonitorSync;

//"Infinity" Buffer size, Monitor(Synchronized methods)

public class Buffer {
	
	private int[] contents;
	//private final int size;
	private final int infBufferS;
	private int front, back;
    private volatile int counter = 0;

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
	 public synchronized void put(int data) {

		 //Lock the Critical Section(shared counter) with Synchronized method - Only ONE producer can change counter
		 
		 //Needn't to check if buffer is full, because is "infinity"
		 /*
		 while (counter == size) {
				try {
					wait();
				} catch (InterruptedException e) {}
			}
		 */
		//Produce an item and increase shared counter by 1
        back = (back + 1); //Needn't "% size" 
	    contents[back] = data;
        counter++;
	    System.out.println("Prod " + Thread.currentThread().getName() + " No "+ data + " Loc " + back + " - Count = " + counter);
		
	    //Signal other threads
	    notifyAll();
			 
	}

	// Get an item from buffer
	public synchronized int get() { 
		
		int data = 0;

		//While buffer is empty, wait
		while (counter == 0) {
			try {
				wait();
			}
			catch (InterruptedException e) {}
		}
        //Consume an item and decrease shared counter by 1
		data = contents[front];
		System.out.println("  Cons " + Thread.currentThread().getName() + " No "+ data + " Loc " + front + " Count = " + (counter-1));
        front = (front + 1);
		counter--;
		
		notifyAll();	
        return data;
	}
}

	
			
	
