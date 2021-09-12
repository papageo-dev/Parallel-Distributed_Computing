package MyCalculatorAskClientServerSerializable;

import java.io.*;
import java.net.*;

public class Client { 
	
   private static final String HOST = "localhost";
   private static final int PORT = 1234;

   public static void main(String[] arg) throws IOException, ClassNotFoundException {
	   
	   Socket socketConnection = new Socket(HOST, PORT);

       ObjectOutputStream clientOutputStream = new
          ObjectOutputStream(socketConnection.getOutputStream());
       ObjectInputStream clientInputStream = new 
          ObjectInputStream(socketConnection.getInputStream());

       BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
    

       // Check if user input is correct
       String theInput;
       CheckInput checkInput = new CheckInput();
       do {
    		System.out.print("Enter <number>' '<op>' '<number> or '!' for exit: ");
    		theInput = user.readLine();		
       } while (!checkInput.isValid(theInput));
       
       // Split the input and create a request and send to server
       String[] splited = theInput.trim().split("\\s+");  
       Request req = new Request(Integer.parseInt(splited[0]), splited[1], Integer.parseInt(splited[2]));

       clientOutputStream.writeObject(req);
       Reply rep = (Reply)clientInputStream.readObject();

	   System.out.println("Reply opcode = " + rep .getOpcode());
       System.out.println("Reply value = " + rep .getValue());
         
       clientOutputStream.close();
       clientInputStream.close();

	 socketConnection.close();
   }
   
   
}

