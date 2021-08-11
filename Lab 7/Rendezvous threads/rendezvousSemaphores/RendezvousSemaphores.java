package rendezvousSemaphores;

import java.util.concurrent.Semaphore;

//One car(East or West) enters the bridge

public class RendezvousSemaphores {

	//Initialize Semaphores
    private Semaphore semWest = new Semaphore(1);
    private Semaphore semEast = new Semaphore(0);
	
	int waitscale = 100;
    int inscale = 10000;
       
    void westCross() { 
    	//Car arrival and waiting
        try { 
        	Thread.sleep((int)(Math.random()*waitscale));
        } catch (InterruptedException e) { }
        System.out.println(Thread.currentThread().getName()+"(West) --- wait");
        
        try {
        	//If semaphore value == 1, thread will make it 0 and run the CS, else go sleep
        	semWest.acquire();
        } catch (InterruptedException e) { }
        	
        	//Car entering
            System.out.println(Thread.currentThread().getName()+"(West) >>>     enter");
            
            //Car passing
            System.out.println(Thread.currentThread().getName()+"(West) --- 	passing");
            passing();
            
            //Car exiting
            System.out.println(Thread.currentThread().getName()+"(West) <<<         exit");
            //Release semaphore(value == 1) and signal other threads
            semEast.release();
    }
    
    void eastCross () { 
    	//Car arrival and waiting
        try { 
        	Thread.sleep((int)(Math.random()*waitscale));
        } catch (InterruptedException e) { }
        System.out.println(Thread.currentThread().getName()+"(East) --- wait");
        
        try { 
        	//If semaphore value == 1, thread will make it 0 and run the CS, else go sleep
        	semEast.acquire();
        } catch (InterruptedException e) { }
        	
        	//Car entering
            System.out.println(Thread.currentThread().getName()+"(East) >>>     enter");
            
            //Car passing
            System.out.println(Thread.currentThread().getName()+"(East) --- 	passing");
            passing();
            
            //Car exiting
            System.out.println(Thread.currentThread().getName()+"(East) <<<         exit");
            //Release semaphore(value == 1) and signal other threads
            semWest.release();
    }
        
    //Calculate passing time
    void passing() { 
    	try { 
    		Thread.sleep((int)(Math.random()*inscale));
        } catch (InterruptedException e) { }
    }
}