public class AccountNode {
    private AccountNode next;
    private Account Account;

    public AccountNode() {
        next = null;
        Account = null;
    }

    public AccountNode(Account account) {
        this.Account = account;
    }

    public AccountNode(Account account, AccountNode next) {
        this.Account = account;
        this.next = next;
    }

    public Account getAccount() {
        return Account;
    }

    public void setAccount(Account account) {
        this.Account = account;
    }

    public AccountNode getNext() {
        return next;
    }

    public void setNext(AccountNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return Account.toString();
    }

    public static void main(String[] args) {

    }
}
