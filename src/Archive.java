public class Archive {
    private AccountNode first; //the head node of the linked list


    public void add(Account account) {
        AccountNode newNode = new AccountNode(account);
        if(first == null) {
            first = newNode;
        }
        else {
            newNode.setNext(first);
            first = newNode;
        }
    } //add to front of linked list

    public void print() {
        AccountNode current = first;
        while(current != null) {
            System.out.println(current);
            current = current.getNext();
        }
    } //print the linked list
}
