package arrayObjLock;

public class ArrayLockObjSync {
 
	public static void main (String[] args) {
	 
		int n = 5; //list & locklist length
		
		int[] list = new int[n];
		Locklist[] locklist = new Locklist[n];
  
		//Create Locklist objects in array "locklist"
        for (int i=0; i<n; i++) {
        	locklist[i] = new Locklist(i);
        }
 
        //Create-Execute 2 types of threads(Add, Read)
        new AddThread(list, locklist).start();
        new ReadThread(list, locklist).start();
        	
	} 

}