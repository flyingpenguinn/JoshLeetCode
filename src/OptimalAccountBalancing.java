import base.ArrayUtils;

import java.util.*;

public class OptimalAccountBalancing {

    // for each i try to balance it with another different sign and clear this debt to zero
    Map<List<Integer>, Map<List<Integer>, Integer>> dp = new HashMap<>();

    public int minTransfers(int[][] ts) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int[] t : ts) {
            int out = t[0];
            int in = t[1];
            m.put(out, m.getOrDefault(out, 0) + t[2]);
            m.put(in, m.getOrDefault(in, 0) - t[2]);
        }
        int[] a = new int[m.keySet().size()];
        int i = 0;
        for (int k : m.keySet()) {
            a[i++] = m.get(k);
        }
        return dom(a, 0);
    }

    private int dom(int[] a, int i) {
        int n = a.length;
        while (i < n && a[i] == 0) {
            i++;
        }
        if (i == n) {
            return 0;
        }
        int oai = a[i];
        a[i] = 0;
        int min = 1000000;
        for (int j = i + 1; j < n; j++) {
            if (a[j] * oai < 0) {
                a[j] += oai;
                int cur = 1 + dom(a, i + 1);
                a[j] -= oai;
                min = Math.min(cur, min);
            }
        }
        a[i] = oai;
        return min;
    }


    public static void main(String[] args) {
        System.out.println(new OptimalAccountBalancing().minTransfers(ArrayUtils.read("[[0,1,1], [1,2,9], [2,0,3]]")));

    }
}

class OptimalAccountBalancingFaster {
    // similar idea but this runs faster on leetcode
    Map<List<Integer>, Map<List<Integer>, Integer>> dp = new HashMap<>();

    public int minTransfers(int[][] ts) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int[] t : ts) {
            int out = t[0];
            int in = t[1];
            m.put(out, m.getOrDefault(out, 0) + t[2]);
            m.put(in, m.getOrDefault(in, 0) - t[2]);
        }

        List<Integer> outer = new ArrayList<>();
        List<Integer> iner = new ArrayList<>();
        for (int k : m.keySet()) {
            int pd = m.get(k);
            if (pd > 0) {
                outer.add(pd);
            } else if (pd < 0) {
                iner.add(-pd);
            }
        }
        int r = 0;
        int i = 0;
        while (i < outer.size()) {
            boolean found = false;
            for (int j = 0; j < iner.size(); j++) {
                if (iner.get(j) == outer.get(i)) {
                    iner.remove(j);
                    found = true;
                    r++;
                    break;
                }
            }
            if (found) {
                outer.remove(i);
            } else {
                i++;
            }
        }
        Collections.sort(outer);
        Collections.sort(iner);
        return r + dos(outer, iner);
    }

    private int dos(List<Integer> outer, List<Integer> iner) {
        if (outer.isEmpty() && iner.isEmpty()) {
            return 0;
        }
        Map<List<Integer>, Integer> cm = dp.getOrDefault(outer, new HashMap<>());
        Integer ch = cm.get(iner);
        if (ch != null) {
            return ch;
        }
        int min = 1000000;
        if (outer.get(outer.size() - 1) > iner.get(iner.size() - 1)) {
            int cur = process(outer, iner);
            min = Math.min(min, cur);
        } else {
            int cur = process(iner, outer);
            min = Math.min(min, cur);
        }
        cm.put(iner, min);
        dp.put(outer, cm);
        return min;
    }

    int process(List<Integer> outer, List<Integer> iner) {
        int t = outer.get(outer.size() - 1);
        int min = 1000000;
        for (int j = 0; j < iner.size(); j++) {
            int iv = iner.get(j);
            List<Integer> no = new ArrayList<>(outer);
            no.remove(outer.size() - 1);
            if (t - iv > 0) {
                no.add(t - iv);
            }
            List<Integer> ni = new ArrayList<>(iner);
            ni.remove(j);
            Collections.sort(no);
            Collections.sort(ni);
            int later = dos(no, ni);
            min = Math.min(min, later + 1);
        }
        return min;
    }

}