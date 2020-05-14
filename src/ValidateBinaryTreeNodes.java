import java.util.Arrays;

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
    // directed graph check treeness
    // 1. no circle  2. only one parent per node  3. only one root
    int[] pa;
    boolean bad = false;
    int[] v;

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        pa = new int[n];
        v = new int[n];
        Arrays.fill(pa, -1);
        for (int i = 0; i < n; i++) {
            if (v[i] == 0) {
                dfs(i, leftChild, rightChild, -1);
            }
        }
        if (bad) {
            return false;
        }
        int c = 0;
        for (int i = 0; i < n; i++) {
            if (pa[i] == -1) {
                c++;
                if (c >= 2) {
                    return false;
                }
            }
        }
        return true;
    }

    private void dfs(int i, int[] leftChild, int[] rightChild, int parent) {
        // can't have two parents
        if (pa[i] != -1 && pa[i] != parent) {
            bad = true;
            return;
        }
        pa[i] = parent;
        if (v[i] == 2) {
            return;
        }
        v[i] = 1;
        int lc = leftChild[i];
        if (lc != -1) {
            if (v[lc] == 1) {
                bad = true;
                return;
            }
            dfs(lc, leftChild, rightChild, i);
        }
        int rc = rightChild[i];
        if (rc != -1) {
            if (v[rc] == 1) {
                bad = true;
                return;
            }
            dfs(rc, leftChild, rightChild, i);
        }
        v[i] = 2;
    }
}
