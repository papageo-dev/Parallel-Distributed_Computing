package MyCalculatorValidationByClientServer;

//Calculator client

import java.io.*;
import java.net.*;

public class Client {
	
    //Client should know server's address/name
    private static final String HOST = "localhost";
    private static final int PORT = 1234;
    private static final String EXIT = "!";

    public static void main(String args[]) throws IOException {
    	
        //Try to connect to server
        Socket dataSocket = new Socket(HOST, PORT);

        //Set up input and output streams
        InputStream is = dataSocket.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        OutputStream os = dataSocket.getOutputStream();
        PrintWriter out = new PrintWriter(os, true);
        System.out.println("Connection to " + HOST + " established.");

        //Send request and then receive and process reply
        String inmsg, outmsg;
        ClientValidationProtocol app = new ClientValidationProtocol();
        outmsg = app.prepareRequest();

        while(!outmsg.equals(EXIT)) {
            out.println(outmsg);
            inmsg = in.readLine();
            app.processReply(inmsg);
            outmsg = app.prepareRequest();
        }
        out.println(outmsg);

        //Socket close
        dataSocket.close();
        System.out.println("Data Socket closed.");
    }
}
