package PiCalcRMI_HashMap;

import java.rmi.server.*;
import java.util.Hashtable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.rmi.registry.*;


public class PiServer {
	
	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT; // 1099
	
	// Create a struct(Hashtable) that will contains all calculated Pi values for given number of steps 
    public static Hashtable<Long,Double> calculatedPi = new Hashtable<Long,Double>();
    // Create Lock variable
 	public static Lock lock = new ReentrantLock();
	
	public static void main(String[] args) throws Exception {
				
		// Should be first, especially if server is NOT localhost
        System.setProperty("java.rmi.server.hostname", HOST);
                
        // Remote object creation
		Pi robj = new PiImpl(calculatedPi);
		
		// Choose the appropriate method:
        // get if rmi registry is already running, create otherwise 
		// Registry registry = LocateRegistry.getRegistry(HOST, PORT);
		Registry registry = LocateRegistry.createRegistry(PORT);
		
		// Bind remote object to a name and publish in rmi registry   
		String rmiObjectName = "Pi";
		registry.rebind(rmiObjectName, robj);
		System.out.println("Remote object bounded.");
		
		// Server is running until we press a key
		System.out.println("Press <Enter> for exit.");
		System.in.read();
		
		// Free space and clear rmi registry
		UnicastRemoteObject.unexportObject(robj, true);
		registry.unbind(rmiObjectName);
		System.out.println("Remote object unbounded.");
	}
}

