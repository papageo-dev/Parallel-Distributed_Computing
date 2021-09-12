package MySMSClientsMultithreadedServer;

import java.io.*;
import java.net.*;

class ServerThread extends Thread {
	
	private Socket dataSocket;
	private InputStream is;
   	private BufferedReader in;
	private OutputStream os;
   	private PrintWriter out;
	//private static final String EXIT = "CLOSE";

	
   	public ServerThread(Socket socket) {

   		dataSocket = socket;
   	    // Initialize input & output streams
      	try {
			is = dataSocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			os = dataSocket.getOutputStream();
			out = new PrintWriter(os,true);
		}
		catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
      	}
      	
    }

   	
   	// Thread Code
	public void run() {
		
		// For debugging
		//System.out.println("- Thread ID = " + Thread.currentThread().getId());
		
		// Servers input and output messages
   		String inmsg, outmsg;
		
   	    // Communication between server & client with streams
		try {
			// Read client's request(message, etc)
			inmsg = in.readLine();
			// Create a copy of server's protocol
			ValidationServerSMSProtocol app = new ValidationServerSMSProtocol();
			// Do something(read, edit, etc) on client's request and create an answer
			outmsg = app.processRequest(inmsg);
			// Server is running until have null as input(for this client)
			while (inmsg != null) {
				// Send answer to Client
				out.println(outmsg);
				inmsg = in.readLine();
				outmsg = app.processRequest(inmsg);
			}		

			// Close connection with this client
			dataSocket.close();
			// For debugging
			System.out.println("- Data socket closed for client: " + Thread.currentThread().getId());

		} catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
		}
		
	}
	
	
}	
			
		
