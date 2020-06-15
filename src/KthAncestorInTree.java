import java.util.ArrayList;
import java.util.List;

/*
LC#
You are given a tree with n nodes numbered from 0 to n-1 in the form of a parent array where parent[i] is the parent of node i. The root of the tree is node 0.

Implement the function getKthAncestor(int node, int k) to return the k-th ancestor of the given node. If there is no such ancestor, return -1.

The k-th ancestor of a tree node is the k-th node in the path from that node to the root.



Example:



Input:
["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
[[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]

Output:
[null,1,0,-1]

Explanation:
TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);

treeAncestor.getKthAncestor(3, 1);  // returns 1 which is the parent of 3
treeAncestor.getKthAncestor(5, 2);  // returns 0 which is the grandparent of 5
treeAncestor.getKthAncestor(6, 3);  // returns -1 because there is no such ancestor


Constraints:

1 <= k <= n <= 5*10^4
parent[0] == -1 indicating that 0 is the root node.
0 <= parent[i] < n for all 0 < i < n
0 <= node < n
There will be at most 5*10^4 queries.

 */
public class KthAncestorInTree {
    // sparse table concept
    // @TODO can be simplified
    class TreeAncestor {
        class Node {
            List<Node> children = new ArrayList<>();
            int val;

            public Node(int val) {
                this.val = val;
            }
        }

        int n = 0;
        int[] pa = null;
        List<Integer>[] paths; // 1, 2, 4, 8,16...
        int[] depth = new int[n];
        Node[] nodes;
        Node root;

        public TreeAncestor(int n, int[] pa) {
            this.n = n;
            this.pa = pa;
            paths = new ArrayList[n];
            depth = new int[n];

            nodes = new Node[n];
            for (int i = 0; i < n; i++) {
                int parent = pa[i];
                if (nodes[i] == null) {
                    nodes[i] = new Node(i);
                }
                if (parent == -1) {
                    root = nodes[i];
                }
                if (parent >= 0) {
                    if (nodes[parent] == null) {
                        nodes[parent] = new Node(parent);
                    }
                    nodes[parent].children.add(nodes[i]);

                }
            }
            dfs(root, new ArrayList<>(), 0);
        }

        int count = 0;

        private void dfs(Node node, ArrayList<Integer> path, int d) {
            if (node == null) {
                return;
            }
            depth[node.val] = d;
            path.add(node.val);
            for (Node ch : node.children) {
                dfs(ch, path, d + 1);
            }
            List<Integer> p = new ArrayList<>();
            for (int i = 1; i < path.size(); i *= 2) {
                // n-2, n-3, n-5...
                p.add(path.get(path.size() - 1 - i));
            }
            paths[node.val] = p;
            path.remove(path.size() - 1);
        }


        public int getKthAncestor(int n, int k) {
            int dep = depth[n];
            if (k > dep) {
                return -1;
            }

            List<Integer> cp = paths[n];
            int delta = 1;
            int i = 0;
            while (k > delta && i < cp.size()) {
                delta *= 2;
                i++;
            }
            if (i == cp.size()) {
                int remk = k - delta / 2;
                return getKthAncestor(cp.get(cp.size() - 1), remk);
            }
            if (k == delta) {
                return cp.get(i);
            }
            delta /= 2;
            i--;
            int remk = k - delta;
            return getKthAncestor(cp.get(i), remk);
        }
    }
}
