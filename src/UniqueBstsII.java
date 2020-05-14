import base.TreeNode;

import java.util.ArrayList;
import java.util.List;

/*
LC#95
Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */
public class UniqueBstsII {
    List<TreeNode>[][] dp;
    public List<TreeNode> generateTrees(int n) {
        if(n==0){
            return new ArrayList<>();
        }
        dp=new ArrayList[n+2][n+2];
        return dog(1,n);
    }

    List<TreeNode> dog(int l,int u){
        List<TreeNode> r= new ArrayList<>();
        if(l>u){
            r.add(null);
        }
        if(dp[l][u] != null){
            return dp[l][u];
        }
        // enumerate root at i
        for(int i=l;i<=u;i++){
            List<TreeNode> left= dog(l,i-1);
            List<TreeNode> right= dog(i+1,u);
            for(TreeNode ln:left){
                for(TreeNode rn:right){
                    TreeNode rt= new TreeNode(i);
                    rt.left=ln;
                    rt.right=rn;
                    r.add(rt);
                }
            }
        }
        dp[l][u] =r;
        return r;
    }
}
