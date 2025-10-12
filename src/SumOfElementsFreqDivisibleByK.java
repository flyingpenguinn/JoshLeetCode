public class SumOfElementsFreqDivisibleByK {
    public int sumDivisibleByK(int[] a, int k) {
        int n = a.length;
        int[] cnt = new int[200];
        for (int i = 0; i < n; ++i) {
            ++cnt[a[i]];
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (cnt[a[i]] % k == 0) {
                res += a[i];
            }
        }
        return res;
    }
}
