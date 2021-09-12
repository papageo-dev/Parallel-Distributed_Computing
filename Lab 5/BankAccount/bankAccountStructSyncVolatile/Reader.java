package bankAccountStructSyncVolatile;

class Reader extends Thread {
	
	BankAccountSynchronizedVolatile[] acc;
	int i;

	public Reader(BankAccountSynchronizedVolatile[] acc, int i) {
		this.acc = acc;
		this.i = i;
	}

	//Thread code
	public void run() {
		
		//Print account's balance
		System.out.println("Balance of account " + i + " is: " 
							+ acc[i].getBalance());
	}

}