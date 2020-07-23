
import java.util.*;
/*
LC#428
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree. An N-ary tree is a rooted tree in which each node has no more than N children. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree



as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this format.

Or you can follow LeetCode's level order traversal serialization format, where each group of children is separated by the null value.



For example, the above tree may be serialized as [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].

You do not necessarily need to follow the above suggested formats, there are many more different formats that work so please be creative and come up with different approaches yourself.



Constraints:

The height of the n-ary tree is less than or equal to 1000
The total number of nodes is between [0, 10^4]
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 */

public class SerializeAndDeserializeNrayTree {

    // store on level order or pre order, but use node val: node children count, .... format, don't use other ways!

    public static void main(String[] args) {
        CodecNrayTreeStack cts = new CodecNrayTreeStack();
        System.out.println(cts.new Codec().deserialize("1,3,3,2,5,0,6,0,2,0,4,0"));
    }
}

class CodecNrayTree {
    // for each node record the number of children then do level based search. leetcode style
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    final String Sep = ":";

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Deque<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            Node top = q.pop();
            List<Node> ch = top.children;
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(top.val + Sep + ch.size());
            for (Node n : ch) {
                q.offer(n);
            }
        }
        return sb.toString();
    }

    class QueueItem {
        Node n;
        int ch;

        public QueueItem(Node n, int ch) {
            this.n = n;
            this.ch = ch;
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] ss = data.split(",");
        Deque<QueueItem> q = new ArrayDeque<>();
        String[] sss = ss[0].split(Sep);
        Node root = tonode(sss[0]);
        q.offer(new QueueItem(root, Integer.valueOf(sss[1])));
        for (int i = 1; i < ss.length; i++) {
            String[] tops = ss[i].split(Sep);
            Node node = tonode(tops[0]);
            q.peek().n.children.add(node);
            if (q.peek().n.children.size() == q.peek().ch) {
                q.poll();
            }
            Integer topcount = Integer.valueOf(tops[1]);
            if (topcount > 0) {
                // dont have this headache in binary tree because we state all null values there
                q.offer(new QueueItem(node, topcount));
            }
        }
        return root;
    }

    private Node tonode(String sss) {
        Node n = new Node(Integer.valueOf(sss));
        n.children = new ArrayList<>();
        return n;
    }

}


class CodecNrayTreeStack {
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    class Codec {
        // Encodes a tree to a single string.
        public String serialize(Node root) {
            if (root == null) {
                return "#";
            }
            StringBuilder sb = new StringBuilder();
            sb.append(root.val);
            sb.append(",");
            // assuming chilcren list always non null. so for leaves we will get 0
            sb.append(root.children.size());
            sb.append(",");
            for (Node ch : root.children) {
                String str = serialize(ch);
                sb.append(str);
            }
            return sb.toString();
        }

        class StackItem {
            private Node node;
            private int ccount;

            public StackItem(Node node, int count) {
                this.node = node;
                this.ccount = count;
            }
        }

        // Decodes your encoded data to tree.
        public Node deserialize(String data) {
            if ("#".equals(data)) {
                return null;
            }
            String[] ss = data.split(",");
            Node root = null;
            Deque<StackItem> st = new ArrayDeque<>();
            for (int i = 0; i < ss.length; i += 2) {
                // st.peek either empty or is the last ele that is waiting for child
                Node cur = new Node(Integer.valueOf(ss[i]), new ArrayList<>());
                if (root == null) {
                    root = cur;
                }
                int count = Integer.valueOf(ss[i + 1]);
                if (!st.isEmpty()) {
                    StackItem peek = st.peek();
                    peek.node.children.add(cur);
                }
                st.push(new StackItem(cur, count));
                while (!st.isEmpty()) {
                    if (st.peek().ccount == st.peek().node.children.size()) {
                        st.pop();
                    }
                }
            }
            return root;
        }
    }
}