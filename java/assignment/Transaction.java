package assignment;

enum TransactionType {DEPOSIT, WITHDRAW, TRANSFER}
public class Transaction {
    private TransactionType type;
    private double amount;
    private String accountNumber;
    private String receiverAccount;
    private String time;

    // Implement the rest of the class
    public Transaction(TransactionType type, double amount, String accountNumber, String time) {
        this.type = type;
        this.amount = amount;
        this.accountNumber = accountNumber;
        //this.receiverAccount = null;
        this.time = time;
    }

    public Transaction(TransactionType type, double amount, String accountNumber, String targetAccount, String time) {
        this.type = type;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.receiverAccount = targetAccount;
        this.time = time;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getTargetAccount() {
        return receiverAccount;
    }

    public String getTime() {
        return time;
    }
}

