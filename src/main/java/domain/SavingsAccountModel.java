package domain;

import java.time.LocalDate;
import java.util.List;

public class SavingsAccountModel extends AccountModel {
    private double interest; // always adds to balance based on frequency
    private CapitalizationFrequency interestFrequency; // how often interest is added to the account balance
    private LocalDate lastInterestAppliedDate; // the last date the interest was applied to the account

    public SavingsAccountModel(
            String id,
            MoneyModel balance,
            List<TransactionModel> transactions,
            double interest,
            CapitalizationFrequency interestFrequency,
            LocalDate lastInterestAppliedDate
    ) {
        super(id, AccountType.SAVINGS, balance, transactions);
        this.interest = interest;
        this.interestFrequency = interestFrequency;
        this.lastInterestAppliedDate = lastInterestAppliedDate;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public CapitalizationFrequency getInterestFrequency() {
        return interestFrequency;
    }

    public void setInterestFrequency(CapitalizationFrequency interestFrequency) {
        this.interestFrequency = interestFrequency;
    }

    public LocalDate getLastInterestAppliedDate() {
        return lastInterestAppliedDate;
    }

    public void setLastInterestAppliedDate(LocalDate lastInterestAppliedDate) {
        this.lastInterestAppliedDate = lastInterestAppliedDate;
    }

    @Override
    public String toString() {
        return "SavingsAccountModel{" +
                "id='" + getId() + '\'' +
                ", accountType=" + getAccountType() +
                ", balance=" + getBalance() +
                ", transactions=" + getTransactions().toString() +
                ", interest=" + interest +
                ", interestFrequency=" + interestFrequency +
                ", lastInterestAppliedDate=" + lastInterestAppliedDate +
                '}';
    }
}

