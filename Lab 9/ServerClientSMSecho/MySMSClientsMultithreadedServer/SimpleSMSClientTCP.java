package MySMSClientsMultithreadedServer;

import java.net.*;
import java.io.*;

public class SimpleSMSClientTCP {
	
	// Connection Info
    private static final String HOST = "localhost";
	private static final int PORT = 1234;
	
	public static void main(String args[]) throws IOException {

		// Initialize a data socket for communication between server and client(send & receive data)
		//InetAddress address = InetAddress.getByName(HOST);
        Socket dataSocket = new Socket(HOST, PORT);
        
        // Create and initialize input & output streams
		InputStream is = dataSocket.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(is));
		OutputStream os = dataSocket.getOutputStream();
		PrintWriter out = new PrintWriter(os,true);
		
		System.out.println("Connection to " + HOST + " established");

		// Communication between server & client with streams
		String inmsg, outmsg;
		// Create a copy of client's protocol
		ClientSMSProtocol app = new ClientSMSProtocol();
		// Prepare request(message, question, etc) to sent
        outmsg = app.prepareRequest();
        
        while (!outmsg.equals(null)) {
        	// Send request(message, question, etc) to server
    		out.println(outmsg);
    		// Read server's answer(message, etc)
    		inmsg = in.readLine();
    		// Print server's answer(message, etc)
    		app.processReply(inmsg);
    		// Prepare request(message, question, etc) to sent
            outmsg = app.prepareRequest();
        }
        
		// Close connection
		dataSocket.close();
		System.out.println("Data Socket closed");
	}
}			

