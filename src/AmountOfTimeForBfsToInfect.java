import base.TreeNode;

import java.util.*;

public class AmountOfTimeForBfsToInfect {
    private Map<Integer, Set<Integer>> g = new HashMap<>();

    public int amountOfTime(TreeNode root, int start) {
        dfs(root);
        //     System.out.println(g);
        Deque<Integer> q = new ArrayDeque<>();
        Map<Integer, Integer> dist = new HashMap<>();
        q.offer(start);
        dist.put(start, 0);
        int res = 0;
        while (!q.isEmpty()) {
            int i = q.poll();
            int cd = dist.get(i);
            res = Math.max(res, cd);
            for (int ne : g.getOrDefault(i, new HashSet<>())) {
                if (dist.containsKey(ne)) {
                    continue;
                }
                dist.put(ne, cd + 1);
                q.offer(ne);
            }
        }
        return res;
    }

    private void dfs(TreeNode n) {
        if (n.left != null) {
            g.computeIfAbsent(n.val, k -> new HashSet<>()).add(n.left.val);
            g.computeIfAbsent(n.left.val, k -> new HashSet<>()).add(n.val);
            dfs(n.left);
        }
        if (n.right != null) {
            g.computeIfAbsent(n.val, k -> new HashSet<>()).add(n.right.val);
            g.computeIfAbsent(n.right.val, k -> new HashSet<>()).add(n.val);
            dfs(n.right);
        }


    }
}
