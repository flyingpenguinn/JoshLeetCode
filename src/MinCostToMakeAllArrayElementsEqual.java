public class MinCostPrefixSuffixOperations {
    // Weekly 529 Q2. Enumerate the final value as an interior run [first, last]: pay first * v to overwrite the prefix
    // (free if the prefix is already all v) and (n - last - 1) * v for the suffix. Brute-force checked (Dijkstra over states, n <= 5).
    public long minCost(int[] a) {
        int n = a.length;
        int i = 0;
        while (i < n && a[i] == a[0]) {
            ++i;
        }
        if (i == n) {
            return 0;
        }
        int pos1 = i;
        i = n - 1;
        while (i >= 0 && a[i] == a[n - 1]) {
            --i;
        }
        int pos2 = i;
        long res = (long) 1e18;
        i = 1;
        while(i<n-1){
            int j = i;
            while(j<n && a[j]==a[i]){
                ++j;
            }
            int first = i;
            int last = j-1;
            long p1 = 0;
            if (i >= pos1) {
                p1 = (long) (first ) * a[i];
            }
            long p2 = 0;
            if (i <= pos2) {
                p2 = (long) (n - last - 1) * a[i];
            }
            long cur = p1 + p2;
            res = Math.min(res, cur);
            i = j;
        }
        return res;
    }
}
