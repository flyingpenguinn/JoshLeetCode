import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinishTimeOfTasksII {
    private List<Integer>[] t;
    private long[] max1;
    private long[] max2;
    private long[] min1;
    private long[] min2;
    private long[] finish;

    private long Max = (long) 1e17;
    private long Min = -Max;
    private long res = Max;

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
        max1 = new long[n];
        Arrays.fill(max1, Min);
        max2 = new long[n];
        Arrays.fill(max2, Min);
        min1 = new long[n];
        Arrays.fill(min1, Max);
        min2 = new long[n];
        Arrays.fill(min2, Max);
        finish = new long[n];
        dfs1(0, -1, baseTime);

        dfs2(0, 0, -1, baseTime);
        return res;
    }

    private void dfs2(int i, long upper, int p, int[] baseTime) {
        long cur = 0;
        if (p != -1) {
            if (upper > max1[i]) {
                max2[i] = max1[i];
                max1[i] = upper;
            } else if (upper > max2[i]) {
                max2[i] = upper;
            }

            if (upper < min1[i]) {
                min2[i] = min1[i];
                min1[i] = upper;
            } else if (upper < min2[i]) {
                min2[i] = upper;
            }
            cur = (2 * max1[i] - min1[i] + baseTime[i]);
        } else {
            cur = t[i].isEmpty() ? baseTime[i] : (2 * max1[i] - min1[i] + baseTime[i]);
        }
        res = Math.min(res, cur);
        for (int ne : t[i]) {
            if (ne == p) {
                continue;
            }
            if (t[i].size() == 1) {
                dfs2(ne, baseTime[i], i, baseTime);
            } else {
                long maxi = max1[i];
                if (finish[ne] == max1[i]) {
                    maxi = max2[i];
                }
                long mini = min1[i];
                if (finish[ne] == min1[i]) {
                    mini = min2[i];
                }
                long newi = 2 * maxi - mini + baseTime[i];
                dfs2(ne, newi, i, baseTime);
            }
        }
    }


    private long dfs1(int i, int p, int[] baseTime) {

        for (int ne : t[i]) {
            if (ne == p) {
                continue;
            }
            long nes = dfs1(ne, i, baseTime);
            if (nes > max1[i]) {
                max2[i] = max1[i];
                max1[i] = nes;
            } else if (nes > max2[i]) {
                max2[i] = nes;
            }

            if (nes < min1[i]) {
                min2[i] = min1[i];
                min1[i] = nes;
            } else if (nes < min2[i]) {
                min2[i] = nes;
            }
        }

        long ownfinish = 0;
        if (min1[i] < Max) {
            long ownduration = max1[i] - min1[i] + baseTime[i];
            ownfinish = ownduration + max1[i];
        } else {
            ownfinish = baseTime[i];
        }
        finish[i] = ownfinish;

        return ownfinish;
    }

    static void main() {
        System.out.println(new FinishTimeOfTasksII().finishTime(3, ArrayUtils.read("[[0,2],[0,1]]"), ArrayUtils.read1d("[2,5,12]")));
        System.out.println(new FinishTimeOfTasksII().finishTime(1, ArrayUtils.read("[]"), ArrayUtils.read1d("[1]")));
        System.out.println(new FinishTimeOfTasksII().finishTime(3, ArrayUtils.read("[[0,1],[1,2]]"), ArrayUtils.read1d("[9,1,5]")));
        System.out.println(new FinishTimeOfTasksII().finishTime(3, ArrayUtils.read("[[0,1],[0,2]]"), ArrayUtils.read1d("[4,7,6]")));
        System.out.println(new FinishTimeOfTasksII().finishTime(4, ArrayUtils.read("[[0,1],[0,2],[2,3]]"), ArrayUtils.read1d("[5,8,2,1]")));
    }
}

