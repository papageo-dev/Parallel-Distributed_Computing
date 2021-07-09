package arrayObjLockSync;

public class AddThread extends Thread {
	
	private int[] list;
    private Locklist[] locklist;
 
    public AddThread(int[] list, Locklist[] locklist) {
        this.list = list;
        this.locklist = locklist;
    }
 
    public void run() {
        
        int counter = 0;
        while (true) {
            delay(5000);
            synchronized(locklist[counter]) {
            	list[counter]++;
            	System.out.println("Add done");
            	counter = (counter+1)%5;
            }    	
        }
    }
    
	public static void delay (int d) {
	  	try {
			Thread.sleep((int)(Math.random()*d));
		} catch (InterruptedException e) { }
	}

}
