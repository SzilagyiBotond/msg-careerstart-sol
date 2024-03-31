import domain.CurrencyType;
import domain.MoneyModel;
import org.junit.Assert;
import org.junit.Test;
import seed.SeedInitializer;
import services.SavingsManagerService;
import services.TransactionManagerService;

import static org.junit.Assert.*;
import static seed.AccountsSeedData.*;

public class BankingApplicationTest {

    @Test
    public void testTransaction(){
        SeedInitializer.seedData();
        TransactionManagerService transactionManagerServiceInstance = new TransactionManagerService();
        Assert.assertEquals(transactionManagerServiceInstance.checkFunds(checkingAccountA.getId()).getAmount(), new MoneyModel(100, CurrencyType.RON).getAmount(),0.0);
        Assert.assertEquals(transactionManagerServiceInstance.checkFunds(checkingAccountB.getId()).getAmount(),new MoneyModel(300,CurrencyType.RON).getAmount(),0.0);

        //checking RON-RON transaction
        transactionManagerServiceInstance.transfer(checkingAccountA.getId(),checkingAccountB.getId(),new MoneyModel(50,CurrencyType.RON));
        Assert.assertEquals(transactionManagerServiceInstance.checkFunds(checkingAccountA.getId()).getAmount(), new MoneyModel(50, CurrencyType.RON).getAmount(),0.0);
        Assert.assertEquals(transactionManagerServiceInstance.checkFunds(checkingAccountB.getId()).getAmount(),new MoneyModel(350,CurrencyType.RON).getAmount(),0.0);

        //checking RON-EUR transaction
        transactionManagerServiceInstance.transfer(checkingAccountA.getId(),checkingAccountC.getId(),new MoneyModel(50,CurrencyType.RON));
        Assert.assertEquals(transactionManagerServiceInstance.checkFunds(checkingAccountA.getId()).getAmount(),new MoneyModel(0,CurrencyType.RON).getAmount(),0.0);
        Assert.assertEquals(transactionManagerServiceInstance.checkFunds(checkingAccountC.getId()).getAmount(),new MoneyModel(20,CurrencyType.EUR).getAmount(),0.0);

        //checking EUR-RON transaction
        transactionManagerServiceInstance.transfer(checkingAccountC.getId(),checkingAccountB.getId(),new MoneyModel(10,CurrencyType.EUR));
        Assert.assertEquals(transactionManagerServiceInstance.checkFunds(checkingAccountC.getId()).getAmount(),new MoneyModel(10,CurrencyType.EUR).getAmount(),0.0);
        Assert.assertEquals(transactionManagerServiceInstance.checkFunds(checkingAccountB.getId()).getAmount(),new MoneyModel(399.8,CurrencyType.RON).getAmount(),0.0);

        //checking transaction fail with insufficient funds
        try{
            transactionManagerServiceInstance.transfer(checkingAccountA.getId(),checkingAccountB.getId(),new MoneyModel(1000,CurrencyType.RON));
            fail("Didnt catch");
        }catch (RuntimeException e){
            assertEquals("There are not enough funds in the from account",e.getMessage());
        }

        //checking transaction fail with savings account aas sender
        try{
            transactionManagerServiceInstance.transfer(savingsAccountA.getId(),checkingAccountB.getId(),new MoneyModel(10,CurrencyType.EUR));
            fail("Didnt catch");
        }catch (RuntimeException e){
            assertEquals("From account is a SavingsAccount",e.getMessage());
        }
    }

    @Test
    public void testWithdraw(){
        SeedInitializer.seedData();
        TransactionManagerService transactionManagerServiceInstance = new TransactionManagerService();

        //checking withdraw
        transactionManagerServiceInstance.withdraw(checkingAccountA.getId(),new MoneyModel(50,CurrencyType.RON));
        Assert.assertEquals(transactionManagerServiceInstance.checkFunds(checkingAccountA.getId()).getAmount(), new MoneyModel(50, CurrencyType.RON).getAmount(),0.0);

        //checking insuff funds
        try{
            transactionManagerServiceInstance.withdraw(checkingAccountA.getId(),new MoneyModel(100,CurrencyType.RON));
            fail("Didnt catch");
        }catch (Exception ex){
            Assert.assertEquals("Not enough funds to perform withdraw",ex.getMessage());
        }
    }

    @Test
    public void testSavings(){
        SeedInitializer.seedData();
        TransactionManagerService transactionManagerServiceInstance = new TransactionManagerService();
        SavingsManagerService savingsManagerServiceInstance = new SavingsManagerService();
        savingsManagerServiceInstance.passTime();

        //checking monthly
        Assert.assertEquals(transactionManagerServiceInstance.checkFunds(savingsAccountA.getId()).getAmount(),new MoneyModel(1055,CurrencyType.RON).getAmount(),0.0);
        Assert.assertEquals(transactionManagerServiceInstance.checkFunds(savingsAccountB.getId()).getAmount(),new MoneyModel(2000,CurrencyType.EUR).getAmount(),0.0);

        //checking quarterly
        savingsManagerServiceInstance.passTime();
        Assert.assertEquals(transactionManagerServiceInstance.checkFunds(savingsAccountB.getId()).getAmount(),new MoneyModel(2000,CurrencyType.EUR).getAmount(),0.0);
        savingsManagerServiceInstance.passTime();
        Assert.assertEquals(transactionManagerServiceInstance.checkFunds(savingsAccountB.getId()).getAmount(),new MoneyModel(2113,CurrencyType.EUR).getAmount(),0.0);

    }

    @Test
    public void expectTrueTest() {
        assertTrue(true);
    }
}
