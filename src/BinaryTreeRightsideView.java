import base.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LC#199
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
 */
public class BinaryTreeRightsideView {
    List<Integer> r = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        return r;
    }

    void dfs(TreeNode n, int dep){
        if(n==null){
            return;
        }
        if(r.size()==dep){
            r.add(n.val);
        }else{
            r.set(dep, n.val);
        }
        dfs(n.left, dep+1);
        dfs(n.right, dep+1);
    }
}
