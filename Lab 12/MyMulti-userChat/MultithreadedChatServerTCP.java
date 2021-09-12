package MyMulti-userChat;

import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class MultithreadedChatServerTCP {
	
	private static final int PORT = 1234;
	
    // Struct to store userNames
    static ArrayList<String> userNames = new ArrayList<>();
    // Struct to store clients/threads/users
    static ArrayList<ServerThread> userThreads = new ArrayList<>();

	// Main method
	public static void main(String args[]) throws IOException {
		
		ServerSocket connectionSocket = connectionSocket = new ServerSocket(PORT);
		
		System.out.println("Server is waiting first client in port: " + PORT);
		
		while (true) {	

			// Accept connection
			Socket dataSocket = connectionSocket.accept();
			System.out.println("New user connected");
			System.out.println("Received request from " + dataSocket.getInetAddress());

			// Create a new thread for new connection
			ServerThread newUser = new ServerThread(dataSocket);
			// Add connection/new user to struct 
			userThreads.add(newUser);
			// Start thread
			newUser.start();
			
		}

	}
	
	
}


