import base.TreeNode;
import base.Trees;

import java.util.*;

/*
LC#545
Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.  (The values of the nodes may still be duplicates.)

Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Example 1

Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].


Example 2

Input:
    ____1_____
   /          \
  2            3
 / \          /
4   5        6
   / \      / \
  7   8    9  10

Ouput:
[1,2,4,7,8,9,10,6,3]

Explanation:
The left boundary are node 1,2,4. (4 is the left-most node according to definition)
The leaves are node 4,7,8,9,10.
The right boundary are node 1,3,6,10. (10 is the right-most node).
So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 */
public class BoundaryOfBinaryTree {
    // mind the logic for "left" when it comes to root. it's different from rest of the nodes in tree
    // avoid first and last leaves for left, and avoid first, last leaves and root for right
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> r = new ArrayList<>();
        if (root == null) {
            return r;
        }
        List<TreeNode> leaves = new ArrayList<>();
        dfs(root, leaves);
        TreeNode firstleaf = leaves.get(0);
        TreeNode lastleaf = leaves.get(leaves.size() - 1);
        TreeNode p = root;
        if (root.left != null) {
            while (p != null) {
                if (p != firstleaf && p != lastleaf) {
                    r.add(p.val);
                }
                if (p.left != null) {
                    p = p.left;
                } else {
                    p = p.right;
                }
            }
        } else {
            if (root != firstleaf && root != lastleaf) {
                r.add(root.val);
            }
        }
        for (TreeNode t : leaves) {
            r.add(t.val);
        }
        p = root;

        if (root.right != null) {
            List<Integer> right = new ArrayList<>();
            while (p != null) {
                if (p != firstleaf && p != lastleaf && p != root) {
                    right.add(p.val);
                }
                if (p.right != null) {
                    p = p.right;
                } else {
                    p = p.left;
                }
            }
            Collections.reverse(right);
            r.addAll(right);
        }
        return r;
    }

    private void dfs(TreeNode p, List<TreeNode> leaves) {
        if (p == null) {
            return;
        }
        if (p.left == null && p.right == null) {
            leaves.add(p);
            return;
        }
        dfs(p.left, leaves);
        dfs(p.right, leaves);
    }


    public static void main(String[] args) {

        System.out.println(new BoundaryOfBinaryTree().boundaryOfBinaryTree(Trees.fromString("1,2,3,4,5,6,null,null,null,7,8,9,10")));
        System.out.println(new BoundaryOfBinaryTree().boundaryOfBinaryTree(Trees.fromString("1,2,3,4,5,6,11,null,null,7,8,9,10,null,null,null,null,12")));

        System.out.println(new BoundaryOfBinaryTree().boundaryOfBinaryTree(Trees.fromString("1,2,5,3,4,6,7")));
        System.out.println(new BoundaryOfBinaryTree().boundaryOfBinaryTree(Trees.fromString("1,null,2,3,4")));
        System.out.println(new BoundaryOfBinaryTree().boundaryOfBinaryTree(Trees.fromString("1")));
    }
}
