import base.TreeNode;
import base.Trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
LC#366
Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.



Example:

Input: [1,2,3,4,5]

          1
         / \
        2   3
       / \
      4   5

Output: [[4,5,3],[2],[1]]


Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         /
        2


2. Now removing the leaf [2] would result in this tree:

          1


3. Now removing the leaf [1] would result in the empty tree:

          []
 */
public class FindLeavesOfBinaryTree {
    // larget distance from leaf
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> rl = new ArrayList<>();
        dfs(root, rl);
        return rl;
    }

    int dfs(TreeNode root, List<List<Integer>> rl) {
        if (root == null) {
            return 0;
        }
        int cl = dfs(root.left, rl);
        int cr = dfs(root.right, rl);
        int max = Math.max(cl, cr) + 1;
        if (max - 1 == rl.size()) {
            rl.add(new ArrayList<>());
        }
        rl.get(max - 1).add(root.val);
        return max;
    }

    public static void main(String[] args) {
        TreeNode node = Trees.fromString("1,2,3,4,5");
        System.out.println(new FindLeavesOfBinaryTree().findLeaves(node));
    }
}
