package assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private String name;
    private HashMap<String, Account> Accounts;

    public Bank(String name) {
        this.name = name;
        this.Accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public void removeAccount(String accountNumber) throws AccountNotFoundException {
        if (!accounts.containsKey(accountNumber)) {
            throw new AccountNotFoundException("Account not found in the bank.");
        }
        accounts.remove(accountNumber);
    }

    public double getTotalAssets() {
        double totalAssets = 0;
        for (Account account : accounts.values()) {
            totalAssets += account.getBalance();
        }
        return totalAssets;
    }

    public List<Transaction> getTransactionHistory() {
        List<Transaction> transactions = new ArrayList<>();
        for (Account account : accounts.values()) {
            transactions.addAll(account.getTransactions());
        }
        return transactions;
    }
}
