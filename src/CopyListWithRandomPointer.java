import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
LC#138
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.



Example 1:



Input:
{"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

Explanation:
Node 1's value is 1, both of its next and random pointer points to Node 2.
Node 2's value is 2, its next pointer points to null and its random pointer points to itself.


Note:

You must return the copy of the given head as a reference to the cloned list.
 */

// just treating it as graph dfs
/*
We can avoid using extra space for old node ---> new node mapping, by tweaking the original linked list. Simply interweave the nodes of the old and copied list. For e.g.
Old List: A --> B --> C --> D
InterWeaved List: A --> A' --> B --> B' --> C --> C' --> D --> D'
 */
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {
    }

    public Node(int _val, Node _next, Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};

public class CopyListWithRandomPointer {
    // similar to graph clone
    Map<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        return dfs(head);
    }

    Node dfs(Node p) {
        if (p == null) {
            return null;
        }
        if (map.containsKey(p)) {
            return map.get(p);
        }
        Node cp = new Node(p.val, null, null);
        map.put(p, cp);

        Node cn = dfs(p.next);
        Node cr = dfs(p.random);
        cp.next = cn;
        cp.random = cr;
        return cp;
    }

}
