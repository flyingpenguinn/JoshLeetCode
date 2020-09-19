public class SumOfOddLengthSubarray {

    public int sumOddLengthSubarrays(int[] a) {
        int n = a.length;
        int[] sum = new int[n];
        sum[0] = a[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + a[i];
        }
        int res = 0;
        for (int len = 1; len <= n; len += 2) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                res += sum[j] - (i == 0 ? 0 : sum[i - 1]);
            }
        }
        return res;
    }
}
