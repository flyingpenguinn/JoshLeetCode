import java.util.ArrayList;
import java.util.List;

public class FinishTimeOfTaskI {
    private List<Integer>[] t;

    public long finishTime(int n, int[][] edges, int[] baseTime) {
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
        long res0 = dfs(0, -1, baseTime);
        return res0;
    }

    private long Max = (long) 1e17;
    private long Min = -Max;

    private long dfs(int i, int p, int[] baseTime) {

        long minc = Max;
        long maxc = Min;
        for (int ne : t[i]) {
            if (ne == p) {
                continue;
            }
            long nes = dfs(ne, i, baseTime);
            minc = Math.min(minc, nes);
            maxc = Math.max(maxc, nes);
        }

        long ownfinish = 0;
        if (minc < Max) {
            long ownduration = maxc - minc + baseTime[i];
            ownfinish = ownduration + maxc;
        } else {
            ownfinish = baseTime[i];
        }

        return ownfinish;
    }
}
