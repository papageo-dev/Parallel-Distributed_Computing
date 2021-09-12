package arrayListConcurrentSync;

import java.util.ArrayList;

public class ArrayListSync {
 
	public static void main (String[] args) {
	 
		ArrayList<Integer> list = new ArrayList<>();

		//Add items to ArrayList "list"
		list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
 
        new AddThread(list).start(); //Add items to list
        new RemoveThread(list).start(); //Remove list items
        new ReadThread(list).start(); //Read list Items
        new UpdateThread(list).start(); //Update list items
        	
	} 

}
	 
	 


