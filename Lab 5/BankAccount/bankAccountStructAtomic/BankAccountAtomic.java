package bankAccountStructAtomic;

import java.util.concurrent.atomic.*;

public class BankAccountAtomic {
	
    private final AtomicLong balance;

    public BankAccountAtomic(long balance) {
      this.balance = new AtomicLong(balance);
    }

    public void deposit(long amount) {
      //Add amount to balance and return updated balance
      balance.addAndGet(amount);
    }

    public void withdraw(long amount) {
      //Decrease balance by amount and return updated balance
      balance.addAndGet(-amount);
    }

    public long getBalance() {
      //Retun current balance	
      return balance.get();
    }
}
