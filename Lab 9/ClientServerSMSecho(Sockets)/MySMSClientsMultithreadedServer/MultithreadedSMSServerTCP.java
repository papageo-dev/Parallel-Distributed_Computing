package MySMSClientsMultithreadedServer;

import java.net.*;
import java.io.*;

// Server is serving multiple clients at a time

public class MultithreadedSMSServerTCP {
	
	private static final int PORT = 1234;
	
	public static void main(String args[]) throws IOException {

		// Open a connection server socket(Create, Bind, Listen)
		// Server is listening for client's request(s)
		ServerSocket connectionSocket = connectionSocket = new ServerSocket(PORT);
		
		// Server is running forever(for infinity clients/threads)
		while (true) {	

			// When a client tries to connect with server
			// Initialize a data socket for communication between server and client(send & receive data)
			System.out.println("Server is listening to port: " + PORT);
			Socket dataSocket = connectionSocket.accept();
			System.out.println("Received request from " + dataSocket.getInetAddress());

			// Create and execute a thread
			// Give the dataSocket to a thread to handle and wait for other clients to serve 
			ServerThread sthread = new ServerThread(dataSocket);
			sthread.start();
		}
	}
}


