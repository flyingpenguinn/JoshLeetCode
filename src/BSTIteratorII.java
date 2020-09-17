import base.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BSTIteratorII {
    class BSTIterator {
        // Three Stacks:
        // nst stores the next values and is the next() when ncache() is empty. pushing nodes here requires pushLeft
        // ncache stores previously fetched prev() values for later next() calls. Pushing values here doesn't require pushLeft
        // pst stores the previously fetched next() for later prev() calls.  prev() will be the second node from the top of this stack

        private Deque<TreeNode> nst = new ArrayDeque<>();
        private Deque<TreeNode> pst = new ArrayDeque<>();
        private Deque<TreeNode> ncache = new ArrayDeque<>();

        private void pushLeft(TreeNode p) {
            while (p != null) {
                nst.push(p);
                p = p.left;
            }
        }

        public BSTIterator(TreeNode root) {
            pushLeft(root);
        }

        public boolean hasNext() {
            return !ncache.isEmpty() || !nst.isEmpty();
        }

        public int next() {
            TreeNode next = null;
            if (!ncache.isEmpty()) {
                next = ncache.pop();
            } else {
                next = nst.pop();
                pushLeft(next.right);
            }
            pst.push(next);
            return next.val;
        }

        public boolean hasPrev() {
            return pst.size() > 1;
        }

        public int prev() {
            ncache.push(pst.pop());
            return pst.peek().val;
        }
    }
}

class BstIteratorIIList {
    class BSTIterator {
        private Deque<TreeNode> nst = new ArrayDeque<>();
        private List<TreeNode> list = new ArrayList<>();
        private int nextIndex = -1;

        // nextIndex is the cur element tht we are standing on
        // nextIndex- 1 is the prev value; next+1 is the next value, if exists, in the list
        // if nextIndex+1 is too big we expand the list by adding the top of the stack to it and move nextIndex

        private void pushLeft(TreeNode p) {
            while (p != null) {
                nst.push(p);
                p = p.left;
            }
        }

        public BSTIterator(TreeNode root) {
            pushLeft(root);
        }

        public boolean hasNext() {
            if (nextIndex + 1 >= 0 && nextIndex + 1 < list.size()) {
                return true;
            }
            return !nst.isEmpty();
        }

        public int next() {
            int rt = 0;
            if (nextIndex + 1 >= 0 && nextIndex + 1 < list.size()) {
                rt = list.get(nextIndex + 1).val;
            } else {
                // if cur node is the last we will have to expand
                TreeNode next = nst.pop();
                pushLeft(next.right);
                list.add(next);
                rt = next.val;
            }
            nextIndex++;
            return rt;
        }

        public boolean hasPrev() {
            return nextIndex - 1 >= 0 && nextIndex - 1 < list.size();
        }

        public int prev() {
            return list.get(--nextIndex).val;
        }
    }
}
