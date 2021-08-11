package rendezvousMonitor;

//One car(East or West) enters the bridge

public class RendezvousMonitor {

	//flags
	private boolean westBound = true;
	private boolean eastBound = false;
	
	int waitscale = 100;
    int inscale = 10000;
       
    synchronized void westCross() { 
    	//Car arrival and waiting
        try { 
        	Thread.sleep((int)(Math.random()*waitscale));
        } catch (InterruptedException e) { }
        System.out.println(Thread.currentThread().getName()+"(West) --- wait");
        
        try {
        	while (!westBound) wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        	
        //Car entering
        System.out.println(Thread.currentThread().getName()+"(West) >>>     enter");
        //Change West condition
        westBound = false;
            
        //Car passing
        System.out.println(Thread.currentThread().getName()+"(West) ---     passing");
        passing();
            
        //Car exiting
        System.out.println(Thread.currentThread().getName()+"(West) <<<         exit");
        
        //Change West condition - Let West cars try to cross the bridge
        eastBound = true;
        //Signal other threads
        notifyAll();
    }
    
    synchronized void eastCross () { 
    	//Car arrival and waiting
        try { 
        	Thread.sleep((int)(Math.random()*waitscale));
        } catch (InterruptedException e) { }
        System.out.println(Thread.currentThread().getName()+"(East) --- wait");
        
        try {
        while (!eastBound) wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
        //Car entering
        System.out.println(Thread.currentThread().getName()+"(East) >>>     enter");
        //Change East condition
        eastBound = false;
        
        //Car passing
        System.out.println(Thread.currentThread().getName()+"(East) ---     passing");
        passing();
            
        //Car exiting
        System.out.println(Thread.currentThread().getName()+"(East) <<<         exit");
        
        //Change East condition - Let East cars try to cross the bridge
        westBound = true;
        //Signal other threads
        notifyAll();
    }
        
    //Calculate passing time
    void passing() { 
    	try { 
    		Thread.sleep((int)(Math.random()*inscale));
        } catch (InterruptedException e) { }
    }
}