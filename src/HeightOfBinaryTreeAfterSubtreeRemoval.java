import base.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HeightOfBinaryTreeAfterSubtreeRemoval {
    private Map<Integer, PriorityQueue<int[]>> m = new HashMap<>();
    private Map<Integer, Integer> dm = new HashMap<>();
    public int[] treeQueries(TreeNode root, int[] queries) {
        // the contribution to depth is the depth itself + height.
        // we only need to keep top 2 of the heights for the same depth
        int allhigh = dfs(root, 0)-1;
        int[] res = new int[queries.length];
        for(int i=0; i<queries.length; ++i){
            int q = queries[i];
            int cd = dm.get(q);
            PriorityQueue<int[]> pq = m.get(cd);
            int[] top = pq.poll();
            if(pq.isEmpty()){
                res[i] = allhigh-top[0];
            }else if(pq.peek()[1] == q){
                // peek is the smaller one

                int diff = pq.peek()[0] - top[0];
                res[i] = allhigh - diff;


            }else{
                res[i] = allhigh;
            }
            pq.offer(top);
        }
        return res;
    }

    // return height, pass in depth
    private int dfs(TreeNode root, int d) {
        if (root == null) {
            return 0;
        }
        dm.put(root.val, d);
        int lh = dfs(root.left, d + 1);
        int rh = dfs(root.right, d + 1);
        int ch = Math.max(lh, rh) + 1;
        PriorityQueue<int[]> pq = m.getOrDefault(d, new PriorityQueue<>((x, y) -> Integer.compare(x[0], y[0])));
        pq.offer(new int[]{ch, root.val}); // height, value
        if (pq.size() > 2) {
            pq.poll();
        }
        m.put(d, pq);

        return ch;
    }
}
