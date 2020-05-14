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
    // different from lfu cache: there is no remove there! so when we are evicting, the smallest is always freq = 1. not an optoin here
    // becaues we could jump from freq 4 to 1
    class LNode {
        int val;
        LNode next;
        LNode prev;

        public LNode(int val) {
            this.val = val;
        }
    }

    Map<String, Integer> m = new HashMap<>();
    Map<Integer, Set<String>> cm = new HashMap<>();
    Map<Integer, LNode> lm = new HashMap<>();
    LNode head = null;
    LNode tail = null;

    /**
     * Initialize your data structure here.
     */
    public AllOne() {

    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        updatemap(key, 1);
    }

    private void updatemap(String key, int delta) {
        int oc = m.getOrDefault(key, 0);
        int nc = oc + delta;
        if (nc > 0) {
            m.put(key, nc);
        } else {
            m.remove(key);
        }
        Set<String> oset = cm.getOrDefault(oc, new HashSet<>());
        oset.remove(key);
        if (oset.isEmpty()) {
            cm.remove(oc);
        }
        if (nc > 0) {
            Set<String> nset = cm.getOrDefault(nc, new HashSet<>());
            nset.add(key);
            cm.put(nc, nset);
        }
        adjlist(oc, delta);
    }

    // on exist or not? on gone or not? nn new node or not?
    private void adjlist(int oc, int delta) {
        if (oc == 0) {
            // on doesnt exist, either we are over reducing, or this is a new one node
            if (delta < 0) {
                return;
            }
            // nc must be 1 because oc = 0. we add new node nn
            LNode one = lm.get(1);
            if (one == null) {
                one = new LNode(1);
                lm.put(1, one);
                one.next = head;
                if (head != null) {
                    head.prev = one;
                }
                head = one;
                if (tail == null) {
                    tail = one;
                }
            }
            // or do nothing here if node one already exists
        } else {
            // on exists
            int nc = oc + delta;
            LNode on = lm.get(oc);
            Set<String> oset = cm.get(oc);
            if (oset == null || oset.isEmpty()) {
                // but on needs to go
                if (nc > 0) {
                    // on gone, but nc is >0. need to removelist on, and insert nn if it doesnt exist
                    LNode nn = lm.get(nc);
                    if (nn == null) {
                        // if nn is a new node we flip on to nn
                        on.val = nc;
                        lm.remove(oc);
                        lm.put(nc, on);
                        return;
                    } else {
                        // otherwise just removelist on. keep nn as existing node
                        removelist(on);
                        lm.remove(oc);
                    }
                } else {
                    // nc == 0, just removelist on, no need to add nn. this happens when nc==0 and on==1
                    removelist(on);
                    lm.remove(oc);
                }
            } else {
                // keep on, nn exist or not?
                LNode nn = lm.get(nc);
                if (nn != null || nc == 0) {
                    // keep on, nn exists, do nothing
                    return;
                }
                // keep on, add nn as new node
                nn = new LNode(nc);
                lm.put(nc, nn);
                if (delta > 0) {
                    LNode onext = on.next;
                    on.next = nn;
                    nn.prev = on;
                    if (onext != null) {
                        onext.prev = nn;
                        nn.next = onext;
                    }
                    if (tail == on) {
                        tail = nn;
                    }
                } else {
                    LNode opre = on.prev;
                    on.prev = nn;
                    nn.next = on;
                    if (opre != null) {
                        opre.next = nn;
                        nn.prev = opre;
                    }
                    if (head == on) {
                        head = nn;
                    }
                }

            }
        }
    }

    private void removelist(LNode on) {
        LNode op = on.prev;
        LNode onext = on.next;
        if (op != null) {
            op.next = onext;
        }
        if (onext != null) {
            onext.prev = op;
        }
        if (tail == on) {
            tail = op;
        }
        if (head == on) {
            head = onext;
        }
    }

    public void dec(String key) {
        updatemap(key, -1);
    }

    public String getMaxKey() {
        return getnodestring(tail);
    }

    String getnodestring(LNode node) {
        return node == null ? "" : cm.get(node.val).iterator().next();
    }

    public String getMinKey() {
        return getnodestring(head);
    }
}
