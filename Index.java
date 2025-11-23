public class Index {
    /*
     * Write a program to reverse a singly linked list.
     * Expected Input/Output:
     * Input: 1 -> 2 -> 3 -> 4 -> 5 -> null
     * Output: 5 -> 4 -> 3 -> 2 -> 1 -> null
     */
    public class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    Node head;

    public Node reverse() {
        Node prev = null, current = head, next = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }

        head = prev;
        return head;
    }

    public void removeLoop(Node nodePosition) {
        Node ptr1 = head;
        Node ptr2 = nodePosition;

        if(ptr1 == ptr2) {
            while (ptr2.next != ptr1) {
                ptr2 = ptr2.next;
            }

            ptr2.next = null;
            return;
        }
        
        while (ptr1.next != ptr2.next) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        
        ptr2.next = null;
        return;
    }

    public void loopCheck() {
        // Flyod's rule
        Node slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                removeLoop(slow);
                return;
            }
        }
    }

    // Node head = null;

    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Index list = new Index();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(3);

        System.out.print("Original List: ");
        list.print();
        System.out.print("Reverse List: ");
        list.reverse();
        list.print();
        System.out.println();
        System.out.print("Loop Check: ");
        list.loopCheck();
        list.print();

    }
}