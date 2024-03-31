package services;

import domain.AccountModel;
import domain.MoneyModel;
import domain.TransactionModel;
import repository.AccountsRepository;
import utils.MoneyUtils;
import validators.TransactionValidator;
import validators.WithdrawValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransactionManagerService {

    public TransactionModel transfer(String fromAccountId, String toAccountId, MoneyModel value) {
        AccountModel fromAccount = AccountsRepository.INSTANCE.get(fromAccountId);
        AccountModel toAccount = AccountsRepository.INSTANCE.get(toAccountId);

        if (fromAccount == null || toAccount == null) {
            throw new RuntimeException("Specified account does not exist");
        }

        TransactionModel transaction = new TransactionModel(
                UUID.randomUUID(),
                fromAccountId,
                toAccountId,
                value,
                LocalDate.now()
        );
        TransactionValidator.validate(transaction);

        fromAccount.getBalance().setAmount(fromAccount.getBalance().getAmount()- MoneyUtils.convert(value,fromAccount.getBalance().getCurrency()).getAmount());
//        fromAccount.getBalance().setAmount(fromAccount.getBalance().getAmount() - value.getAmount());
        fromAccount.getTransactions().add(transaction);
        toAccount.getBalance().setAmount(toAccount.getBalance().getAmount()+MoneyUtils.convert(value,toAccount.getBalance().getCurrency()).getAmount());
//        toAccount.getBalance().setAmount(toAccount.getBalance().getAmount() + value.getAmount());
        toAccount.getTransactions().add(transaction);

        return transaction;
    }

    public TransactionModel withdraw(String accountId, MoneyModel amount) {
        AccountModel account = AccountsRepository.INSTANCE.get(accountId);
        if (account==null){
            throw new RuntimeException("Specified account does not exist");
        }
        TransactionModel withdraw = new TransactionModel(
                UUID.randomUUID(),
                accountId,
                null,
                amount,
                LocalDate.now()
        );
        WithdrawValidator.validate(withdraw);
        account.getBalance().setAmount(account.getBalance().getAmount()-MoneyUtils.convert(amount,account.getBalance().getCurrency()).getAmount());
        account.getTransactions().add(withdraw);
        return withdraw;
    }

    public MoneyModel checkFunds(String accountId) {
        if (!AccountsRepository.INSTANCE.exist(accountId)) {
            throw new RuntimeException("Specified account does not exist");
        }
        return AccountsRepository.INSTANCE.get(accountId).getBalance();
    }

    public List<TransactionModel> retrieveTransactions(String accountId) {
        if (!AccountsRepository.INSTANCE.exist(accountId)) {
            throw new RuntimeException("Specified account does not exist");
        }
        return new ArrayList<>(AccountsRepository.INSTANCE.get(accountId).getTransactions());
    }
}

