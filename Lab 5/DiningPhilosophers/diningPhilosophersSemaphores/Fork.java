package diningPhilosophersSemaphores;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Fork {

    private int identity; //Fork number
    private Semaphore mutex = new Semaphore(1);

    Fork(int id) {
    	identity = id;
    }

    //Method to get fork
    void get() {
    	try {
			mutex.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //Method to left fork
    void put() {
    	mutex.release();
    }
}
