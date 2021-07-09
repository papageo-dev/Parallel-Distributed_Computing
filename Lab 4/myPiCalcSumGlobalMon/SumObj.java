package myPiCalcSumGlobalMon;

public class SumObj {
	
	double sum;
    
    public SumObj (){
    	this.sum = 0;
    }

    // Synchronized method to add local thread's result to total result
    public synchronized void add (double localsum) {
    	this.sum = this.sum + localsum;
    }

	// Method to print total result
    public void printout() {
    	System.out.println(this.sum);
    }

	// Method to get total result
	public double getSum() {
		return sum;
	}

}
