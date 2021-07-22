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
        if(s.isEmpty()){
            return null;
        }
        Deque<TreeNode> st = new ArrayDeque<>();
        st.push(new TreeNode(0));
        int n = s.length();
        int sign = 1;
        for(int i=0; i<n;i++){
            char c = s.charAt(i);
            if(c=='('){
                st.push(new TreeNode(0));
                sign = 1;
            }else if(c==')'){
                TreeNode ct = st.pop();
                TreeNode nt = st.peek();
                if(nt.left == null){
                    nt.left = ct;
                }else{
                    nt.right = ct;
                }
            }else if(c=='-'){
                sign = -1;
            }else{
                int cn = c-'0';
                st.peek().val = st.peek().val*10+sign*cn;
            }
        }
        return st.pop();
    }
}