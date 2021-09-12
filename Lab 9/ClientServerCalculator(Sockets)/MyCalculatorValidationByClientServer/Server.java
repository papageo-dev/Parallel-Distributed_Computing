package MyCalculatorValidationByClientServer;

//Calculator Server

import java.net.*;
import java.io.*;

public class Server {
	
    private static final int PORT = 1234;

    public static void main(String args[]) throws IOException {
    	
        //Establish listening server socket
        ServerSocket connectionSocket = new ServerSocket(PORT);
        System.out.println("Server is listening to port: " + PORT);

        while(true) {
            //Wait for connection and produce actual socket
            Socket dataSocket = connectionSocket.accept();
            System.out.println("Received request from " + dataSocket.getInetAddress());

            //Server Thread
            ServerThread sthread = new ServerThread(dataSocket);
            sthread.start();
        }
    }

}
