package assignment;

class SavingsAccount extends Accounts {
    private double interestRate;

    public SavingsAccount(String accountNumber, double balance, String accountHolder) {
        super(accountNumber, balance, accountHolder, accountStatus);
        this.interestRate = 0.05;
    }

    public void applyInterest() throws AccountInactiveException, AccountClosedException {
        Accounts thisAccount = new Accounts(null, interestRate, null, null);
        if (thisAccount.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new AccountInactiveException("Cannot apply interest to an inactive account.");
        }
        if (thisAccount.getAccountStatus() == AccountStatus.CLOSED) {
            throw new AccountClosedException("Cannot apply interest to a closed account.");
        }
        double interest = thisAccount.getBalance() * interestRate;
        thisAccount.balance += interest;
        Transaction transaction = new Transaction(TransactionType.DEPOSIT, interest, thisAccount.getAccountNumber(), null);
        thisAccount.getTransaction().add(transaction);
    }

}

