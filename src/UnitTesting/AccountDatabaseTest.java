package UnitTesting;

import banking.Account;
import banking.AccountDatabase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDatabaseTest {
    Account account;
    AccountDatabase db;

    @Before
    public void setUp() {
        db = new AccountDatabase();
        db.add(account);
    }

    @Test
    public void testAccountDatabase() {
        AccountDatabase accountDatabase = new AccountDatabase();
        assertNotNull(accountDatabase);
    }
}