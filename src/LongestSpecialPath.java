import base.ArrayUtils;

import java.util.*;

public class LongestSpecialPath {
    private Map<Integer, Map<Integer, Integer>> t = new HashMap<>();
    private int[] a;
    private int[] res = new int[2];

    public int[] longestSpecialPath(int[][] edges, int[] a) {
        int n = a.length;
        res[0] = 0;
        res[1] = n;
        int en = edges.length;
        this.a = a;
        for (int[] e : edges) {
            int v1 = e[0];
            int v2 = e[1];
            int len = e[2];
            t.computeIfAbsent(v1, p -> new HashMap<>()).put(v2, len);
            t.computeIfAbsent(v2, p -> new HashMap<>()).put(v1, len);
        }
        dfs(0, -1, 0, -1, new HashMap<>(), new ArrayList<>());
        return res;
    }

    private void dfs(int i, int p, int sum, int pbefore, Map<Integer, Deque<Integer>> m, List<Integer> sums) {
        int v = a[i];
        int level = sums.size();
        sums.add(sum);
        Deque<Integer> dq = m.get(v);
        int before = pbefore;
        if (dq != null) {
            int cbefore = dq.getLast();
            before = Math.max(cbefore, before);
        }

        int diff = sum - sums.get(before + 1);
        int nodes = level - before;
        if (diff > res[0]) {
            res[0] = diff;
            res[1] = nodes;
        } else if (res[0] == diff) {
            res[1] = Math.min(res[1], nodes);
        }

        m.computeIfAbsent(v, k -> new ArrayDeque<>()).offerLast(level);
        for (int ne : t.getOrDefault(i, new HashMap<>()).keySet()) {
            if (ne == p) {
                continue;
            }
            int len = t.get(i).get(ne);
            dfs(ne, i, sum + len, before, m, sums);
        }
        m.get(v).pollLast();
        if(m.get(v).isEmpty()){
            m.remove(v);
        }
        sums.remove(sums.size() - 1);
    }




    public static void main(String[] args) {
        System.out.println(Arrays.toString(new LongestSpecialPath().longestSpecialPath(ArrayUtils.read("[[1,0,2],[0,2,10]]"), ArrayUtils.read1d("[2,4,4]"))));
    }
}
