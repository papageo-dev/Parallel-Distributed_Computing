package bankAccountStructReadWriteLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//Note: Only a thread can write at a time. But, multiple threads can read, if none thread write.

public class BankAccount { 
	
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private long balance;

	public BankAccount(long balance) {
		this.balance = balance;
	}

	public void deposit(long amount) {
      //Write Lock − If no thread is reading or writing, then one thread can access the write lock.
	  lock.writeLock().lock(); //Lock CS for write. Only a thread can write at a time. 
	  try {
		//Critical Section
	    balance += amount;
	  } finally {
	    lock.writeLock().unlock(); //Unlock for write
	  }
	}

	public void withdraw(long amount) {
	  //Write Lock − If no thread is reading or writing, then one thread can access the write lock.
	  lock.writeLock().lock(); //Lock CS for write. Only a thread can write at a time.
	  try {
		//Critical Section
	    balance -= amount;
	  } finally {
	    lock.writeLock().unlock(); //Unlock for write
	  }
	}

	public long getBalance() {
	  //Read Lock - If no thread has locked the ReadWriteLock for writing then multiple thread can access the read lock.
	  lock.readLock().lock(); //Lock for read. Multiple threads can read, if no write.
	  try {
		//Critical Section
	    return balance;
	  } finally {
	    lock.readLock().unlock(); //Unlock for read
	  }
	}
} 