import base.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class FindElementsInContaminatedTree {
    class FindElements {
        TreeNode cr = null;
        Set<Integer> values = new HashSet<>();

        public FindElements(TreeNode root) {
            cr = root;
            dfs(root, 0);
        }

        private void dfs(TreeNode node, int val) {
            if (node == null) {
                return;
            }
            node.val = val;
            values.add(val);
            dfs(node.left, val * 2 + 1);
            dfs(node.right, val * 2 + 2);
        }

        public boolean find(int target) {
            return values.contains(target);
        }

    }
}

