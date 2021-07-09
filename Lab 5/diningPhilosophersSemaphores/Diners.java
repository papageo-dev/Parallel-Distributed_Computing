package diningPhilosophersSemaphores;

public class Diners {

     static final int numphils = 10; //Total number of philosophers
     static final int sleeptime = 5000; //Sleeping time
    
     public static void main(String[] args) {
    	 
    	 //Create an array with objects Philosophers
         Philosopher[] phil= new Philosopher[numphils];
         //Create an array with objects Forks
         Fork[] fork = new Fork[numphils];

         //Initialize forks
         for (int i =0; i<numphils; ++i) {
              fork[i] = new Fork(i);
         }
         
         //Create & execute threads
         for (int i=0; i<numphils; ++i) {
        	 //If is turn of the last philosopher to eat, change forks, like: Right = Left fork, Left = Right fork
        	 if (i==numphils-1) {
        		 phil[i] = new Philosopher(i, (i+1)%numphils, sleeptime, fork[(i+1)%numphils], fork[i]); 
        	 }
        	 //other philosophers will take forks like: Right = Right fork, Left = Left fork
        	 else {
        		 phil[i] = new Philosopher(i, (i+1)%numphils, sleeptime, fork[i], fork[(i+1)%numphils]);
        	 } 
             phil[i].start(); //Start thread
         } 
    } 
}
