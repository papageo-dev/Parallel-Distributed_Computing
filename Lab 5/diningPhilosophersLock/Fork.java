package diningPhilosophersLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Fork {

    private int identity; //Fork number
    private Lock lock = new ReentrantLock(); //Lock variable 

    Fork(int id) {
    	identity = id;
    }

    //Method to get fork
    void get() {
    	lock.lock();
    }

    //Method to left fork
    void put() {
    	lock.unlock();
    }
}
