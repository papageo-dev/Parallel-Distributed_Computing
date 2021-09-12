package bankAccountStructStampedLock;

class Reader extends Thread {
	
	BankAccountStampedLock[] acc;
	int i;

	public Reader(BankAccountStampedLock[] acc, int i) {
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