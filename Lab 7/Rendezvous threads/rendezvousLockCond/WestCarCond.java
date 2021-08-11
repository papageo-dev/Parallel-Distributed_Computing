package rendezvousLockCond;

class WestCarCond extends Thread {
	
	//Initialize variables
	private RendezvousLockCond aRandezvous;

	//Constructor
	public WestCarCond (RendezvousLockCond a) {
		aRandezvous = a;
	}

	//Thread Code
	public void run() {
		//Every car will cross the bridge 100 times
		for (int i = 0; i < 100; i++) {
			aRandezvous.westCross();
        }
	}

}