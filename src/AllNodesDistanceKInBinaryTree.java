import base.TreeNode;
import base.Trees;


import java.util.*;

public class AllNodesDistanceKInBinaryTree {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> r = new ArrayList<>();
        if(root==null || target==null || k<0){
            return r;
        }
        // tree non null, target must be in the tree
        Map<TreeNode, Set<TreeNode>> g = new HashMap<>();
        dfs(root, g);
        Deque<TreeNode> q = new ArrayDeque<>();
        q.offer(target);
        Map<TreeNode, Integer> steps = new HashMap<>();
        steps.put(target, 0);
        while(!q.isEmpty()){
            TreeNode top = q.poll();
            int step = steps.get(top);
            if(step==k){
                r.add(top.val);
                continue;
            }
            for(TreeNode ne: g.getOrDefault(top, new HashSet<>())){
                if(!steps.containsKey(ne)){
                    steps.put(ne, step+1);
                    q.offer(ne);
                }
            }
        }
        return r;
    }

    // n non null
    private void dfs(TreeNode n, Map<TreeNode, Set<TreeNode>> g){
        if(n.left!= null){
            g.computeIfAbsent(n, k-> new HashSet<>()).add(n.left);
            g.computeIfAbsent(n.left, k-> new HashSet<>()).add(n);
            dfs(n.left, g);
        }
        if(n.right != null){
            g.computeIfAbsent(n, k-> new HashSet<>()).add(n.right);
            g.computeIfAbsent(n.right, k-> new HashSet<>()).add(n);
            dfs(n.right, g);
        }
    }
}