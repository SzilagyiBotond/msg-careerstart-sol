package domain;

import java.time.LocalDate;

public class CardModel {
    private Long cardNumber;
    private String cardHolderName;
    private int cvv;
    private LocalDate expirationDate;
    private LocalDate issueDate;
    private boolean contactless;
    private boolean active;  // A boolean indicating whether the card is currently active or has been deactivated
    private double dailyWithdrawalLimit; // The maximum amount of money that can be withdrawn from the card in a day
    private double dailyTransactionLimit; // The maximum amount of money that can be spent using the card in a day.

    public CardModel(
            Long cardNumber,
            String cardHolderName,
            int cvv,
            LocalDate expirationDate,
            LocalDate issueDate,
            boolean contactless,
            boolean active,
            double dailyWithdrawalLimit,
            double dailyTransactionLimit
    ) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.issueDate = issueDate;
        this.contactless = contactless;
        this.active = active;
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
        this.dailyTransactionLimit = dailyTransactionLimit;

    }

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public boolean isContactless() {
        return contactless;
    }

    public void setContactless(boolean contactless) {
        this.contactless = contactless;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getDailyWithdrawalLimit() {
        return dailyWithdrawalLimit;
    }

    public void setDailyWithdrawalLimit(int dailyWithdrawalLimit) {
        this.dailyWithdrawalLimit = dailyWithdrawalLimit;
    }

    public double getDailyTransactionLimit() {
        return dailyTransactionLimit;
    }

    public void setDailyTransactionLimit(int dailyTransactionLimit) {
        this.dailyTransactionLimit = dailyTransactionLimit;
    }

    @Override
    public String toString() {
        return "CardModel{" +
                "cardNumber=" + cardNumber +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", cvv=" + cvv +
                ", expirationDate=" + expirationDate +
                ", issueDate=" + issueDate +
                ", contactless=" + contactless +
                ", active=" + active +
                ", dailyWithdrawalLimit=" + dailyWithdrawalLimit +
                ", dailyTransactionLimit=" + dailyTransactionLimit +
                '}';
    }
}
