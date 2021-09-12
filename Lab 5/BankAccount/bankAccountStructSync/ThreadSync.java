package bankAccountStructSync;
import java.util.Random;

public class ThreadSync
{
	public static void main(String[] args)
	{
		//Total accounts number
        int totalAcc = 100;
        
		// bank account creation
        BankAccountSynchronized[] accArray = new BankAccountSynchronized[totalAcc];
        
        //Accounts initialization
        for (int i=0; i<totalAcc; i++) {
        	//All accounts created with 1000$ balance
        	accArray[i] = new BankAccountSynchronized(1000);
        }
        
        int rThreads = 1000; //Read threads number
        int wThreads = 50; //Write threads number    

        // Create instance of Random class
        Random random = new Random();
        
		// Reader thread creation (array of threads)
   		Reader[] read = new Reader[rThreads];
   		//Create and start Reader threads
   		for (int i=0; i<rThreads; i++) {
   			
   			//Choose a random account from 0 to totalAcc
   			int rand = random.nextInt(totalAcc);
   	        
   			read[i] = new Reader(accArray, rand); //Create a Reader thread
   			read[i].start(); //Start/Run Reader thread
   		}  	
   		
   		// Reader thread creation (array of threads)
   		Writer[] write = new Writer[wThreads];
   		//Create and start Writer threads
   		for (int i=0; i<wThreads; i++) {
   			
   			//Choose a random account from 0 to totalAcc
   			int rand = random.nextInt(totalAcc);

   			write[i] = new Writer(accArray, rand ); //Create a Writer thread
   			write[i].start(); //Start/Run Writer thread
   		} 
    

		// Wait for Reader threads to finish
   		for (int i=0; i<rThreads; i++) {
   			try {
   				read[i].join();
   			} catch (InterruptedException e) {
   				e.printStackTrace();
   			}
   		}
   		
   	    // Wait for Writer threads to finish
   		for (int i=0; i<wThreads; i++) {
   			try {
   				write[i].join();
   			} catch (InterruptedException e) {
   				e.printStackTrace();
   			}
   		}
	}
}