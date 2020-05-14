package base;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;


// use LinkedList to avoid "null not allowed in queue" error
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();
        int lastNonNull = 1;
        while (!queue.isEmpty()) {
            TreeNode nnode = queue.poll();
            String value = nodeToString(nnode);
            sb.append(value + ",");
            if (!"null".equals(value)) {
                lastNonNull = sb.length();
            }
            if (nnode != null) {
                queue.offer(nnode.left);
                queue.offer(nnode.right);
            }
        }
        // avoid the ending "," so -1
        return sb.substring(0, lastNonNull - 1);
    }

    // Decodes your encoded data to tree.
    // key insights:
    // 1. non last level is always fully populated so we know which father to connect to
    // 2. we skip null node don't put them to queue because their subtree is ignored in the next level's string
    // 3. if left is null but right is not we get subtrees as null, right
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] splitted = data.split(",");
        Deque<TreeNode> lastLevel = new ArrayDeque<TreeNode>();
        Deque<TreeNode> thisLevel = new ArrayDeque<TreeNode>();
        boolean leftVisited = false;
        TreeNode root = null;
        for (int i = 0; i < splitted.length; i++) {
            TreeNode node = stringToNode(splitted[i]);
            if (root == null) {
                root = node;
            }
            if (node != null) {
                // this is one key: we ignore null nodes in the queue so it won't have children
                // this also avoids confusion at line 91 the null check
                thisLevel.offer(node);
            }
            if (!lastLevel.isEmpty()) {
                TreeNode nextInLast = lastLevel.peek();
                if (!leftVisited) {
                    leftVisited = true;
                    nextInLast.left = node;
                } else {
                    leftVisited = false;
                    nextInLast.right = node;
                    lastLevel.poll();
                }
            }
            if (lastLevel.isEmpty()) {
                lastLevel.addAll(thisLevel);
                thisLevel.clear();
            }
        }
        return root;
    }

    private TreeNode stringToNode(String s) {
        return "null".equals(s) ? null : new TreeNode(Integer.valueOf(s));
    }

    private String nodeToString(TreeNode nnode) {
        return nnode == null ? "null" : String.valueOf(nnode.val);
    }
};

public class Trees {
    private static Codec codec = new Codec();

    public static TreeNode fromString(String str) {
        if (str.startsWith("[")) {
            str = str.substring(1, str.length() - 1);
        }

        return codec.deserialize(str);
    }

    public static String toString(TreeNode t) {
        return codec.serialize(t);
    }
}
