package ProducerConsumerRMI_MonSync;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientConsumer {

	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT;
	
	
	public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException{
		
		// Producers' info
		int noItems = 100;
		int consumerDelay = 50;
		
		Registry registry = LocateRegistry.getRegistry(HOST,PORT);
		String rmiObjName = "Buffer";
		Interface iface = (Interface) registry.lookup(rmiObjName);

		// Consume(get) items from buffer
        for (int i=0; i<noItems; i++) {
            iface.get();
            Thread.sleep((int)(Math.random()*consumerDelay));
            System.out.println("Consumer " + Thread. currentThread().getId() + " consumes item: " + i);
        }

		System.out.println("Time is up, consumer " + Thread. currentThread().getId() + " exiting...");	
	}
	
}
