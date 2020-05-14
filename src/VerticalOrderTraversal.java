import base.TreeNode;

import java.util.*;

public class VerticalOrderTraversal {
    class Node {
        int y;
        int val;

        public Node(int y, int val) {
            this.y = y;
            this.val = val;
        }
    }

    Map<Integer, List<Node>> map = new HashMap<>();
    int low = Integer.MAX_VALUE;
    int high = 0;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> r = new ArrayList<>();
        dfs(root, 0, 0);
        for (int i = low; i <= high; i++) {
            List<Node> li = map.get(i);
            Collections.sort(li, (a, b) -> a.y != b.y ? Integer.compare(a.y, b.y) : Integer.compare(a.val, b.val));
            List<Integer> lint = new ArrayList<>();
            for (int j = 0; j < li.size(); j++) {
                lint.add(li.get(j).val);
            }
            r.add(lint);
        }
        return r;
    }

    private void dfs(TreeNode root, int x, int y) {
        if (root == null) {
            return;
        }
        List<Node> ext = map.getOrDefault(x, new ArrayList<>());
        ext.add(new Node(y, root.val));
        map.put(x, ext);
        low = Math.min(low, x);
        high = Math.max(high, x);
        dfs(root.left, x - 1, y + 1);
        dfs(root.right, x + 1, y + 1);
    }


}
