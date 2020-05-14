import java.util.ArrayList;
import java.util.List;

/*
LC#1273
A tree rooted at node 0 is given as follows:

The number of nodes is nodes;
The value of the i-th node is value[i];
The parent of the i-th node is parent[i].
Remove every subtree whose sum of values of nodes is zero.

After doing so, return the number of nodes remaining in the tree.



Example 1:



Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
Output: 2


Constraints:

1 <= nodes <= 10^4
-10^5 <= value[i] <= 10^5
parent.length == nodes
parent[0] == -1 which indicates that 0 is the root.
 */
public class DeleteTreeNodes {

    List<Integer>[] children;
    int[] v;

    public int deleteTreeNodes(int n, int[] p, int[] v) {
        children = new ArrayList[n];
        this.v = v;
        for (int i = 0; i < children.length; i++) {
            children[i] = new ArrayList<>();
        }

        int root = -1;
        for (int i = 0; i < n; i++) {
            if (p[i] == -1) {
                root = i;
            } else {
                children[p[i]].add(i);
            }
        }
        int[] res = dfs(root);
        return n - res[1];
    }

    // sum, how many removed, num of nodes in the subtree
    private int[] dfs(int root) {
        if (root == -1) {
            return new int[]{0, 0, 0};
        }
        int cursum = v[root];
        int nodes = 1;
        int deleted = 0;
        for (int i = 0; i < children[root].size(); i++) {
            int[] cv = dfs(children[root].get(i));
            cursum += cv[0];
            deleted += cv[1];
            nodes += cv[2];
        }

        if (cursum == 0) {
            return new int[]{cursum, nodes, nodes};
        } else {
            return new int[]{cursum, deleted, nodes};
        }
    }
}
