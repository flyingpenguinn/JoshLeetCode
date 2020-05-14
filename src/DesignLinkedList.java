public class DesignLinkedList {
}


class MyLinkedList {

    class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    private Node head = new Node(0, null);

    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {

    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        Node p = head;
        if (index < 0) {
            return -1;
        }
        while (index >= 0) {
            p = p.next;
            if (p == null) {
                return -1;
            }
            index--;
        }
        return p.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        Node newZero = new Node(val, null);
        Node next = head.next;
        head.next = newZero;
        newZero.next = next;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        Node p = head;
        // find the last node
        while (p.next != null) {
            p = p.next;
        }
        Node np = new Node(val, null);
        p.next = np;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        Node p = head;
        Node prev = null;
        if (index < 0) {
            addAtHead(val);
            return;
        }
        while (index >= 0) {
            if (p == null) {
                return;
            }
            prev = p;
            p = p.next;
            index--;
        }
        // p is the next for np! should set p = np.next properly
        Node np = new Node(val, p);
        prev.next = np;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        Node p = head;
        Node prev = null;
        if (index < 0) {
            return;
        }
        while (index >= 0) {
            if (p == null) {
                return;
            }
            prev = p;
            p = p.next;
            index--;
        }
        Node prevnext = null;
        if (p != null) {
            prevnext = p.next;
        }
        prev.next = prevnext;
    }
}