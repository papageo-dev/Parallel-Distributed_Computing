package stringMatchObjSync;

public class MyObj {
	
	int sharedCounter;
    
    public MyObj (){
    	this.sharedCounter = 0;
    }

    // Synchronized method to increase shared counter
    public synchronized void plusOne () {
    	this.sharedCounter++;
    }

    //Return current value of shared counter
	public int getCounter() {
		return sharedCounter;
	}

}
