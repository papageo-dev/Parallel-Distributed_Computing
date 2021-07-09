package bankAccountStructSyncVolatile;

/*   The Java volatile keyword is used to mark a Java variable as "being stored in main memory". 
 *   More precisely that means, that every read of a volatile variable will be read from the
 *   computer's main memory, and not from the CPU cache, and that every write to a volatile
 *   variable will be written to main memory, and not just to the CPU cache.
 *   In a multithreaded application where the threads operate on non-volatile variables, 
 *   each thread may copy variables from main memory into a CPU cache while working on them, 
 *   for performance reasons.
 *   Use synchronized in that case to guarantee that the reading and writing of "balance" variable is atomic.
 */

public class BankAccountSynchronizedVolatile {
	
  private volatile long balance;

  // Constructor
  public BankAccountSynchronizedVolatile(long balance) {
    this.balance = balance;
  }

  // Synchronized method to deposit amount to account
  public synchronized void deposit(long amount) {
    balance += amount;
  }

  // Synchronized method to withdraw  amount from account
  public synchronized void withdraw(long amount) {
    balance -= amount;
  }

  // Method to get current account balance
  public long getBalance() {
	  return balance;
  }
  
}
 