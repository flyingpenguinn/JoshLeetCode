import java.util.Arrays;

public class LongestSubsequenceWithLimitedSum {
    public int[] answerQueries(int[] a, int[] queries) {
        Arrays.sort(a);
        int n = a.length;
        int[] sum = new int[n];
        for (int i = 0; i < n; ++i) {
            sum[i] = (i == 0 ? 0 : sum[i - 1]) + a[i];
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int q = queries[i];
            // last <=
            int pos = binary(sum, q);
            res[i] = pos + 1;
        }
        return res;
    }

    private int binary(int[] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}
