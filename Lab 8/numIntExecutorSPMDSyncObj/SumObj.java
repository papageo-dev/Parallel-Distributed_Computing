package numIntExecutorSPMDSyncObj;

public class SumObj {
	
	double sum;
    
    public SumObj (){
    	this.sum = 0;
    }

    //Aukshsh tou moirazomenou methrhth
    public synchronized void add (double localsum) {
    	//Me thn xrhsh tou *synchronized* epitugxanetai anoivaios apokleismos, xwris thn xrhsh kleidwmatos(lock)
    	this.sum = this.sum + localsum;
    }

    public void printout() {
    	System.out.println(this.sum);
    }

	public double getSum() {
		return sum;
	}

}
