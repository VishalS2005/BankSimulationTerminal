package UnitTesting;

import banking.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import util.Date;

/**
 * The AccountDatabaseTest class is a test suite for validating the behavior of the AccountDatabase class.
 *
 * @author Vishal Saravanan, Yining Chen
 */
public class AccountDatabaseTest {
    AccountDatabase db;
    Savings accountReg;
    MoneyMarket accountMoneyMarket;

    /**
     * Creates AccountDatabase object along with a Savings and MoneyMarket object to populate it
     */
    @Before
    public void setUp() {
        db = new AccountDatabase();
        accountReg = new Savings(Branch.EDISON,
                AccountType.SAVINGS,
                new Profile("John", "Doe", new Date(1, 1, 2000)),
                1000.0);
        accountMoneyMarket = new MoneyMarket(Branch.EDISON,
                AccountType.MONEY_MARKET,
                new Profile("John", "Doe", new Date(1, 1, 2000)),
                4900.0);
        db.add(accountReg);
        db.add(accountMoneyMarket);
    }

    /**
     * Test case #1:
     * Tests the case where $100 is deposited into a Savings account.
     */
    @Test
    public void testDeposit_SavingsAccount() {
        db.deposit(accountReg.getAccountNumber(), 100);
        assertEquals(accountReg.getBalance(), 1100.0, 0.01);
    }

    /**
     * Test case #2:
     * Tests the case where $200 is deposited into a MoneyMarket account and the loyalty status is changed.
     */
    @Test
    public void testDeposit_MoneyMarketAccount() {
        assertEquals(accountMoneyMarket.isLoyal(), false);
        db.deposit(accountMoneyMarket.getAccountNumber(), 200);
        assertEquals(accountMoneyMarket.getBalance(), 5100.0, 0.01);
        assertEquals(accountMoneyMarket.isLoyal(), true);
    }

    /**
     * Test case #3:
     * Tests the case where $50 is withdrawn from an Account.
     */
    @Test
    public void testWithdrawalValid() {
        boolean isWithdrawn = db.withdraw(accountReg.getAccountNumber(), 50.0);
        assertTrue(isWithdrawn);
        assertEquals(accountReg.getBalance(), 950.0, 0.01);
    }

    /**
     * Test case #4:
     * Tests the case where $2000 is withdrawn from an Account but there is not enough money in the Account.
     */
    @Test
    public void testWithdrawalInvalid() {
        boolean isWithdrawn = db.withdraw(accountReg.getAccountNumber(), 2000.0);
        assertFalse(isWithdrawn);
        assertEquals(accountReg.getBalance(), 1000.0, 0.01);
    }

    /**
     * Test case #5:
     * Tests the case where $200 is withdrawn from an Account and the customer loses loyalty status.
     */
    @Test
    public void testWithdrawal_MoneyMarketAccount() {
        assertEquals(accountMoneyMarket.isLoyal(), true);
        db.withdraw(accountMoneyMarket.getAccountNumber(), 200);
        assertEquals(accountMoneyMarket.getBalance(), 4900.0, 0.01);
        assertEquals(accountMoneyMarket.isLoyal(), false);
    }
}
