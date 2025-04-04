package customDSImplementations;

public class CustomLinkList{
    private Node head;
    private Node tale;
    private int size = 0;
    static class Node{
        int val;
        Node next;
        public Node(int val){
            this.val = val;
            this.next = null;
        }
    }
    //method for adding element at biginning
    public void addElementAtBeginning(int val){
        Node newnode = new Node(val);
        if(size == 0){
            head = newnode;
            tale = newnode;
        }else{
            newnode.next = head;
            head = newnode;
        }
        size++;
    }
    //method for adding element at end
    public void addElementAtEnd(int val){
        Node newnode = new Node(val);
        if(size == 0){
            head = newnode;
        }else{
            tale.next = newnode;
        }
        size++;
    }
    public void addElementAtPosition(int pos, int val){
        Node newnode = new Node(val);
        if(pos > size) throw new RuntimeException("The limit is not in the range");
        if(size == 0){
            head = newnode;
        }else{
            if (pos == 0) addElementAtBeginning(val);
            if (pos == size) addElementAtBeginning(val);
            Node temp = head;
            for (int i = 0; i < pos - 1; i++) {
                temp = temp.next;
            }
            Node temp1 = temp.next;
            temp.next = newnode;
            newnode.next = temp1;
        }
        size++;
    }
    //method to reverse a linked list
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    //method to print the list
    public void display(){
        Node temp = head;
        for (int i = 0; i < size; i++) {
            System.out.print(temp.val+ "->");
            temp = temp.next;
        }
        System.out.println();
    }
}
