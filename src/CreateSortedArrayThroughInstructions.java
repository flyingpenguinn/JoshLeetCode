public class CreateSortedArrayThroughInstructions {
    // just typical fenwick tree...
    private long mod = 1000000007;

    public int createSortedArray(int[] a) {
        int maxnum = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            maxnum = Math.max(maxnum, a[i]);
        }
        long[] fen = new long[maxnum + 1];
        long[] cnt = new long[maxnum + 1];
        long res = 0;
        for (int i = 0; i < n; i++) {
            long smaller = query(fen, a[i] - 1);
            long eq = cnt[a[i]];
            long bigger = i - smaller - eq;
            //  System.out.println(i+" "+smaller+" "+eq+" "+bigger);
            long cost = Math.min(smaller, bigger);
            res += cost;
            res %= mod;
            add(fen, a[i]);
            cnt[a[i]]++;
        }
        return (int) res;
    }

    private long query(long[] fen, int i) {
        long res = 0;
        while (i > 0) {
            res += fen[i];
            i -= i & (-i);
        }
        return res;
    }

    private void add(long[] fen, int i) {
        while (i < fen.length) {
            fen[i]++;
            i += i & (-i);
        }
    }
}
