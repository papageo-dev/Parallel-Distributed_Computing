package ProducerConsumerRMI_Sem;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientProducer {

	private static final String HOST = "localhost";
	private static final int PORT = Registry.REGISTRY_PORT;
	
	
	public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException{
		
		// Producers' info
		int noItems = 100;
		int producerDelay = 100;
		
		Registry registry = LocateRegistry.getRegistry(HOST,PORT);
		String rmiObjName = "Buffer";
		Interface iface = (Interface) registry.lookup(rmiObjName);

		// Produce(put) items into buffer
        for (int i=0; i<noItems; i++) {
            iface.put(i);
            Thread.sleep((int)(Math.random()*producerDelay));
            System.out.println("Producer " + Thread. currentThread().getId() + " produces item: " + i);
        }

		System.out.println("Time is up, producer " + Thread. currentThread().getId() + " exiting...");	
	}
	
}
