package bankAccountStructStampedLock;
import java.util.concurrent.locks.*;

/* StampedLock: 
 * A capability-based lock with three modes for controlling read/write access. 
 * The state of a StampedLock consists of a version and mode. 
 * Lock acquisition methods return a stamp that represents and controls access with respect to
 * a lock state; "try" versions of these methods may instead return the special value zero to represent 
 * failure to acquire access. Lock release and conversion methods require stamps as arguments, and fail if 
 * they do not match the state of the lock. */

public class BankAccountStampedLock {
	
  private final StampedLock sl = new StampedLock();
  private long balance;

  public BankAccountStampedLock(long balance) {
    this.balance = balance;
  }

  public void deposit(long amount) {
    long stamp = sl.writeLock();
    try {
      balance += amount;
    } finally {
      sl.unlockWrite(stamp);
    }
  }

  public void withdraw(long amount) {
    long stamp = sl.writeLock();
    try {
      balance -= amount;
    } finally {
      sl.unlockWrite(stamp);
    }
  }

  public long getBalance() {
    long stamp = sl.readLock();
    try {
      return balance;
    } finally {
      sl.unlockRead(stamp);
    }
  }

  public long getBalanceOptimisticRead() {
	  
    long stamp = sl.tryOptimisticRead();
    long balance = this.balance;
    
    if (!sl.validate(stamp)) {
      stamp = sl.readLock();
      try {
        balance = this.balance;
      } finally {
        sl.unlockRead(stamp);
      }
    }
    
    return balance;
  }
}
