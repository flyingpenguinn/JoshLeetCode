import java.util.*;

public class DistributeElementsIntoTwoArraysII {
    // fenwick tree
    private Map<Integer, Integer> sm = new HashMap<>();

    public int[] resultArray(int[] a) {
        int n = a.length;
        int[] ca = Arrays.copyOf(a, n);
        Arrays.sort(ca);
        int seq = 1;
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && ca[i] == ca[j]) {
                ++j;
            }
            sm.put(ca[i], seq);
            ++seq;
            i = j;
        }
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        int[] bit1 = new int[seq + 1];
        int[] bit2 = new int[seq + 1];
        add(l1, bit1, a[0]);
        add(l2, bit2, a[1]);
        for (i = 2; i < n; ++i) {
            int vseq = sm.get(a[i]);
            int v1 = l1.size() - q(bit1, vseq);
            int v2 = l2.size() - q(bit2, vseq);
            if (v1 > v2) {
                add(l1, bit1, a[i]);
            } else if (v1 < v2) {
                add(l2, bit2, a[i]);
            } else if (l1.size() < l2.size()) {
                add(l1, bit1, a[i]);
            } else if (l1.size() > l2.size()) {
                add(l2, bit2, a[i]);
            } else {
                add(l1, bit1, a[i]);
            }
        }
        int[] res = new int[n];

        int ri = 0;
        for (i = 0; i < l1.size(); ++i) {
            res[ri++] = l1.get(i);
        }
        for (i = 0; i < l2.size(); ++i) {
            res[ri++] = l2.get(i);
        }
        return res;
    }

    private void add(List<Integer> l, int[] bit, int v) {
        l.add(v);
        int seq = sm.get(v);
        u(bit, seq, 1);
    }

    // update
    private void u(int[] b, int i, int d) {
        while (i < b.length) {
            b[i] += d;
            i += i & (-i);
        }
    }

    // query
    private int q(int[] b, int i) {
        int res = 0;
        while (i > 0) {
            res += b[i];
            i -= i & (-i);
        }
        return res;

    }
}
