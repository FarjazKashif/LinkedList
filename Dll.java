// public class Dll {
//     /*
//      * Write a program to detect if a loop exists in a singly linked list and remove it
//      * without losing nodes.
//      * Expected Input/Output:
//      * Input: 1 -> 2 -> 3 -> 4 -> 5 -> 3 (loop back to node 3)
//      * Output: 1 -> 2 -> 3 -> 4 -> 5 -> null
//      */

//     public class Node {
//         int data;
//         Node next;

//         Node(int data) {
//             this.data = data;
//         }
//     }

//     Node head;

//     // Method to detect and remove loop using Floyd's Cycle Detection Algorithm
//     public void detectAndRemoveLoop() {
//         if (head == null || head.next == null) {
//             return; // No loop possible
//         }

//         Node slow = head;
//         Node fast = head;

//         // Step 1: Detect if loop exists using Floyd's Algorithm
//         while (fast != null && fast.next != null) {
//             slow = slow.next;           // Move slow by 1
//             fast = fast.next.next;      // Move fast by 2

//             if (slow == fast) {
//                 // Loop detected!
//                 removeLoop(slow);
//                 return;
//             }
//         }

//         // No loop found
//     }

//     // Method to remove the loop once detected
//     private void removeLoop(Node loopNode) {
//         Node ptr1 = head;
//         Node ptr2 = loopNode;

//         // Find the start of the loop
//         // Move ptr1 and ptr2 at same speed until they meet at loop start
//         while (true) {
//             Node temp = ptr2;

//             // Check if ptr1 is in the loop
//             while (temp.next != loopNode && temp.next != ptr1) {
//                 temp = temp.next;
//             }

//             if (temp.next == ptr1) {
//                 // Found the start of loop
//                 temp.next = null; // Remove the loop
//                 break;
//             }

//             ptr1 = ptr1.next;
//         }
//     }

//     // Add node at the end
//     public void add(int data) {
//         Node newNode = new Node(data);
//         if (head == null) {
//             head = newNode;
//             return;
//         }
//         Node temp = head;
//         while (temp.next != null) {
//             temp = temp.next;
//         }
//         temp.next = newNode;
//     }

//     // Create a loop for testing (connect last node to a specific position)
//     public void createLoop(int position) {
//         if (head == null) return;

//         Node temp = head;
//         Node loopNode = null;
//         int count = 1;

//         // Find the node at the given position
//         while (temp.next != null) {
//             if (count == position) {
//                 loopNode = temp;
//             }
//             temp = temp.next;
//             count++;
//         }

//         // Create loop by connecting last node to loopNode
//         if (loopNode != null) {
//             temp.next = loopNode;
//             System.out.println("Loop created: last node connected to node at position " + position);
//         }
//     }

//     // Print the list (with loop detection to avoid infinite loop)
//     public void print() {
//         if (head == null) {
//             System.out.println("List is empty");
//             return;
//         }

//         Node temp = head;
//         Node slow = head;
//         Node fast = head;
//         boolean loopExists = false;

//         System.out.print("List: ");
//         int count = 0;
//         int maxNodes = 20; // Print max 20 nodes to avoid infinite loop

//         while (temp != null && count < maxNodes) {
//             System.out.print(temp.data + " -> ");
//             temp = temp.next;
//             count++;

//             // Check for loop while printing
//             if (fast != null && fast.next != null) {
//                 slow = slow.next;
//                 fast = fast.next.next;
//                 if (slow == fast && !loopExists) {
//                     loopExists = true;
//                     System.out.print("(LOOP DETECTED) ...");
//                     break;
//                 }
//             }
//         }

//         if (!loopExists && temp == null) {
//             System.out.println("null");
//         } else if (count >= maxNodes) {
//             System.out.println("... (stopped printing)");
//         } else {
//             System.out.println();
//         }
//     }

//     public static void main(String[] args) {
//         Dll list = new Dll();

//         // Create the list: 1 -> 2 -> 3 -> 4 -> 5
//         list.add(1);
//         list.add(2);
//         list.add(3);
//         list.add(4);
//         list.add(5);

//         System.out.println("Original List (without loop):");
//         list.print();
//         System.out.println();

//         // Create a loop: connect last node (5) back to node at position 3 (node with value 3)
//         list.createLoop(3);

//         System.out.println("After creating loop:");
//         list.print();
//         System.out.println();

//         // Detect and remove the loop
//         System.out.println("Detecting and removing loop...");
//         list.detectAndRemoveLoop();
//         System.out.println();

//         System.out.println("After removing loop:");
//         list.print();
//     }
// }

public class Dll {
    public class Node {
        int data;
        Node prev = null;
        Node next = null;

        Node(int data) {
            this.data = data;
        }
    }

    Node head;

    public void InsertAtStart(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }

        newNode.next = head;
        head.prev = newNode;
        
        // Updating head 
        head = newNode;
    }

    public void InsertAtEnd(int data) {
        Node newNode = new Node(data);
        Node temp = head;

        if(head == null) {
            head = newNode;
            return;
        }
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.prev = temp;
    }

    public void InsertAtPosition(int position, int data) {
        Node newNode = new Node(data);
        Node temp = head;
        int count = 1;

        // Base Case
        if(position == 1) {
            InsertAtStart(data);
            return;
        }

        while (count < position -1 && temp != null) {
            temp = temp.next;
            count++;
        }

        if(temp.next == null) {
            temp.next = newNode;
            newNode.prev = temp;
            return;
        }

        // Insert At Middle
        Node nextNode = temp.next;

        newNode.next = nextNode;
        nextNode.prev = newNode;
        newNode.prev = temp;
        temp.next = newNode;
    }
    /*
    DoublyLinkedList:
        1) InsertAtStart()
        2) Insertatend()
        3) Insertatposition()
        4) Deleteatstart()
        5) Deleteatend()
        Deleteatposition()
        6) Forwardtraverse()
        7) Backwardtraverse()
        8) Findmid()
        9) SinglyLinkedList isCircular()
        10) SinglyLinedList makeCircular()
        11) DoublyLinkedList isCircular ()
        12) DoublyLinkedList make Circular()
    */
    public static void main(String[] args) {

    }
}