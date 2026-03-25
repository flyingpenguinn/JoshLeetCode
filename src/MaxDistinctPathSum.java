import base.TreeNode;
import base.Trees;

import java.util.*;


public class MaxDistinctPathSum {

    private final Map<TreeNode, List<TreeNode>> g = new HashMap<>();
    private int res = (int) -1e9;

    public int maxSum(TreeNode root) {
        dfs(root, null);
        for (TreeNode k : g.keySet()) {
            Set<Integer> seen = new HashSet<>();
            dfs2(k, null, seen, 0);
        }
        return res;
    }

    private void dfs2(TreeNode i, TreeNode p, Set<Integer> seen, int csum) {
        csum += i.val;
        res = Math.max(res, csum);
        seen.add(i.val);
        for (TreeNode ne : g.getOrDefault(i, new ArrayList<>())) {
            if (ne == p) {
                continue;
            }
            if (seen.contains(ne.val)) {
                continue;
            }
            dfs2(ne, i, seen, csum);
        }
        seen.remove(i.val);
    }

    private void dfs(TreeNode n, TreeNode par) {
        g.put(n, new ArrayList<>());
        if (par != null) {
            g.computeIfAbsent(n, k -> new ArrayList<>()).add(par);
        }
        if (n.left != null) {
            g.computeIfAbsent(n, k -> new ArrayList<>()).add(n.left);
            dfs(n.left, n);
        }
        if (n.right != null) {
            g.computeIfAbsent(n, k -> new ArrayList<>()).add(n.right);
            dfs(n.right, n);
        }
    }

    static void main() {
        System.out.println(new MaxDistinctPathSum().maxSum(Trees.fromString("[-541,-20,127,172,-880,-44,-379,-732,278,544,649,566,210,-738,-610,-700,284,-140,780,-190,-566,750,-973,-941,-949,-163,-255,890,-27,-483,-950,null,-234,514,389,null,-707,769,213,null,-173,668,973,968,-999,-802,59,null,869,-312,-507,null,993,467,769,null,58,-82,991,-773,750,635,926]")));
        System.out.println(new MaxDistinctPathSum().maxSum(Trees.fromString("[2,2,1]")));
        System.out.println(new MaxDistinctPathSum().maxSum(Trees.fromString("[1,-2,5,null,null,3,5]")));
        System.out.println(new MaxDistinctPathSum().maxSum(Trees.fromString("[858,395,-610,-376,-453,529,-19,-866,-938,-805,232,-453,887,439,695,null,null,null,null,null,null,null,215,null,null,null,null,null,null,null,-782]")));
    }
}
