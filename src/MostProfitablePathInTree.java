import java.util.*;

public class MostProfitablePathInTree {
    private Map<Integer, Set<Integer>> tree = new HashMap<>();
    private int[] parent;
    private List<Integer> bobs = new ArrayList<>();
    private Map<Integer, Integer> bopen = new HashMap<>();

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = edges.length + 1;
        parent = new int[n];
        for (int[] e : edges) {
            int start = e[0];
            int end = e[1];
            tree.computeIfAbsent(start, k -> new HashSet<>()).add(end);
            tree.computeIfAbsent(end, k -> new HashSet<>()).add(start);
        }
        dfs(0, -1);
        traceback(bob);
        //  System.out.println(bobs);
        //  System.out.println(bopen);
        return solve(0, 0, amount);
    }

    private void traceback(int bob) {
        if (bob == -1) {
            return;
        }
        bopen.put(bob, bobs.size());
        bobs.add(bob);
        traceback(parent[bob]);
    }

    private int solve(int cur, int i, int[] amount) {

        int nres = (int) (-1e9);
        for (int ne : tree.getOrDefault(cur, new HashSet<>())) {
            if (ne == parent[cur]) {
                continue;
            }
            int cres = solve(ne, i + 1, amount);
            //   System.out.println(ne+" "+cres);
            nres = Math.max(nres, cres);
        }
        int res = nres <= (-1e9) ? 0 : nres;
        if (bopen.containsKey(cur) && bopen.get(cur) < i) {
            // already opened
            res += 0;
        } else if (bopen.containsKey(cur) && bopen.get(cur) == i) {
            res += amount[cur] / 2;
        } else {
            res += amount[cur];
        }
        return res;
    }

    private void dfs(int i, int pa) {
        parent[i] = pa;
        for (int ne : tree.getOrDefault(i, new HashSet<>())) {
            if (ne == pa) {
                continue;
            }
            dfs(ne, i);
        }
    }
}
