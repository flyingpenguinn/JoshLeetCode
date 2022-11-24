public class NumberOfUniqueTriplesInArray {
    // On: sum(m[a]...m[m]) * m[n] * sum(m[o]...m[z])
    public int unequalTriplets(int[] a) {
        int n = a.length;
        int[] counter = new int[1001];
        for (int i = 0; i < n; ++i) {
            ++counter[a[i]];
        }
        int left = 0;
        int right = n;
        int res = 0;
        for (int i = 1; i <= 1000; ++i) {
            int cur = left * counter[i] * (right - counter[i]);
            res += cur;
            right -= counter[i];
            left += counter[i];
        }
        return res;
    }
}
