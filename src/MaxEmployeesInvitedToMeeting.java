import base.ArrayUtils;

import java.util.*;

public class MaxEmployeesInvitedToMeeting {
    // either circle, or dags joined by a circle of 2 nodes linking each other. we can add those dags together
    private int[] st;
    private int maxcycle = 0;
    private Map<Integer, List<Integer>> rg = new HashMap<>(); // reverse graph. note the reverse graph
    private Set<Integer> pset = new HashSet<>();

    public int maximumInvitations(int[] a) {
        int n = a.length;
        st = new int[n];
        List<int[]> pairs = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (st[i] == 0) {
                dfs(a, i, 0, new HashMap<>());
            }
            if (a[a[i]] == i && i < a[i]) {
                pairs.add(new int[]{i, a[i]});
                pset.add(i);
                pset.add(a[i]);
            }
            rg.computeIfAbsent(a[i], k -> new ArrayList<>()).add(i);
        }
        int res = 0;
        for (int[] pair : pairs) {
            // we work on reverse graph which is a dag
            int cur = dfsrg(pair[0]) + dfsrg(pair[1]);
            res += cur;
        }
        return Math.max(res, maxcycle);
    }

    // rg is a dag
    private int dfsrg(int i) {
        int cur = 0;
        for (int j : rg.getOrDefault(i, new ArrayList<>())) {
            if (pset.contains(j)) {
                continue;
            }
            cur = Math.max(cur, dfsrg((j)));
        }
        return cur + 1;
    }

    private void dfs(int[] a, int i, int index, Map<Integer, Integer> pre) {
        int ne = a[i];
        if (st[ne] == 2) {
            return;
        }
        st[i] = 1;
        if (pre.containsKey(ne)) {
            int preindex = pre.get(ne);
            int clen = index - preindex + 1;
            maxcycle = Math.max(maxcycle, clen);
        } else {
            pre.put(i, index);
            dfs(a, ne, index + 1, pre);
            pre.remove(i);
        }
        st[i] = 2;
    }

    public static void main(String[] args) {
        System.out.println(new MaxEmployeesInvitedToMeeting().maximumInvitations(ArrayUtils.read1d("[1,0,3,2,5,6,7,4,9,8,11,10,11,12,10]")));
    }
}
