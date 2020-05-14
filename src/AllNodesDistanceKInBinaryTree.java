import base.TreeNode;
import base.Trees;


import java.util.*;

public class AllNodesDistanceKInBinaryTree {

    int targetHight = -1;
    Set<Integer> path = new HashSet<>();
    List<Integer> result = new ArrayList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        dfsHeight(root, target, 0);
        dfsFind(root, target, targetHight - k, k, 0, 0);
        return result;
    }

    // status = 0, ancestors
    // status ==2, subtree
    // status ==1, cousins
    private void dfsFind(TreeNode root, TreeNode target, int heightToFind, int k, int curHeight, int status) {
        if (root == null) {
            return;
        }
        if (heightToFind == curHeight) {
            result.add(root.val);
            if (status == 2) {
                return;
            }
        }
        if (root.val == target.val) {
            status = 2;
        }
        if (status == 0) {
            // we are on target's ancestors
            if (root.left != null) {
                if (path.contains(root.left.val)) {
                    dfsFind(root.left, target, targetHight - k, k, curHeight + 1, 0);
                } else {
                    dfsFind(root.left, target, k - (targetHight - curHeight)+curHeight, k, curHeight + 1, 1);
                }
            }
            if (root.right != null) {
                if (path.contains(root.right.val)) {
                    dfsFind(root.right, target, targetHight - k, k, curHeight + 1, 0);
                } else {
                    dfsFind(root.right, target, k - (targetHight - curHeight)+curHeight, k, curHeight + 1, 1);
                }
            }
        } else if (status == 1) {
            // cousins
            dfsFind(root.left, target, heightToFind, k, curHeight + 1, 1);
            dfsFind(root.right, target, heightToFind, k, curHeight + 1, 1);
        } else {
            // subtree
            dfsFind(root.left, target, targetHight + k, k, curHeight + 1, 2);
            dfsFind(root.right, target, targetHight + k, k, curHeight + 1, 2);
        }

    }

    private boolean dfsHeight(TreeNode root, TreeNode target, int i) {
        if (root.val == target.val) {
            path.add(root.val);
            targetHight = i;
            return true;
        }
        if (root.left != null) {
            boolean leftFound = dfsHeight(root.left, target, i + 1);
            if (leftFound) {
                path.add(root.val);
                return true;
            }
        }
        if (root.right != null) {
            boolean rightFound = dfsHeight(root.right, target, i + 1);
            if (rightFound) {
                path.add(root.val);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        TreeNode root = Trees.fromString("0,null,1,2,5,null,3,null,null,null,4");
        System.out.println(new AllNodesDistanceKInBinaryTree().distanceK(root, new TreeNode(2), 2));
    }

}


class DistanceKGraph {

    class Pair {
        int val;
        int len;

        public Pair(int val, int len) {
            this.val = val;
            this.len = len;
        }
    }

    Map<Integer, List<Integer>> graph = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        buildGraph(root, null, graph);
        Deque<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(target.val, 0));
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(target.val);
        while (!q.isEmpty()) {
            Pair top = q.poll();
            if (top.len > k) {
                break;
            }
            if (top.len == k) {
                result.add(top.val);
            }
            List<Integer> connected = graph.getOrDefault(top.val, new ArrayList<>());
            for (Integer c : connected) {
                if (!visited.contains(c)) {
                    visited.add(c);
                    q.offer(new Pair(c, top.len + 1));
                }
            }
        }
        return result;
    }

    private void buildGraph(TreeNode root, TreeNode parent, Map<Integer, List<Integer>> graph) {
        List<Integer> connected = graph.getOrDefault(root, new ArrayList<>());
        if (root == null) {
            return;
        }
        if (parent != null) {
            connected.add(parent.val);
        }
        if (root.left != null) {
            connected.add(root.left.val);
            buildGraph(root.left, root, graph);
        }
        if (root.right != null) {
            connected.add(root.right.val);
            buildGraph(root.right, root, graph);
        }
        graph.put(root.val, connected);
    }
}