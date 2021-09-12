package PiClientMultServerTCPSyncHash;

import java.net.*;
import java.util.Hashtable;
import java.io.*;

public class MultithreadedPiServerTCP {
	
	private static final int PORT = 1234;
	public static Pi n = new Pi();
	
	// Create a struct(Hashtable that will contains all calculated Pi values for given number of steps 
	public static Hashtable<Long,Double> calculatedPi = new Hashtable<Long,Double>();

	public static void main(String args[]) throws IOException {


		ServerSocket connectionSocket = connectionSocket = new ServerSocket(PORT);
		
		while (true) {	

			System.out.println("Server is listening to port: " + PORT);
			Socket dataSocket = connectionSocket.accept();
			System.out.println("Received request from " + dataSocket.getInetAddress());

			ServerThread sthread = new ServerThread(dataSocket, n, calculatedPi);
			sthread.start();
		}
	}
}


