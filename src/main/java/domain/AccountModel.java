package domain;

import java.util.ArrayList;
import java.util.List;

public abstract class AccountModel {
    private String id;
    private AccountType accountType;
    private MoneyModel balance;
    private List<TransactionModel> transactions;

    public AccountModel(String id, AccountType accountType, MoneyModel balance, List<TransactionModel> transactions) {
        this.id = id;
        this.accountType = accountType;
        this.balance = balance;
        this.transactions = new ArrayList<>(transactions);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public MoneyModel getBalance() {
        return balance;
    }

    public void setBalance(MoneyModel balance) {
        this.balance = balance;
    }

    public List<TransactionModel> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionModel> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "id='" + id + '\'' +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", transactions=" + transactions.toString() +
                '}';
    }
}
