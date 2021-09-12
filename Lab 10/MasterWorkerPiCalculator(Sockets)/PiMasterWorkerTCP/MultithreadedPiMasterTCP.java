package PiMasterWorkerTCP;

import java.net.*;
import java.io.*;
import java.lang.management.ThreadInfo;

public class MultithreadedPiMasterTCP {
	
	private static final int PORT = 1234;
	
	private static final int numWorkers = 4;
	public static Pi pi = new Pi(10000);

	
	public static void main(String args[]) throws IOException {

		ServerSocket connectionSocket = connectionSocket = new ServerSocket(PORT);
		MasterThread mthread[] = new MasterThread[numWorkers];
		
		// Wait for workers to connect and create/execute a thread for each worker
		for (int i=0; i<numWorkers; i++) {
			System.out.println("Master is listening to port: " + PORT);
			Socket dataSocket = connectionSocket.accept();
			System.out.println("Sending request to: " + dataSocket.getInetAddress());
			System.out.println("Connected workers: " + i + "/" + numWorkers);
			mthread[i] = new MasterThread(dataSocket, i, pi);
			mthread[i].start();	
		}
		System.out.println("All workers started computing Pi...\n");
		
		// Wait for all workers to end the calculation of Pi
		for (int i=0; i<numWorkers; i++) {
			try {
				mthread[i].join();
           		} catch (InterruptedException e) {}
		}
		
		// Print the final Pi value
		pi.printResult(); 	 	
	}
	
	
}


