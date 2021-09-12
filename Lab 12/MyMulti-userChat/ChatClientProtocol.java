package MyMulti-userChat;
import java.net.*;
import java.io.*;

public class ChatClientProtocol {

	BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
	private String userName;
	
	// Method to send a message
	public String sendMessage() throws IOException {
	
        //System.out.print("Send message, CLOSE for exit:");
		String theOutput = user.readLine();
		return theOutput;
    }

	// Method to receive a message
	public String receiveMessage(String theInput) throws IOException {
	
		System.out.println(theInput);
		//System.out.println("Received message: " + theInput);
        //System.out.print("Send a reply, CLOSE for exit:");
		return theInput;
	}
	
    // Set Username
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    // Return Username
    public String getUserName() {
        return this.userName;
    }
}