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
        // binary lifting!
        private int[][] dp;

        public TreeAncestor(int n, int[] parent) {
            // dp[i][j] means from j walk 2^i steps we get the ancestor
            dp = new int[17][n];
            for (int i = 0; i < n; ++i) {
                dp[0][i] = parent[i];
            }
            for (int i = 1; i < 17; ++i) {
                for (int j = 0; j < n; ++j) {
                    int mid = dp[i - 1][j];
                    if (mid != -1) {
                        dp[i][j] = dp[i - 1][mid];
                    } else {
                        dp[i][j] = -1;
                    }
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            int cur = node;
            for (int i = 0; i < 17; ++i) {
                if (((1 << i) & k) == 0) {
                    continue;
                }
                cur = dp[i][cur];
                if (cur == -1) {
                    break;
                }
            }
            return cur;
        }
    }

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */
}