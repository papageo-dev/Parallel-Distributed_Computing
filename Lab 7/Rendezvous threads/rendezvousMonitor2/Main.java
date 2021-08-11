package rendezvousMonitor2;

public class Main {
	
	public static void main(String[] args) {
		
		RendezvousMonitor x = new RendezvousMonitor();
		
		//Total number of cars(West & East)
		int westCars = 2; 
		int eastCars = 2;
		
		//Initialize arrays of threads(West & East)
        WestCarCond wCarsThreads[] = new WestCarCond[westCars];
        EastCarCond eCarsThreads[] = new EastCarCond[eastCars];

        //Execute West threads
        for (int i=0; i<westCars; i++) {
        	wCarsThreads[i] = new WestCarCond(x);
            wCarsThreads[i].start(); 
        }
        
        //Execute East threads
        for (int i=0; i<eastCars; i++) {
        	eCarsThreads[i] = new EastCarCond(x);
            eCarsThreads[i].start(); 
        }
           	
        //Wait all West Threads to terminate
        for (int i=0; i<westCars; i++) {
      		try { 
        		wCarsThreads[i].join(); 
       		} catch (InterruptedException e) { }
      	}
        
        //Wait all East Threads to terminate
        for (int i=0; i<eastCars; i++) {
      		try { 
        		eCarsThreads[i].join(); 
       		} catch (InterruptedException e) { }
      	}
        		
    }
		
}