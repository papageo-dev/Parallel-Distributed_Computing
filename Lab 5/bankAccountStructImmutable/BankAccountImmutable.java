package bankAccountStructImmutable;

public class BankAccountImmutable {
	
    private static long balance;

    //Constructor
    public BankAccountImmutable(long balance) {
      BankAccountImmutable.balance = balance;
    }

    //Synchronized methods for thread safe class - No negative balance. 
    //(http://tutorials.jenkov.com/java-concurrency/thread-safety-and-immutability.html)
    
    //Add amount to balance and return updated balance
    public synchronized BankAccountImmutable deposit(long amount) {
      return new BankAccountImmutable(balance + amount);
    }

    //Remove amount from balance and return updated balance
    public synchronized BankAccountImmutable withdraw(long amount) {
      return new BankAccountImmutable(balance - amount);
    }

    //Return current balance
    public synchronized long getBalance() {
      return balance;
    }
}
