import base.TreeNode;

/*
LC#114
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
 */
public class FlattenBinaryTreeToLinkedList {
    TreeNode pre = null;

    public void flatten(TreeNode root) {
        dfs(root);
    }

    void dfs(TreeNode cur) {
        if (cur == null) {
            return;
        }
        if (pre != null) {
            pre.right = cur;
            pre.left = null; // last is leaf so no need to set
        }
        pre = cur;
        TreeNode cr = cur.right;
        dfs(cur.left);
        dfs(cr); // as right will be overridden in dfs left
    }

}
