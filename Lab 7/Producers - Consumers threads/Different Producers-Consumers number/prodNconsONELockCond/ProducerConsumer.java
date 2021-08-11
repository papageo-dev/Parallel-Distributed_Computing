package prodNconsONELockCond;

public class ProducerConsumer {
	
	public static void main(String[] args) {
		
		int bufferSize = 10;
		int noIterations = 100;
		int producerDelay = 1;
		int consumerDelay = 100;
        int noProds = 3;
        //int noCons = 1;
        
        Producer prod[] = new Producer[noProds];
        //Needn't array of consumers
		//Consumer cons[] = new Consumer[noCons];
		
		// Bounded Buffer
        Buffer buff = new Buffer(bufferSize);
		
		// Producer threads
        for (int i=0; i<noProds; i++) {
        	prod[i] = new Producer(buff, noIterations, producerDelay);
    		prod[i].start();
        }
		
		// Single Consumer thread
        Consumer cons = new Consumer(buff, consumerDelay);
		cons.start();
        
	}
	
}
