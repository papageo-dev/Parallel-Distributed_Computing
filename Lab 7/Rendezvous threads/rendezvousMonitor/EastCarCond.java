package rendezvousMonitor;

public class EastCarCond extends Thread {
	
	//Initialize variables
	private RendezvousMonitor aRandezvous;

	//Constructor
	public EastCarCond (RendezvousMonitor a) {
		aRandezvous = a;
	}

	//Thread Code
	public void run() {
		//Every car will cross the bridge 100 times
		for (int i = 0; i < 100; i++) {
			aRandezvous.eastCross();
        }
	}

}
