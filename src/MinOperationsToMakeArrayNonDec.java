public class MinOperationsToMakeArrayNonDec {
    public long minOperations(int[] a) {
        int n = a.length;
        long max = a[0];
        long added = 0;
        long res = 0;
        for (int i = 1; i < n; ++i) {
            long nv = a[i] + added;
            if (nv >= max) {
                max = nv;
                continue;
            }
            long diff = max - nv;
            res += diff;
            added += diff;
            long nv2 = nv + diff;
            max = Math.max(max, nv2);
        }
        return res;

    }
}
