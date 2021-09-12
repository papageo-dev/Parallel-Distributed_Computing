package PiClientMultServerTCPSyncHash;

import java.io.*;
import java.net.*;
import java.util.Hashtable;

class ServerThread extends Thread
{
	private Socket dataSocket;
	private InputStream is;
   	private BufferedReader in;
	private OutputStream os;
   	private PrintWriter out;
   	
	private Pi countPi;
	private Hashtable<Long,Double> calculatedPi = new Hashtable<Long,Double>();

   	public ServerThread(Socket socket, Pi cPi, Hashtable<Long,Double> calculatedPi)
   	{
      		dataSocket = socket;
      		try {
			is = dataSocket.getInputStream();
			in = new BufferedReader(new InputStreamReader(is));
			os = dataSocket.getOutputStream();
			out = new PrintWriter(os,true);
			countPi = cPi;
			this.calculatedPi = calculatedPi;
		}
		catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
      		}
    	}

	public void run()
	{
   		String inmsg, outmsg;
		
		try {
			inmsg = in.readLine();
			ServerProtocol app = new ServerProtocol(countPi);
			outmsg = app.processRequest(inmsg, calculatedPi);
			while (!outmsg.equals("EXIT")) {
				out.println(outmsg);
				inmsg = in.readLine();
				outmsg = app.processRequest(inmsg, calculatedPi);		
			}	
			dataSocket.close();	

		} catch (IOException e)	{		
	 		System.out.println("I/O Error " + e);
		}
	}	
}	
			
		
