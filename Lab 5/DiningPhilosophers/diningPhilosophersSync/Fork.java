package diningPhilosophersSync;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Fork {

    private int identity; //Fork number

    Fork(int id) {
    	identity = id;
    }

    //Method to get fork
    synchronized void get() {
    	
    }

    //Method to left fork
    synchronized void put() {
    }
}
