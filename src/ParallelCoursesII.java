import base.ArrayUtils;
import crap.Crap;

import java.io.IOException;
import java.util.*;

public class ParallelCoursesII {
    // wrong, but OJ accepted it, ditching my ranking to 1k
    // gave up LC contest after this problem
    int[] d;
    int[] ind;
    int[] outd;
    List<Integer>[] g;

    public int minNumberOfSemesters(int n, int[][] deps, int k) {
        d = new int[n + 1];
        ind = new int[n + 1];
        outd = new int[n + 1];
        g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] dep : deps) {
            g[dep[0]].add(dep[1]);
            ind[dep[1]]++;
        }
        for (int i = 1; i <= n; i++) {
            if (d[i] == 0) {
                dfs(i, g);
            }
        }
        List<Integer> q = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (ind[i] == 0) {
                q.add(i);
            }
        }
        int sem = 0;
        while (!q.isEmpty()) {
            // each iteration is a new semester
            sem++;
            Collections.sort(q, (x, y) -> Integer.compare(d[y], d[x]));
            int taken = Math.min(q.size(), k);
            for (int i = 0; i < taken; i++) {
                for (int next : g[q.get(i)]) {
                    ind[next]--;
                    if (ind[next] == 0) {
                        q.add(next);
                    }
                }
            }
            q = q.subList(taken, q.size());
        }
        return sem;
    }

    private int dfs(int i, List<Integer>[] g) {
        int maxd = 1;
        for (int next : g[i]) {
            int cur = 1 + dfs(next, g);
            maxd = Math.max(cur, maxd);
        }
        d[i] = maxd;
        return maxd;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new ParallelCoursesII().minNumberOfSemesters(6, ArrayUtils.read("[[2,5],[1,5],[3,5],[3,4],[3,6]]"), 2));
    }
}

class ParallelCoursesIIDp {

}
