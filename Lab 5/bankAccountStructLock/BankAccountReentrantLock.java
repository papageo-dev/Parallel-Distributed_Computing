package bankAccountStructLock;
import java.util.concurrent.locks.*;

//Note: This method needs more time than ReadWriteLock. Only a thread can write or read at a time.

public class BankAccountReentrantLock {
	
    private final Lock lock = new ReentrantLock();
    private long balance;

    public BankAccountReentrantLock(long balance) {
      this.balance = balance;
    }

    public void deposit(long amount) {
      lock.lock(); //Lock CS to write. Only a thread can access at a time.
      try {
    	//Critical Section
        balance += amount;
      } finally {
        lock.unlock(); //Unlock CS.
      }
    }

    public void withdraw(long amount) {
      lock.lock(); //Lock CS to write. Only a thread can access at a time.
      try {
    	//Critical Section
        balance -= amount;
      } finally {
        lock.unlock(); //Unlock CS.
      }
    }

  public long getBalance() {
      lock.lock(); //Lock CS to read current balance. Only a thread can access at a time.
      try {
    	//Critical Section
        return balance;
      } finally {
        lock.unlock(); //Unlock CS.
      }
    }
}
