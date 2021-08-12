import java.util.*;

/*
LC#432
Implement a data structure supporting the following operations:

Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
Challenge: Perform all these in O(1) time complexity.
 */
public class AllOoneDataStructure {
    public static void main(String[] args) {
        //test1();
        test2();
    }

    private static void test1() {
        AllOne obj = new AllOne();
        obj.inc("abc");
        obj.inc("abc");
        obj.inc("cde");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        obj.dec("cde");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        obj.inc("def");
        obj.inc("def");
        obj.inc("def");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        obj.inc("abc");
        obj.inc("abc");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        obj.inc("cde");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
    }

    private static void test2() {
        AllOne obj = new AllOne();
        obj.inc("a");
        obj.inc("b");
        obj.inc("b");
        obj.inc("c");
        obj.inc("c");
        obj.inc("c");
        obj.dec("b");
        obj.dec("b");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());
        obj.dec("a");
        System.out.println(obj.getMaxKey());
        System.out.println(obj.getMinKey());

    }
}

class AllOne {
    // we can maintain a sorted list in o1 time if it's a linked list and we only add/dec by 1
    // different from lfu cache: there is no remove there! so when we are evicting, the smallest is always the newly inserted one with freq = 1. not an optoin here
    // becaues we could be removing freq= 1 element. the next min is 4 and we won't know
    private class Node {

        public Node(int val) {
            this.val = val;
        }

        private int val;
        private Node next;
        private Node prev;
    }

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
        head.next = tail;
        head.prev = tail;
        tail.next = head;
        tail.prev = head;
    }

    private Node head = new Node(-1);     // head and tail dummy nodes
    private Node tail = new Node(-1);

    private Map<String, Integer> m = new HashMap<>();
    private Map<Integer, Node> int2node = new HashMap<>();
    private Map<Integer, Set<String>> int2string = new HashMap<>();


    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        int ov = m.getOrDefault(key, 0);
        int nv = ov + 1;
        insertNewNode(ov, nv, true);
        cleanupOld(ov, key);
        m.put(key, nv);
        int2string.computeIfAbsent(nv, k -> new HashSet<>()).add(key);
    }
    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        int ov = m.getOrDefault(key, 0);
        if (ov == 0) {
            return;
        } else {
            int nv = ov - 1;
            if (nv == 0) {
                m.remove(key);
            } else {
                m.put(key, nv);
                int2string.computeIfAbsent(nv, k -> new HashSet<>()).add(key);
                insertNewNode(ov, nv, false);
            }
            cleanupOld(ov, key);
        }
    }

    private void cleanupOld(int ov, String key) {
        if (ov == 0) {
            return;
        }
        Set<String> oldset = int2string.get(ov);
        oldset.remove(key);
        Node oldNode = int2node.get(ov);
        if (oldset.isEmpty()) {
            int2node.remove(ov);
            removeNode(oldNode);
        }
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        if (tail.prev == head) {
            return "";
        }
        return int2string.get(tail.prev.val).iterator().next();
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        if (tail.prev == head) {
            return "";
        }
        return int2string.get(head.next.val).iterator().next();
    }

    private void insertNewNode(int ov, int nv, boolean after) {
        Node oldNode = ov == 0 ? head : int2node.get(ov);
        Node newNode = int2node.get(nv);
        if (newNode == null) {
            newNode = new Node(nv);
            if (after) {
                insertAfter(oldNode, newNode);
            } else {
                insertBefore(oldNode, newNode);
            }
            int2node.put(nv, newNode);
        }
    }

    private void insertAfter(Node n1, Node n2) {
        // insert n2 after n1
        Node oldn1next = n1.next;
        n1.next = n2;
        n2.prev = n1;
        n2.next = oldn1next;
        oldn1next.prev = n2;
    }

    private void insertBefore(Node n1, Node n2) {
        // n2 before n1
        Node oldn1prev = n1.prev;
        n2.prev = oldn1prev;
        n2.next = n1;
        n1.prev = n2;
        oldn1prev.next = n2;
    }

    private void removeNode(Node n) {
        Node prev = n.prev;
        Node next = n.next;
        prev.next = next;
        next.prev = prev;
    }
}
