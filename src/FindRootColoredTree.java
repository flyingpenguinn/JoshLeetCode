import java.util.*;

public class FindRootColoredTree {
    private List<Integer>[] t;
    private int[] dp;

    public int findColoredRoot(int[][] edges, String colors) {
        int en = edges.length;
        int n = en + 1;
        t = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            t[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            t[v1].add(v2);
            t[v2].add(v1);
        }
        dp = new int[n];
        dfs(0, -1, colors);
        int res = dfs2(0, -1, colors);
        return res;
    }

    private int dfs2(int i, int pa, String co) {
        int oldsize = pa == -1 ? t[i].size() : t[i].size() - 1;
        boolean goodnewsize = pa == -1 ? true : oldsize <= 1;
        if (dp[i] == 1 && goodnewsize) {
            return i;
        }
        char cc = co.charAt(i);
        for (int ne : t[i]) {
            if (ne == pa) {
                continue;
            }
            boolean hasbad = false;
            for (int ne2 : t[i]) {
                if (ne2 == ne || ne2 == pa) {
                    continue;
                }
                char cne2 = co.charAt(ne2);
                if (dp[ne2] == 2 || !good(cc, cne2)) {
                    hasbad = true;
                    break;
                }
            }
            if (hasbad) {
                continue;
            }
            char cne = co.charAt(ne);
            boolean pagood = good(cne, cc);
            if (pagood) {
                int cres = dfs2(ne, i, co);
                if (cres != -1) {
                    return cres;
                }
            }
        }
        return -1;
    }

    private int dfs(int i, int pa, String co) {
        int nesize = t[i].size();
        if (pa != -1) {
            nesize -= 1;
        }
        int res = nesize <= 2 ? 1 : 2;
        char cc = co.charAt(i);
        for (int ne : t[i]) {
            if (ne == pa) {
                continue;
            }
            char cne = co.charAt(ne);
            if (!good(cc, cne)) {
                res = 2;
            }
            int cr = dfs(ne, i, co);
            if (cr == 2) {
                res = 2;
            }
        }
        dp[i] = res;
        return res;
    }

    private boolean good(char c1, char c2) {
        return c1 == 'R' && c2 == 'B'
                || c1 == 'W' && c2 == 'R'
                || c1 == 'B' && c2 == 'W';

    }
}
