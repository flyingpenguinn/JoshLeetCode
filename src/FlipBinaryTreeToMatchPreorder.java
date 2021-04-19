import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FlipBinaryTreeToMatchPreorder {
    // v.length == n so we dont need to worry about edge cases where i overflows.
    // similar tree recursions: LowestCommonAncestorOfDeepestLeaf and SplitBst
    private List<Integer> res = new ArrayList<>();

    private int i = 0;
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        boolean good = dfs(root, voyage);
        return good ? res: List.of(-1);
    }

    private boolean dfs(TreeNode n, int[] v){
        if(n==null){
            return true;
        }else if(n.val != v[i]){
            return false;
        }
        i++;
        if(n.left != null){
            if(n.left.val != v[i]){
                res.add(n.val);
                return dfs(n.right,v) &&  dfs(n.left, v);
            }else{
                return dfs(n.left,v) && dfs(n.right, v);

            }
        }else{
            return dfs(n.right, v);
        }
    }
}
