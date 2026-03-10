public class ReverseKSubarrays {
    public int[] reverseSubarrays(int[] a, int k) {
        int n = a.length;
        int len = n / k;
        for (int i = 0; i + len - 1 < n; i += len) {
            int rj = i + len - 1;
            int ri = i;
            while (ri < rj) {
                int tmp = a[ri];
                a[ri] = a[rj];
                a[rj] = tmp;
                ++ri;
                --rj;
            }
        }
        return a;
    }
}
