package assignment;

import java.util.HashMap;
import java.util.Map;

class CheckingAccount extends Accounts {
    private Map<Integer, Double> uncashedChecks;

    public CheckingAccount(String accountNumber, double balance, String accountHolder) {
        super(accountNumber, balance, accountHolder, accountStatus);
        this.uncashedChecks = new HashMap<>();
    }

    public void writeCheck(int checkNumber, double amount) throws AccountInactiveException, AccountClosedException, InsufficientFundsException {
        if (getAccountStatus() != AccountStatus.ACTIVE) {
            throw new AccountInactiveException("Cannot write a check from an inactive account.");
        }
        if (getAccountStatus() == AccountStatus.CLOSED) {
            throw new AccountClosedException("Cannot write a check from a closed account.");
        }
        if (getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds in the account.");
        }
        balance -= amount;
        uncashedChecks.put(checkNumber, amount);
        Transaction transaction = new Transaction(TransactionType.WITHDRAW, amount, getAccountNumber(), accountHolder);
        getTransaction().add(transaction);
    }

    public void cashCheck(int checkNumber, Account recipientAccount) throws AccountInactiveException, AccountClosedException, CheckNotFoundException, InsufficientFundsException {
        if (getAccountStatus() != AccountStatus.ACTIVE) {
            throw new AccountInactiveException("Cannot cash a check from an inactive account.");
        }
        if (getAccountStatus() == AccountStatus.CLOSED) {
            throw new AccountClosedException("Cannot cash a check from a closed account.");
        }
        if (!uncashedChecks.containsKey(checkNumber)) {
            throw new CheckNotFoundException("Check number not found.");
        }
        double amount = uncashedChecks.remove(checkNumber);
        if (getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds to cash the check.");
        }
        balance -= amount;
        Transaction transaction = new Transaction(TransactionType.TRANSFER, amount, getAccountNumber(), recipientAccount.getAccountNumber());
        getTransaction().add(transaction);
        recipientAccount.deposit(amount);
    }
}
