package bankAccountStructSync;

import java.util.Random;

public class Writer extends Thread {
	
	BankAccountSynchronized[] acc;
	int i;
	long amount;
	// create instance of Random class
    Random rand = new Random();

	public Writer(BankAccountSynchronized[] acc, int i) {
		this.acc = acc;
		this.i = i;	
		this.amount = rand.nextInt(1000); // Generate random integers in range 0 to 999
	}
	
	//Thread code
	public void run() {
		
		//Pick a random transaction to run
		if (i%2 == 0) {
			System.out.println(i + ": Deposit in progress...");
			delay(50000); //Delay time to make the program realistic
			acc[i].deposit(amount); //Do transaction - Write
			System.out.println(i + ": Deposit amount is: " + amount + "$");
			System.out.println(i + ": New balance is: " + acc[i].getBalance() + "$");
		}
		else {
			if (amount<=acc[i].getBalance() && acc[i].getBalance()>=0) { //Check balance & amount
				System.out.println(i + ": Withdraw in progress...");
				delay(50000); //Delay time to make the program realistic
				acc[i].withdraw(amount); //Do transaction - Write
				System.out.println(i + ": Withdraw amount is: " + amount + "$");
				System.out.println(i + ": New balance is: " + acc[i].getBalance() + "$");
			}
			else { //If no enough balance, print message
				System.out.println(i + ": Withdraw in progress...");
				delay(1000); //Delay time to make the program realistic
				System.out.println(i + ": Withdraw amount is: " + amount  + "$"
						+ ", but your balance is: " + acc[i].getBalance() + "$" + ". You can't do this Withdraw!");
			}
		}
	}
	
	public static void delay (int d) {

	  	try {
			Thread.sleep((int)(Math.random()*d));
		} catch (InterruptedException e) { }
	}

}
