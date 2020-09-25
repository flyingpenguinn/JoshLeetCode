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
    // similar to Tarjan sparse table rmq algo: nlgn to initalize, lgn to look up
    class TreeAncestor {
        private int[][] dep;

        public TreeAncestor(int n, int[] parent) {
            dep = new int[n][17];
            for (int i = 0; i < n; i++) {
                dep[i][0] = parent[i];
            }
            for (int d = 1; d <= 16; d++) {
                for (int i = 0; i < n; i++) {
                    int ans = dep[i][d - 1];
                    if (ans == -1) {
                        dep[i][d] = -1;
                    } else {
                        dep[i][d] = dep[ans][d - 1];
                    }
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            if (node == -1) {
                return -1;
            }
            if (k == 1) {
                return dep[node][0];
            }
            int d = 0;
            while ((1 << d) < k) {
                d++;
            }
            int lower = d - 1;
            int half = dep[node][lower];
            return getKthAncestor(half, k - (1 << lower));
        }
    }
}
