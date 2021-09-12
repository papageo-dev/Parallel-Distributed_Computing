package ProducerConsumerRMI_Sem;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;

public class Server {
	
	private static final String HOST = "localhost";
    private static final int PORT = Registry.REGISTRY_PORT;
    
    // Buffer size variable
    private static int bufferSize = 1000000;
	
	public static void main(String[] args) throws Exception {

		System.setProperty("java.rmi.server.hostname", HOST);		
		BufferImplementation rObj = new BufferImplementation(bufferSize);

        // Registry registry = LocateRegistry.getRegistry(HOST, PORT);
		Registry reg = LocateRegistry.createRegistry(PORT);

		String rmiObjName = "Buffer";
		reg.rebind(rmiObjName, rObj);
		System.out.println("Remote object bounded.");
 
        System.out.println("Press <Enter> for exit.");
		System.in.read();
		
		UnicastRemoteObject.unexportObject(rObj, true);
		reg.unbind(rmiObjName);
		System.out.println("Remote object unbounded.");
	}

}
