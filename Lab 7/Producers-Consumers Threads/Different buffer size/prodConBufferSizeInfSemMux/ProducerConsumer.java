package prodConBufferSizeInfSemMux;

public class ProducerConsumer {
	
	public static void main(String[] args) {
		
		int bufferSize = 99999; //Give a very big number "Infinity Loop"
		int noIterations = 20;
		int producerDelay = 1;
		int consumerDelay = 100;
        int noProds = 5;
        int noCons = 10;
        Producer prod[] = new Producer[noProds];
		Consumer cons[] = new Consumer[noCons];
		
		// Bounded Buffer
        Buffer buff = new Buffer(bufferSize);
		
		// Producer threads
        for (int i=0; i<noProds; i++) {
        	prod[i] = new Producer(buff, noIterations, producerDelay);
			prod[i].start();
		}

		// Consumer threads
        for (int j=0; j<noCons; j++) { 
        	cons[j] = new Consumer(buff, consumerDelay);
			cons[j].start();
        }
	}
	
}
