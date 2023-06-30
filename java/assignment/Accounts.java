package assignment;

import java.util.HashMap;

class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String message){
        super(message);
    }
}
class AccountNotFoundException extends Exception{
    public AccountNotFoundException(String message){
        super(message);
    }
}
class AccountInactiveException extends Exception{
    public AccountInactiveException(String message){
        super(message);
    }
    
}
class AccountClosedException extends Exception{
    public AccountClosedException(String message){
        super(message);
    }
}
class CheckNotFoundException extends Exception{
    public CheckNotFoundException(String message){
        super(message);
    }
}



enum AccountStatus {ACTIVE, INACTIVE, CLOSED}

public class Accounts {
    private String accountNumber;
    protected double balance;
    protected String accountHolder;
    protected static AccountStatus accountStatus; 
    private HashMap<String, Integer> transactions;

    public Accounts(String accountNumber, double balance, String accountHolder, AccountStatus accountStatus){
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountHolder = accountHolder;
        Accounts.accountStatus = AccountStatus.ACTIVE;
        this.transactions = new HashMap<>();
    }

    public void deposit(double amount) throws AccountInactiveException, AccountClosedException {
        if ( accountStatus != AccountStatus.ACTIVE){
            throw new AccountInactiveException("Account is Inactive");
        }
        if ( accountStatus == AccountStatus.CLOSED){
            throw new AccountInactiveException("Account is Closed");
        }
        balance=balance+amount;
        new Transaction(TransactionType.DEPOSIT, amount, accountNumber, accountNumber, accountHolder);
    }

    public void withdraw(double amount) throws InsufficientFundsException,AccountInactiveException, AccountClosedException {
        if ( balance < amount){
            throw new InsufficientFundsException("Insufficient Funds mate");
        }
        if ( accountStatus != AccountStatus.ACTIVE){
            throw new AccountInactiveException("Account is Inactive");
        }
        if ( accountStatus == AccountStatus.CLOSED){
            throw new AccountInactiveException("Account is Closed");
        }
        balance=balance-amount;
        new Transaction(TransactionType.WITHDRAW, amount, accountNumber, accountHolder);
    }

    public void transfer(double amount) throws InsufficientFundsException,AccountInactiveException, AccountClosedException {
        if ( balance < amount){
            throw new InsufficientFundsException("Insufficient Funds mate");
        }
        if ( accountStatus != AccountStatus.ACTIVE){
            throw new AccountInactiveException("Account is Inactive");
        }
        if ( accountStatus == AccountStatus.CLOSED){
            throw new AccountInactiveException("Account is Closed");
        }
        balance=balance-amount;
        new Transaction(TransactionType.WITHDRAW, amount, accountNumber, accountHolder);
    }


    public double getBalance(){
        return balance;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public String getAccountHolder(){
        return accountHolder;
    } 

    public AccountStatus getAccountStatus(){
        return accountStatus;
    }

    public HashMap<String, Integer> getTransaction() {
        return transactions;
    }
}
