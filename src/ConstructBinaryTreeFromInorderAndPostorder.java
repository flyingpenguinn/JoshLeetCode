import base.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer,Integer> inmap = new HashMap<>();
        for(int i=0; i<inorder.length;i++){
            inmap.put(inorder[i], i);
        }
        return dfs(inmap, inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }

    private TreeNode dfs(Map<Integer,Integer> inmap, int[] inorder, int il, int iu, int[] postorder, int pl, int pu){
        // i and p length are synced
        if(il>iu){
            return null;
        }
        else if(il==iu){
            return new TreeNode(inorder[il]);
        }else{
            int inroot = inmap.get(postorder[pu]);
            int leftlen = inroot - il;
            int postright = pl+leftlen;
            TreeNode root = new TreeNode(postorder[pu]);
            TreeNode left  = dfs(inmap, inorder, il, inroot-1, postorder, pl, postright-1);
            TreeNode right = dfs(inmap, inorder, inroot+1, iu, postorder, postright, pu-1);
            root.left = left;
            root.right = right;
            return root;
        }
    }
}
