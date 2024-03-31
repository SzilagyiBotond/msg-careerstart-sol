package domain;

import java.util.List;

public class CheckingAccountModel extends AccountModel {
    private CardModel associatedCard;

    public CheckingAccountModel(String id, MoneyModel balance, List<TransactionModel> transactions, CardModel associatedCard) {
        super(id, AccountType.CHECKING, balance, transactions);
        this.associatedCard = associatedCard;
    }

    public CardModel getAssociatedCard() {
        return associatedCard;
    }

    public void setAssociatedCard(CardModel associatedCard) {
        this.associatedCard = associatedCard;
    }

    @Override
    public String toString() {
        return "CheckingAccountModel{" +
                "id='" + getId() + '\'' +
                ", accountType=" + getAccountType() +
                ", balance=" + getBalance() +
                ", transactions=" + getTransactions().toString() +
                ", associatedCard=" + associatedCard +
                '}';
    }
}

