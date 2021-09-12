package MyMulti-userChat;
import java.net.*;
import java.io.*;

// Spawn a different thread for receiving messages
// Due to multiple threads used, simultaneous communication is now possible

public class ChatClient {
	
	private static final int PORT = 1234;
    //private static final InetAddress HOST = InetAddress.getLocalHost();
    private static final String HOST = "localhost";

	public static void main(String args[]) throws IOException {
		
		// Connect to server
		Socket dataSocket = new Socket(HOST,PORT);
        System.out.println("Connection to " + HOST + " established");

        // Create & Execute SendThread
		SendThread send = new SendThread(dataSocket);
		Thread thread = new Thread(send);
		thread.start();
		
        // Create & Execute ReceiveThread
		ReceiveThread receive = new ReceiveThread(dataSocket);
		Thread thread2 = new Thread(receive);
		thread2.start();
		
	}
	

}


// SendThread Class
class SendThread implements Runnable {

	private Socket dataSocket;
    private OutputStream os;
    private PrintWriter out;
	
    // Constructor
	public SendThread(Socket socket) throws IOException {
		dataSocket = socket;
		os = dataSocket.getOutputStream();
		out = new PrintWriter(os,true);
	}
	
	// Thread Code
	public void run() {
		
		// Create an instance of client protocol
		ChatClientProtocol app = new ChatClientProtocol();
		
		// Create input buffer
		BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
		
		// Ask for username
		String userName;
		do {
			System.out.println("\nEnter your name: ");
			try {
				userName = user.readLine();
			} catch (IOException e1) {
				userName = "Error: Invalid Username";
				e1.printStackTrace();
			}
		} while (userName == null || userName.isBlank() || userName.isEmpty());
    	
		// Set username & print
		System.out.println("*** Hello " + userName + ". You can start chatting with other people! ***");
        app.setUserName(userName);
        out.println(userName);

		
		try {
			String outmsg;
			outmsg = app.sendMessage();
			while (!outmsg.equals("CLOSE")) {
				out.println(outmsg);
				outmsg = app.sendMessage();
			}
			
			dataSocket.close();
			
		} catch (IOException e){}
	}
	
}


// ReceiveThread Class
class ReceiveThread implements Runnable {

	private Socket dataSocket;
    private InputStream is;
    private BufferedReader in;
	
    // Constructor
	public ReceiveThread(Socket socket) throws IOException {
		dataSocket = socket;
        is = dataSocket.getInputStream();
		in = new BufferedReader(new InputStreamReader(is));
	}
	
	// Thread Code
	public void run() {
		
		try {
			String inmsg;
            ChatClientProtocol app = new ChatClientProtocol();
            inmsg = app.receiveMessage(in.readLine());
            
            // Prints the username after displaying the server's message
            if (app.getUserName() != null) {
                System.out.print("[" + app.getUserName() + "]: ");
            }
            
			while(inmsg != null) {
				inmsg = app.receiveMessage(in.readLine());
			}
		} catch (IOException e){}	
	}
	
}

