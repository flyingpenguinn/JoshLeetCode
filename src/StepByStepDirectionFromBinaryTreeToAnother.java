import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class StepByStepDirectionFromBinaryTreeToAnother {
    private List<StringBuilder> sbs = new ArrayList<>();
    public String getDirections(TreeNode root, int s, int t) {
        TreeNode lca = lca(root, s, t);
        dfs(lca, s, new StringBuilder());
        dfs(lca, t, new StringBuilder());
        StringBuilder np1 = new StringBuilder();
        for(int i=0; i<sbs.get(0).length(); ++i){
            np1.append("U");
        }
        return np1.toString() + sbs.get(1).toString();
    }

    private TreeNode lca(TreeNode n, int s, int t){
        if(n==null){
            return null;
        }
        TreeNode left = lca(n.left, s, t);
        TreeNode right = lca(n.right, s, t);
        if(left != null && right!= null){
            return n;
        }else if(n.val==s || n.val == t){
            return n;
        }else{
            return left != null? left: right;
        }
    }

    private void dfs(TreeNode n, int i, StringBuilder path){
        if(n==null){
            return;
        }
        if(n.val == i){
            sbs.add(new StringBuilder(path));
        }else{
            path.append("L");
            dfs(n.left, i, path);
            path.deleteCharAt(path.length()-1);
            path.append("R");
            dfs(n.right, i, path);
            path.deleteCharAt(path.length()-1);
        }
    }
}
