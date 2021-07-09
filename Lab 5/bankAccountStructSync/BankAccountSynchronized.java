package bankAccountStructSync;

public class BankAccountSynchronized {
	
  private long balance;

  public BankAccountSynchronized(long balance) {
    this.balance = balance;
  }

  //Synchronized method for deposit. One thread can access and change "balance" at a time.
  public synchronized void deposit(long amount) {
    balance += amount;
  }

  //Synchronized method for withdraw. One thread can access and change "balance" at a time.
  public synchronized void withdraw(long amount) {
    balance -= amount;
  }

  //Synchronized method to return current balance.
  public synchronized long getBalance() {
    return balance;
  }
}