package domain;

import java.time.LocalDate;
import java.util.UUID;

public class TransactionModel {
    private UUID id;
    private String from;
    private String to; // another account if transfer or the same account (as from) if money is withdrawn
    private MoneyModel amount;
    private LocalDate timestamp;

    public TransactionModel(UUID id, String from, String to, MoneyModel amount, LocalDate timestamp) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.timestamp = timestamp;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public MoneyModel getAmount() {
        return amount;
    }

    public void setAmount(MoneyModel amount) {
        this.amount = amount;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "id='" + id + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}

