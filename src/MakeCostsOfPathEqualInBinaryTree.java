import java.util.*;

public class MakeCostsOfPathEqualInBinaryTree {
    // compare with " Lowest Common Ancestor of Deepest Leaves"
    // note we can also do top down - enlarge each node so that the max path sum it affects = the max path sum itself. then go on to enlarge later nodes
    private int res = 0;

    public int minIncrements(int n, int[] cost) {
        dfs(0, n, cost);
        return res;
    }

    private int dfs(int i, int n, int[] cost) {
        if (i >= n) {
            return 0;
        }
        int left = dfs(2 * i + 1, n, cost);
        int right = dfs(2 * i + 2, n, cost);
        int delta = Math.abs(left - right);
        res += delta;
        return cost[i] + Math.max(left, right);
    }
}


class MakeCostOfPathEqual2 {
    // what if not perfect tree hence we cannot compare each level one by one because some level may not have nodes  at all
    private Map<Integer, Integer> pm = new HashMap<>();
    private int maxpath = 0;
    private int[] npathmax;
    private int res = 0;

    public int minIncrements(int n, int[] cost) {
        npathmax = new int[n];
        dfs(0, cost, n, 0);
        dfs2(0, cost, n);
        //    System.out.println(Arrays.toString(npathmax));

        dfs3(0, cost, n, 0);
        return res;
    }

    private int dfs2(int i, int[] cost, int n) {

        if (2 * i + 1 >= n) {
            // leaf
            npathmax[i] = pm.get(i);
            //         System.out.println(i+" "+npathmax[i]);
            return npathmax[i];
        }
        int lr = dfs2(i * 2 + 1, cost, n);
        int rr = dfs2(i * 2 + 2, cost, n);
        npathmax[i] = Math.max(lr, rr);
        return npathmax[i];
    }

    private void dfs3(int i, int[] cost, int n, int delta) {
        if (i >= n) {
            return;
        }
        int cpathmax = npathmax[i];
        cpathmax += delta;
        int newdelta = maxpath - cpathmax;
        res += newdelta;

        dfs3(2 * i + 1, cost, n, delta + newdelta);
        dfs3(2 * i + 2, cost, n, delta + newdelta);
    }

    private void dfs(int i, int[] cost, int n, int csum) {
        if (i >= n) {
            return;
        }
        csum += cost[i];
        if (2 * i + 1 >= n) {
            // leaf
            pm.put(i, csum);
            maxpath = Math.max(maxpath, csum);
            return;
        }
        dfs(i * 2 + 1, cost, n, csum);
        dfs(i * 2 + 2, cost, n, csum);
    }
}