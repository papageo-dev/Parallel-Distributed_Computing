package MyCalculatorValidationByClientServer;

import java.io.*;
import java.net.*;

class ServerThread extends Thread {
    private Socket dataSocket;
    private InputStream is;
    private BufferedReader in;
    private OutputStream os;
    private PrintWriter out;
    private static final String EXIT = "!";

    public ServerThread(Socket socket)
    {
        dataSocket = socket;
        try {
            is = dataSocket.getInputStream();
            in = new BufferedReader(new InputStreamReader(is));
            os = dataSocket.getOutputStream();
            out = new PrintWriter(os,true);
        }
        catch(IOException e) {
            System.out.println("I/O Error " + e);
        }
    }

    public void run() {
    	
        String inmsg, outmsg;

        try {
            inmsg = in.readLine();
            ServerProtocol app = new ServerProtocol();
            while(!EXIT.equals(inmsg)) {
                outmsg = app.processRequest(inmsg);
                out.println(outmsg);
                inmsg = in.readLine();
            }
            dataSocket.close();
        }
        catch (IOException e) {
            System.out.println("I/O Error --> " + e);
        }
    }
}