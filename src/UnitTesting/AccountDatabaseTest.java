package UnitTesting;


import banking.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import util.Date;

public class AccountDatabaseTest {
    AccountDatabase db;
    Savings accountReg;
    MoneyMarket accountMoneyMarket;

    @Before
    public void setUp() {
        db = new AccountDatabase();
        accountReg = new Savings(Branch.EDISON,
                               AccountType.SAVINGS,
                               new Profile("John", "Doe", new Date(1,1,2000)),
                        1000.0);
        accountMoneyMarket = new MoneyMarket(Branch.EDISON,
                                            AccountType.MONEY_MARKET,
                                            new Profile("John", "Doe", new Date(1,1,2000)),
                                     4900.0);
        db.add(accountReg);
        db.add(accountMoneyMarket);
    }

    @Test
    public void testDeposit_SavingsAccount() {
        db.deposit(accountReg.getAccountNumber(), 100);
        assertEquals(accountReg.getBalance(), 1100.0, 0.01);
    }

    @Test
    public void testDeposit_MoneyMarketAccount() {
        assertEquals(accountMoneyMarket.isLoyal(), false);
        db.deposit(accountMoneyMarket.getAccountNumber(), 200);
        assertEquals(accountMoneyMarket.getBalance(), 5100.0, 0.01); //balance is over 5000
        assertEquals(accountMoneyMarket.isLoyal(), true);                        //check loyalty status
    }

    @Test
    public void testWithdrawalValid() {
        db.withdraw(accountReg.getAccountNumber(), 50.0);
        assertEquals(accountReg.getBalance(), 950.0, 0.01);
    }

    @Test
    public void testWithdrawalInvalid() {
        db.withdraw(accountReg.getAccountNumber(), 2000.0);
        assertThrows();
    }

    @Test
    public void testWithdrawal_MoneyMarketAccount() {
        assertEquals(accountMoneyMarket.isLoyal(), true);
        db.withdraw(accountMoneyMarket.getAccountNumber(), 200);
        assertEquals(accountMoneyMarket.getBalance(), 4900.0, 0.01); //balance is over 5000
        assertEquals(accountMoneyMarket.isLoyal(), false);                        //check loyalty status
    }
}
