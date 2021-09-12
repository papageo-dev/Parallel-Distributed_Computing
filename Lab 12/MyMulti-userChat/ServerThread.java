package MyMulti-userChat;

import java.io.*;
import java.net.*;

class ServerThread extends Thread
{
	private Socket myDataSocket;
    
	private InputStream is;
   	private BufferedReader in;
	private OutputStream os;
   	private PrintWriter out;
   	
   	// Message/Word to stop chat
	private static final String EXIT = "CLOSE";

	// Constructor
   	public ServerThread(Socket socket)
   	{
   		this.myDataSocket = socket;

      	try {
			is = myDataSocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			os = myDataSocket.getOutputStream();
			out = new PrintWriter(os,true);
		}
		catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
      		}
    }

   	// Thread Code
	public void run()
	{
   		String inmsg, outmsg;
		
		try {
			// Create an instance of server's protocol
			ServerProtocol app = new ServerProtocol();
			
			// Print all connected users
			printUsers(app);
			
			// Add username for this user
            String userName = in.readLine();
            app.addUserName(userName);
            
            // Print a message for new connection
            outmsg = "*** New user connected: " + userName;
            // Process message to other users
            app.processRequest(outmsg, this);
			
            // Wait for users' messages and send them to all other users
			do  {
				// Read user's message
				inmsg = in.readLine();
				outmsg = "[" + userName + "]: " + inmsg;
				// Process message to other users
				app.processRequest(outmsg, this);
			} while (!outmsg.equals(EXIT));	 	

			// If user quit, remove from list and close it's dataSocket
			app.removeUser(userName, this);
            myDataSocket.close();
            
            // Send info message to other users
            outmsg = "*** " + userName + " has quitted.";
            app.processRequest(outmsg, this);
			System.out.println("*** Data socket closed");

		} catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
		}
	}
	
    // Sends a list of all connected users to the new connected user
    void printUsers(ServerProtocol app) {
        if (app.hasUsers()) {
        	out.println("*** Connected users: " + app.getUserNames());
        } else {
        	out.println("*** No other users connected");
        }
    }
 
    // Sends a message to the client.
    void sendMessage(String message) {
    	out.println(message);
    }
    
}	
			
		
