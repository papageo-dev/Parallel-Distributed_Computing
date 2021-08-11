package rendezvousLockCond;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

//One car(East or West) enters the bridge

public class RendezvousLockCond {

	//Lock variable
    private Lock lock = new ReentrantLock(); 
    //Condition variables
	private Condition cWest = lock.newCondition();
	private Condition cEast = lock.newCondition();
	//flags
	private boolean westBound = true;
	private boolean eastBound = false;
	
	int waitscale = 100;
    int inscale = 10000;
       
    void westCross() { 
    	//Car arrival and waiting
        try { 
        	Thread.sleep((int)(Math.random()*waitscale));
        } catch (InterruptedException e) { }
        System.out.println(Thread.currentThread().getName()+"(West) --- wait");
        
        //Lock Critical Section(Bridge)
        lock.lock();
        try { 
        	while (!westBound) {
        		try {
        			cWest.await();
        			} catch (InterruptedException e) { }
    		} 
        	
        	//Car entering
            System.out.println(Thread.currentThread().getName()+"(West) >>>     enter");
            westBound = false;
            
            //Car passing
            System.out.println(Thread.currentThread().getName()+"(West) ---     passing");
            passing();
            
            //Car exiting
            System.out.println(Thread.currentThread().getName()+"(West) <<<         exit");
            eastBound = true;
            
            //Signal other threads
            cEast.signal();
        } finally {
        	//Unlock Critical Section(Bridge)
        	lock.unlock() ;
        }
    }
    
    void eastCross () { 
    	//Car arrival and waiting
        try { 
        	Thread.sleep((int)(Math.random()*waitscale));
        } catch (InterruptedException e) { }
        System.out.println(Thread.currentThread().getName()+"(East) --- wait");
        
        //Lock Critical Section(Bridge)
        lock.lock();
        try { 
        	while (!eastBound) {
        		try {
        			cEast.await();
        			} catch (InterruptedException e) { }
    		} 
        	
        	//Car entering
            System.out.println(Thread.currentThread().getName()+"(East) >>>     enter");
            eastBound = false;
            
            //Car passing
            System.out.println(Thread.currentThread().getName()+"(East) ---     passing");
            passing();
            
            //Car exiting
            System.out.println(Thread.currentThread().getName()+"(East) <<<         exit");
            westBound = true;
            
            //Signal other threads
            cWest.signal();
        } finally {
        	//Unlock Critical Section(Bridge)
        	lock.unlock() ;
        }
    }
        
    //Calculate passing time
    void passing() { 
    	try { 
    		Thread.sleep((int)(Math.random()*inscale));
        } catch (InterruptedException e) { }
    }
}