import java.util.Arrays;

public class MinOperationsToMakeMedianK {
    public long minOperationsToMakeMedianK(int[] a, int k) {
        int n = a.length;
        Arrays.sort(a);
        return make(a, n / 2, k);

    }

    private long make(int[] a, int i, long k) {
        //  System.out.println(i);
        int n = a.length;
        if (a[i] == k) {
            return 0L;
        }
        long res = 0;
        if (a[i] < k) {
            for (int j = i; j < n; ++j) {
                if (a[j] < k) {
                    res += 0L + k - a[j];
                } else {
                    break;
                }
            }
        } else {
            for (int j = i; j >= 0; --j) {
                if (a[j] > k) {
                    res += 0L + a[j] - k;
                } else {
                    break;
                }
            }
        }
        return res;
    }
}
