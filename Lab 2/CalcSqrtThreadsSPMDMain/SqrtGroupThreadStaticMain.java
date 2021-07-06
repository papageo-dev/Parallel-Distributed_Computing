package pdc_lab_2_2021_1_2;


class SqrtGroupThreadStaticMain extends Thread
{
	private double [] table;
	private int myfrom;
    private int myto;

	// Constructor
	public SqrtGroupThreadStaticMain(double [] array, int from, int to)
	{
		table = array;
		myfrom = from;
        myto = to;
	}

	// Thread code
	public void run()
	{
		// Each thread calculates a piece of problem, from "myfrom" to "myto"
		for(int i=myfrom; i<myto; i++)
			// Calculate the square root of table[i]
			table[i] = Math.sqrt(table[i]);
	}
}