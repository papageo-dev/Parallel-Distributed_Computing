package prodConsBufferSize1MonitorSync;

public class ProducerConsumer {
	
	public static void main(String[] args) {
		
		int noIterations = 20;
		int producerDelay = 1;
		int consumerDelay = 100;
		
        int noProds = 3; //Total Producers
        int noCons = 3; //Total Consumers
        
        //Array of Producers
        Producer prod[] = new Producer[noProds];
        //Array of Consumers
		Consumer cons[] = new Consumer[noCons];
		
		// Bounded Buffer
        Buffer buff = new Buffer();
		
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
