package MyCalculatorAskClientServerSerializable;

import java.io.*;
import java.net.*;

public class Server {
   private static final int PORT = 1234;

   public static void main(String[] arg) throws IOException, ClassNotFoundException {

         ServerSocket socketConnection = new ServerSocket(PORT);

         System.out.println("Server Waiting");

         Socket pipe = socketConnection.accept();

         ObjectInputStream serverInputStream = new    
            ObjectInputStream(pipe.getInputStream());

         ObjectOutputStream serverOutputStream = new 
            ObjectOutputStream(pipe.getOutputStream());

	 ServerProtocol serverProt = new ServerProtocol();

         Request req = (Request) serverInputStream.readObject();
	 Reply rep = serverProt.processRequest(req);
         serverOutputStream.writeObject(rep);

         serverInputStream.close();
         serverOutputStream.close();

	 pipe.close();

   }

}

