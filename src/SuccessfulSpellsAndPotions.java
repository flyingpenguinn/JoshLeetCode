import java.util.Arrays;

public class SuccessfulSpellsAndPotions {
    public int[] successfulPairs(int[] a, int[] b, long s) {
        int n = a.length;
        Arrays.sort(b);
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            int pos = binary(b, a[i], s);
            res[i] = b.length - pos;
        }
        return res;
    }

    // first value >=t
    private int binary(int[] b, long a, long t) {
        int n = b.length;
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            long v = b[mid] * a;
            if (b[mid] * a >= t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
