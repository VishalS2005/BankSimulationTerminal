package banking;

/**
 * RunProject1 is a class that calls the TransactionManager which will manage transactions.
 * Inputs will be read and corresponding outputs will be printed after the call to run TransactionManager.
 *
 * @author Vishal Saravanan, Yining Chen
 */
public class RunProject1 {

    /**
     * Executes the Transaction Manager.
     *
     * @param args String representation of command line inputs
     */
    public static void main(String [] args) {
        new TransactionManager().run();
    }
}
