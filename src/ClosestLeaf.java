import base.TreeNode;
import base.Trees;

import java.util.*;

public class ClosestLeaf {

    public int findClosestLeaf(TreeNode root, int k) {
        List<TreeNode> nodes = new ArrayList<>();
        TreeNode kn = dfs(root,k, nodes);
        int[] min = dfsmin(kn);
        for(int i=nodes.size()-1; i>=0; i--){
            int[] cur = dfsmin(nodes.get(i));
            int diff = nodes.size()-i;

            if(cur[0]+diff<min[0]){
                min[0] = cur[0]+diff;
                min[1] = cur[1];
            }
        }
        return min[1];
    }

    private TreeNode dfs(TreeNode n, int k, List<TreeNode> nodes){
        if(n==null){
            return null;
        }
        if(n.val==k){
            return n;
        }
        nodes.add(n);
        TreeNode left = dfs(n.left, k, nodes);
        if(left!= null){
            return left;
        }
        TreeNode right= dfs(n.right, k, nodes);
        if(right!= null){
            return right;
        }
        nodes.remove(nodes.size()-1);
        return null;
    }

    private int Max=10000000;

    private int[] dfsmin(TreeNode n){
        if(n==null){
            return new int[]{Max, -1};
        }
        if(n.left==null && n.right==null){
            return new int[]{0, n.val};
        }
        int[] left = dfsmin(n.left);
        int[] right = dfsmin(n.right);
        if(left[0]<=right[0]){
            return new int[]{left[0]+1, left[1]};
        }else{
            return new int[]{right[0]+1, right[1]};
        }
    }

}


