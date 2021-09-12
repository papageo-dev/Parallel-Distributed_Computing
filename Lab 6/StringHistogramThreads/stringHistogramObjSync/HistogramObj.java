package stringHistogramObjSync;

public class HistogramObj {
	
	int histogram[];

	// Constructor
	public HistogramObj(int[] histogram) {
		this.histogram = histogram;
	}
	
	// Sychonized method to ncrease counter of text's position
	public synchronized void plusOne(int txt) {
		histogram[txt] ++;	
	}

}
