package validators;

import domain.*;
import repository.AccountsRepository;
import utils.MoneyUtils;

import java.time.LocalDate;
import java.util.List;

public class TransactionValidator {
    private static void validateFromAccount(AccountModel fromAccount){
        if (fromAccount instanceof SavingsAccountModel){
            throw new RuntimeException("From account is a SavingsAccount");
        }
    }

    private static void validateFromAccountTransferLimit(AccountModel fromAccount, MoneyModel value, LocalDate date){
        List<TransactionModel> transactions=AccountsRepository.INSTANCE.get(fromAccount.getId()).getTransactions();
        Double sumOfSpentMoney = transactions.stream().filter(transaction->transaction.getFrom().equals(fromAccount.getId()))
                .filter(transaction->transaction.getTimestamp().equals(date))
                .mapToDouble(transaction-> MoneyUtils.convert(transaction.getAmount(),fromAccount.getBalance().getCurrency()).getAmount())
                .sum();
        CheckingAccountModel fromAcc = (CheckingAccountModel) fromAccount;
        if (sumOfSpentMoney+MoneyUtils.convert(value,fromAcc.getBalance().getCurrency()).getAmount()>fromAcc.getAssociatedCard().getDailyTransactionLimit()){
            throw new RuntimeException("The daily transaction limit is exceeded with the transaction");
        }
    }

    private static void validateFromAccountBalance(AccountModel fromAccount, MoneyModel value){
        if (fromAccount.getBalance().getCurrency().equals(value.getCurrency())){
            if (fromAccount.getBalance().getAmount()-value.getAmount()<0){
                throw new RuntimeException("There are not enough funds in the from account");
            }
        }else{
            MoneyModel translatedValue= MoneyUtils.convert(fromAccount.getBalance(),value.getCurrency());
            if (translatedValue.getAmount()-value.getAmount()<0){
                throw new RuntimeException("There are not enough funds in the from account");
            }
        }
    }

    public static void validate(TransactionModel transaction){
        AccountModel fromAccount = AccountsRepository.INSTANCE.get(transaction.getFrom());
        TransactionValidator.validateFromAccount(fromAccount);
        TransactionValidator.validateFromAccountBalance(fromAccount,transaction.getAmount());
        validateFromAccountTransferLimit(fromAccount,transaction.getAmount(),transaction.getTimestamp());
    }

}
