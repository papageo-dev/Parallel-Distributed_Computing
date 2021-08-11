package rendezvousMonitor2;

//One car(East or West) enters the bridge

public class RendezvousMonitor {

	//flags
	//private boolean westBound = true;
	//private boolean eastBound = false;
	
	private int wCount = 1;
	private int eCount = 0;
	
	int waitscale = 100;
    int inscale = 10000;
       
    synchronized void westCross() {
    	
    	//Car arrival and waiting
        try { 
        	Thread.sleep((int)(Math.random()*waitscale));
        } catch (InterruptedException e) { }
        System.out.println(Thread.currentThread().getName()+"(West) --- wait");
        
        try {
        	while (wCount!=1) {
        		wait();
        		wCount++;
        	}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        	
        //Car entering
        System.out.println(Thread.currentThread().getName()+"(West) >>>     enter");
        
        //Car passing
        System.out.println(Thread.currentThread().getName()+"(West) ---     passing");
        passing();
            
        //Car exiting
        System.out.println(Thread.currentThread().getName()+"(West) <<<         exit");
        
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
        while (eCount!=1) {
        	wait();
        	eCount++;
        }
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	
        //Car entering
        System.out.println(Thread.currentThread().getName()+"(East) >>>     enter");
        
        //Car passing
        System.out.println(Thread.currentThread().getName()+"(East) ---     passing");
        passing();
            
        //Car exiting
        System.out.println(Thread.currentThread().getName()+"(East) <<<         exit");
        
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