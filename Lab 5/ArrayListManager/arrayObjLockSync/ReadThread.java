package arrayObjLockSync;

public class ReadThread extends Thread {
	
	private int[] list;
    private Locklist[] locklist;
 
    public ReadThread(int[] list, Locklist[] locklist) {
        this.list = list;
        this.locklist = locklist;
    }
 
    public void run() {
 
        while (true) {
            delay(100);
            System.out.println("List :");
            for (int i=0; i<5; i++) {
            	synchronized(locklist[i]) { 
            		System.out.print(list[i]+" ");
            		delay(100);
            	}
            }	
            System.out.println();	
        }
    }
    
    public static void delay (int d) {
	  	try {
			Thread.sleep((int)(Math.random()*d));
		} catch (InterruptedException e) { }
	}

}
