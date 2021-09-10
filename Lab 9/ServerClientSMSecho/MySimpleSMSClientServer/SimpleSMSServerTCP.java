package MySimpleSMSClientServer;

import java.net.*;
import java.io.*;

// Server do one connection per time, send and receive data and close connection

public class SimpleSMSServerTCP {
	
	// Connection Info
	private static final int PORT = 1234;
	
	public static void main(String args[]) throws IOException {

		// Open a connection server socket(Create, Bind, Listen)
		// Server is listening for client's request(s)
		ServerSocket connectionSocket = new ServerSocket(PORT);
		System.out.println("Server is listening to port: " + PORT);	

		// When a client tries to connect with server
		// Initialize a data socket for communication between server and client(send & receive data)
		Socket dataSocket = connectionSocket.accept();
		System.out.println("Received request from " + dataSocket.getInetAddress());

		// Create and initialize input & output streams
		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		
		// Communication between server & client with streams
		String inmsg, outmsg;
		// Read client's request(message, etc)
		inmsg = in.readLine();
		// Create a copy of server's protocol
		ValidationServerSMSProtocol app = new ValidationServerSMSProtocol();
		// Do something(read, edit, etc) on client's request and create an answer
		outmsg = app.processRequest(inmsg);
		// Send answer to Client
        out.println(outmsg);
		
        // Close connection
		dataSocket.close();
		System.out.println("Data socket closed");	
	}
}			

