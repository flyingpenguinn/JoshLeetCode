import base.ArrayUtils;

import java.util.*;

public class CountZeroRequestServers {

    private int limit = 0;

    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        List[] a = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            a[i] = new ArrayList<>();
        }
        for (int q : queries) {
            limit = Math.max(limit, q);
        }
        for (int[] log : logs) {
            limit = Math.max(limit, log[1]);
        }
        limit += x+1;
        for (int[] log : logs) {
            int s = log[0] - 1;
            int t = log[1];
            a[s].add(t);
        }
        int[] map = new int[limit + 10];
        for (int i = 0; i < n; ++i) {
            List<Integer> list = a[i];
            Collections.sort(list);
            int last = 0;
            for (int j = 0; j < list.size(); ++j) {
                if (list.get(j) > last + 1) {
                    int start = last + 1;
                    int end = list.get(j) - 1;
                    ++map[start];
                    --map[end + 1];
                }
                last = Math.max(last, list.get(j) + x);
            }
            ++map[last + 1];
            --map[limit + 9];
        }
        int cur = 0;
        int[] lookup = new int[limit];
        for (int i = 0; i < limit; ++i) {
            cur += map[i];
            lookup[i] = cur;
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            res[i] = lookup[queries[i]];
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new CountZeroRequestServers().countServers(3, ArrayUtils.read("[[1,3],[2,6],[1,5]]"), 5, ArrayUtils.read1d("[10,11]"))));
    }
}
