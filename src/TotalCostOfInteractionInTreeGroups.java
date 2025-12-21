import java.util.ArrayList;
import java.util.List;

public class TotalCostOfInteractionInTreeGroups {
    private long[][] downsumpath;
    private long[][] downnodes;
    private List<Integer>[] t;
    private int[] g;
    private long res = 0;
    private int NG = 0;

    public long interactionCosts(int n, int[][] edges, int[] group) {
        int maxg = 0;
        for (int gi : group) {
            maxg = Math.max(maxg, gi);
        }
        NG = maxg + 1;
        downsumpath = new long[n][NG];
        downnodes = new long[n][NG];
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
        g = group;
        dfs1(0, -1);
        long[] upnodes = new long[NG];
        long[] uppathsum = new long[NG];
        dfs2(0, -1, upnodes, uppathsum);
        return res / 2;
    }

    private void dfs2(int i, int parent, long[] upnodes, long[] uppathsum) {
        int cg = g[i];
        long cur = uppathsum[cg] + downsumpath[i][cg];
        res += cur;
        long[] newupnodes = new long[NG];
        long[] newuppath = new long[NG];
        for (int ne : t[i]) {
            if (ne == parent) {
                continue;
            }
            for (int j = 1; j < NG; ++j) {
                long othernodes = downnodes[i][j] - downnodes[ne][j];
                newupnodes[j] = upnodes[j] + othernodes;
                newuppath[j] = uppathsum[j] + upnodes[j];
                newuppath[j] += downsumpath[i][j] - downsumpath[ne][j] - downnodes[ne][j];
                newuppath[j] += othernodes;
            }
            dfs2(ne, i, newupnodes, newuppath);
        }

    }

    private void dfs1(int i, int parent) {
        int cg = g[i];
        for (int ne : t[i]) {
            if (ne == parent) {
                continue;
            }
            dfs1(ne, i);
            for (int j = 1; j < NG; ++j) {
                downnodes[i][j] += downnodes[ne][j];
                downsumpath[i][j] += downsumpath[ne][j] + downnodes[ne][j];
            }
        }
        ++downnodes[i][cg];
    }
}
