package validators;

import domain.AccountModel;
import domain.CheckingAccountModel;
import domain.MoneyModel;
import domain.TransactionModel;
import repository.AccountsRepository;
import utils.MoneyUtils;

import java.time.LocalDate;
import java.util.List;

public class WithdrawValidator {
    public static void validate(TransactionModel withdraw){
        AccountModel account=AccountsRepository.INSTANCE.get(withdraw.getFrom());
        validateBalance(account,withdraw.getAmount());
        // or account.getAccountType.equals(AccountType.CHECKING) also works as an alternative
        if (account instanceof CheckingAccountModel){
            validateCheckingAccount((CheckingAccountModel) account,withdraw.getAmount(),withdraw.getTimestamp());
        }
    }
    private static void validateBalance(AccountModel account, MoneyModel value){
        if (account.getBalance().getAmount()< MoneyUtils.convert(value,account.getBalance().getCurrency()).getAmount()){
            throw new RuntimeException("Not enough funds to perform withdraw");
        }
    }
    private static void validateCheckingAccount(CheckingAccountModel account, MoneyModel value, LocalDate date){
        List<TransactionModel> transactions= AccountsRepository.INSTANCE.get(account.getId()).getTransactions();
        Double sumOfSpentMoney = transactions.stream().filter(transaction->transaction.getFrom().equals(account.getId()))
                .filter(transaction->transaction.getTimestamp().equals(date))
                .mapToDouble(transaction-> MoneyUtils.convert(transaction.getAmount(),account.getBalance().getCurrency()).getAmount())
                .sum();
        if (sumOfSpentMoney+MoneyUtils.convert(value,account.getBalance().getCurrency()).getAmount()>account.getAssociatedCard().getDailyWithdrawalLimit()){
            throw new RuntimeException("The daily withdraw limit reached");
        }
    }
}
