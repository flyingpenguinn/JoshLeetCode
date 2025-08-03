import java.util.Arrays;
import java.util.TreeSet;

public class ThresholdMajorityQueue {
    // TODO Mo's algo

    private int[] a, comp, id2val, freq;
    private TreeSet<Integer>[] freqGroup;
    private int currMax;

    static class Q {
        int l, r, t, id, block;

        Q(int l, int r, int t, int id, int block) {
            this.l = l;
            this.r = r;
            this.t = t;
            this.id = id;
            this.block = block;
        }
    }

    public int[] subarrayMajority(int[] a, int[][] qs) {
        this.a = a;
        int n = a.length, Qn = qs.length;
        id2val = compress(a);
        int D = id2val.length;
        comp = new int[n];
        for (int i = 0; i < n; i++) {
            comp[i] = Arrays.binarySearch(id2val, a[i]);
        }

        int B = (int) Math.sqrt(n);
        Q[] queries = new Q[Qn];
        for (int i = 0; i < Qn; i++) {
            queries[i] = new Q(qs[i][0], qs[i][1], qs[i][2], i, qs[i][0] / B);
        }
        Arrays.sort(queries, (x, y) ->
                x.block != y.block
                        ? x.block - y.block
                        : ((x.block & 1) == 0 ? x.r - y.r : y.r - x.r)
        );

        freq = new int[D];
        freqGroup = new TreeSet[n + 1];
        for (int f = 0; f <= n; f++) {
            freqGroup[f] = new TreeSet<>();
        }
        currMax = 0;

        int cl = 0, cr = -1;
        int[] ans = new int[Qn];

        for (Q q : queries) {
            int L = q.l, R = q.r, T = q.t, idx = q.id;
            while (cl > L) add(--cl);
            while (cr < R) add(++cr);
            while (cl < L) remove(cl++);
            while (cr > R) remove(cr--);

            if (currMax < T) {
                ans[idx] = -1;
            } else {
                ans[idx] = id2val[freqGroup[currMax].first()];
            }
        }
        return ans;
    }

    private void add(int i) {
        int cid = comp[i];
        int f = freq[cid]++;
        freqGroup[f].remove(cid);
        freqGroup[f + 1].add(cid);
        if (f + 1 > currMax) {
            currMax = f + 1;
        }
    }

    private void remove(int i) {
        int cid = comp[i];
        int f = freq[cid]--;
        freqGroup[f].remove(cid);
        freqGroup[f - 1].add(cid);
        if (freqGroup[currMax].isEmpty()) {
            currMax--;
        }
    }

    private int[] compress(int[] a) {
        int n = a.length;
        int[] b = Arrays.copyOf(a, n);
        Arrays.sort(b);
        int D = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || b[i] != b[i - 1]) {
                b[D++] = b[i];
            }
        }
        return Arrays.copyOf(b, D);
    }


}
