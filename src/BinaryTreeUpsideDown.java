import base.TreeNode;

/*
LC#156
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.


Example:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
 */
public class BinaryTreeUpsideDown {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        return dou(root)[0];
    }

    // return new head and the right most node to connect to
    private TreeNode[] dou(TreeNode node) {
        if (node == null) {
            return new TreeNode[]{null, null};
        }
        if (node.left == null) {
            return new TreeNode[]{node, node};
        }
        TreeNode[] newnodes = dou(node.left);
        newnodes[1].right = node;
        newnodes[1].left = node.right;
        node.right = null;
        node.left = null;
        return new TreeNode[]{newnodes[0], node};
    }
}
