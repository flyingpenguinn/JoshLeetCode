import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
LC#1361

You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.

If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem.



Example 1:



Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
Output: true
Example 2:



Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
Output: false
Example 3:



Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
Output: false
Example 4:



Input: n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
Output: false


Constraints:

1 <= n <= 10^4
leftChild.length == rightChild.length == n
-1 <= leftChild[i], rightChild[i] <= n - 1
 */
public class ValidateBinaryTreeNodes {
    // 1. find root, only one root
    // 2. no cycle, all nodes visited (connected)
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        Set<Integer> nodes = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            nodes.add(i);
        }
        for (int lc : leftChild) {
            if (lc != -1) {
                nodes.remove(lc);
            }
        }
        for (int rc : rightChild) {
            if (rc != -1) {
                nodes.remove(rc);
            }
        }
        if (nodes.size() != 1) {
            return false;
        }
        int root = nodes.iterator().next();
        Set<Integer> seen = new HashSet<>();
        boolean cycle = dfs(root, leftChild, rightChild, seen);
        return !cycle && seen.size() == n;
    }

    private boolean dfs(int root, int[] leftChild, int[] rightChild, Set<Integer> seen) {
        seen.add(root);
        int lc = leftChild[root];
        if (lc != -1 && seen.contains(lc)) {
            return true;
        }
        boolean later = lc == -1 ? false : dfs(lc, leftChild, rightChild, seen);
        if (later) {
            return true;
        }
        int rc = rightChild[root];
        if (rc != -1 && seen.contains(rc)) {
            return true;
        }
        return rc == -1 ? false : dfs(rc, leftChild, rightChild, seen);
    }
}
