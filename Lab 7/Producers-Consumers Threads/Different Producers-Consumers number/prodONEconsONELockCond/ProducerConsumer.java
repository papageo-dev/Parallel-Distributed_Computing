package prodONEconsONELockCond;

public class ProducerConsumer {
	
	public static void main(String[] args) {
		
		int bufferSize = 5;
		int noIterations = 20;
		int producerDelay = 10;
		int consumerDelay = 100;
        //int noProds = 1;
        //int noCons = 1;
		//Needn't array of Producers/Consumers
        //Producer prod[] = new Producer[noProds];
		//Consumer cons[] = new Consumer[noCons];
		
		// Bounded Buffer
        Buffer buff = new Buffer(bufferSize);
		
		// Single Producer thread
        Producer prod = new Producer(buff, noIterations, producerDelay);
		prod.start();

		// Single Consumer thread 
        Consumer cons = new Consumer(buff, consumerDelay);
		cons.start();
        
	}
	
}
