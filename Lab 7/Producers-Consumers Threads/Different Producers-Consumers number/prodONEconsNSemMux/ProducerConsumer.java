package prodONEconsNSemMux;

public class ProducerConsumer {
	
	public static void main(String[] args) {
		
		int bufferSize = 10;
		int noIterations = 100;
		int producerDelay = 1;
		int consumerDelay = 100;
        //int noProds = 3;
        int noCons = 10;
        //Needn't array of producers
        //Producer prod[] = new Producer[noProds];
		Consumer cons[] = new Consumer[noCons];
		
		// Bounded Buffer
        Buffer buff = new Buffer(bufferSize);
		
		// Single Producer thread
        Producer prod = new Producer(buff, noIterations, producerDelay);
		prod.start();
		
		// Consumer threads
        for (int j=0; j<noCons; j++) { 
        	cons[j] = new Consumer(buff, consumerDelay);
			cons[j].start();
        }
	}
	
}
