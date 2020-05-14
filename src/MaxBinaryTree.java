import base.ArrayUtils;
import base.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxBinaryTree {
    // this is different in shape from heap which is a complete tree
    public TreeNode constructMaximumBinaryTree(int[] a) {
        return domax(a, 0, a.length - 1);
    }

    TreeNode domax(int[] a, int l, int u) {
        if (l > u) {
            return null;
        }
        int max = a[l];
        int mj = l;
        for (int j = l + 1; j <= u; j++) {
            if (max < a[j]) {
                max = a[j];
                mj = j;
            }
        }
        TreeNode rt = new TreeNode(max);
        rt.left = domax(a, l, mj - 1);
        rt.right = domax(a, mj + 1, u);
        return rt;

    }

    public static void main(String[] args) {
        System.out.println(new MaxBinaryTreeStack().constructMaximumBinaryTree(ArrayUtils.read1d("3,2,1,6,0,5")));
    }
}

class MaxBinaryTreeStack {

    // https://leetcode.com/problems/maximum-binary-tree/discuss/106147/c-8-lines-on-log-n-map-plus-stack-with-binary-search
    public TreeNode constructMaximumBinaryTree(int[] a) {
        // mono stack from big to small. the local bigger but smallest one on cur's left has cur as right child.
        // the local smaller but biggest one on cur's left has potential to be cur's left
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            TreeNode cur = new TreeNode(a[i]);
            while (!stack.isEmpty() && stack.peek().val < a[i]) {
                TreeNode node = stack.pop();
                cur.left = node;
            }
            if (!stack.isEmpty()) {
                stack.peek().right = cur;
            }
            stack.push(cur);
        }
        while (stack.size() > 1) {
            stack.pop();
        }
        return stack.pop();
    }
}
