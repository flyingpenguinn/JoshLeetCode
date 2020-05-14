import base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;


/*
LC #1161
Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level X such that the sum of all the values of nodes at level X is maximal.



Example 1:



Input: [1,7,0,7,-8,null,null]
Output: 2
Explanation:
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.


Note:

The number of nodes in the given tree is between 1 and 10^4.
-10^5 <= node.val <= 10^5
 */
public class MaxLevelSumOfBinaryTree {
    // bfs. at start of each level q size== level size
    public int maxLevelSum(TreeNode root) {
        if(root==null){
            return 0;
        }
        Deque<TreeNode> q= new ArrayDeque<>();
        q.offer(root);
        int maxsum= Integer.MIN_VALUE;
        int maxl=1;
        int l=1;
        int lsum=0;
        int lsize=1;
        while(!q.isEmpty()){
            TreeNode top = q.poll();

            if(top.left!=null){
                q.offer(top.left);
            }
            if(top.right!=null){
                q.offer(top.right);
            }
            lsum += top.val;
            lsize--;
            if(lsize==0){
                // must do reconing at end
                if(lsum>maxsum){
                    maxsum= lsum;
                    maxl=l;
                }
                // this level done
                l++;
                lsize=q.size();
                lsum=0;
            }
        }
        return maxl;
    }
}

class MaxLevelSumDfs {
    Map<Integer, Integer> map = new HashMap<>();


    public int maxLevelSum(TreeNode root) {
        dfs(root, 1);
        int maxv = 0;
        int maxl = Integer.MIN_VALUE;
        for (int k : map.keySet()) {
            int v = map.get(k);
            if (v > maxv || (v == maxv && k < maxl)) {
                maxv = v;
                maxl = k;
            }
        }
        return maxl;
    }

    // cant compare in dfs as we may do minus
    void dfs(TreeNode root, int d) {
        if (root == null) {
            return;
        }
        int v = map.getOrDefault(d, 0) + root.val;
        map.put(d, v);
        dfs(root.left, d + 1);
        dfs(root.right, d + 1);

    }
}
