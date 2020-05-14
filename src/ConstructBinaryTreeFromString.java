import base.TreeNode;
import base.Trees;

import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#536
You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

Example:
Input: "4(2(3)(1))(6(5))"
Output: return the tree root node representing the following tree:

       4
     /   \
    2     6
   / \   /
  3   1 5
Note:
There will only be '(', ')', '-' and '0' ~ '9' in the input string.
An empty tree is represented by "" instead of "()".
 */
public class ConstructBinaryTreeFromString {
    public TreeNode str2tree(String s) {
        return dostr(s);
    }

    private TreeNode dostr(String s) {
        if (s.isEmpty()) {
            return null;
        }
        int i = 0;
        for (; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                break;
            }
        }
        if (i == s.length()) {
            return new TreeNode(Integer.valueOf(s));
        } else {
            TreeNode root = new TreeNode(Integer.valueOf(s.substring(0, i)));
            int level = 0;
            int start = i;
            boolean isleft = true;

            while (i < s.length()) {
                if (s.charAt(i) == ')') {
                    level--;
                    if (level == 0) {
                        TreeNode nd = dostr(s.substring(start + 1, i));
                        if (isleft) {
                            root.left = nd;
                            isleft = false;
                        } else {
                            root.right = nd;
                        }
                    }
                } else if (s.charAt(i) == '(') {
                    if (level == 0) {
                        start = i;
                    }
                    level++;
                }
                i++;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        System.out.println(Trees.toString(new ConstructBinaryTreeFromStringStack().str2tree("4(2(3)(1))(6(5))")));
    }
}

class ConstructBinaryTreeFromStringStack {
    // (: find out number then put to stack as we are going to its left tree
    // ): create a node for current top, or keep popping and linking a node whose value we know already
    public TreeNode str2tree(String s) {
        Deque<TreeNode> st = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                if (sb.length() > 0) {
                    int num = Integer.valueOf(sb.toString());
                    sb = new StringBuilder();
                    st.push(new TreeNode(num));
                }

            } else if (c == ')') {
                TreeNode cur = null;
                if (sb.length() > 0) {
                    int num = Integer.valueOf(sb.toString());
                    sb = new StringBuilder();
                    cur = new TreeNode(num);
                } else {
                    cur = st.pop();
                }
                if (st.peek().left == null) {
                    st.peek().left = cur;
                } else {
                    st.peek().right = cur;
                }
            } else {
                sb.append(c);
            }
        }
        // just nummbers no ()
        if (sb.length() > 0) {
            return new TreeNode(Integer.valueOf(sb.toString()));
        } else {
            return st.isEmpty() ? null : st.pop();
        }
    }
}