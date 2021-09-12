package diningPhilosophersLock;

class Philosopher extends Thread {
	
    private int identity; //Thread/Philosopher number
    private Fork left; //Left Fork number
    private Fork right; //Right Fork number
    private int scale; //Sleeping Time
    private int next; //Next Fork

    //Constructor
    Philosopher(int id, int n, int s, Fork l, Fork r) {
    	  identity = id;
    	  next = n;
    	  scale = s;
    	  left = l;
    	  right = r; 
    }

    //Thread code
    public void run() {
        while (true) { //Infinity Loop
        	//thinking
    	    System.out.println(" Philosopher "+ identity + " is thinking");
            delay(scale);
            //hungry
            System.out.println(" Philosopher "+ identity + " is trying to get fork " + identity);
            right.get();
            //got right fork
            System.out.println(" Philosopher "+ identity + " got fork " + identity + " and is trying to get fork " + next);
            left.get();
            System.out.println(" Philosopher "+ identity + " is eating");
            //eating
            System.out.println(" Philosopher "+ identity + " is releasing left fork " + next);
            delay(scale);
            left.put();
            System.out.println(" Philosopher "+ identity + " is releasing fork " + identity);
            delay(scale);
            right.put();
        }    
    }

    //Calculator for sleeping time
    public void delay(int scale) {
    	try {
    		sleep((int)(Math.random()*scale));
        	} catch (InterruptedException e) { }
    }
}
