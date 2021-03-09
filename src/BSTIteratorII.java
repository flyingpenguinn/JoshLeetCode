import base.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BSTIteratorII {
    class BSTIterator {

        private Deque<TreeNode> st = new ArrayDeque<>();
        private List<TreeNode> list = new ArrayList<>();
        private int index = -1; // we are standing on -1 to start with

        public BSTIterator(TreeNode root) {
            pushleft(root);
        }

        private void pushleft(TreeNode p) {
            while (p != null) {
                st.push(p);
                p = p.left;
            }
        }

        public boolean hasNext() {
            if (index + 1 < list.size()) {
                return true;
            }
            return !st.isEmpty();
        }

        public int next() {
            if (index + 1 < list.size()) {
                return list.get(++index).val;
            } else {
                TreeNode p = st.pop();
                list.add(p);
                index++;
                pushleft(p.right);
                return p.val;
            }
        }

        public boolean hasPrev() {
            return index - 1 >= 0;
        }

        public int prev() {
            return list.get(--index).val;
        }
    }
}
