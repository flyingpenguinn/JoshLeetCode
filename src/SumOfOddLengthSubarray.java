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

class SumOddLenSubarrayOn {
    // for each i, we can pick left len as 0,1,2...i numbers (0 up to i-1, i+1 choices
    // we can pick 0,1,2..n-i-1 numbers, n-i choices.
    // so there are in all x = (i+1) * (n-i) different subarrays per i. among them, (x+1) / 2 are odd
    // so each a[i] is repeated (x+1)/2 times
    public int sumOddLengthSubarrays(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += (((i + 1) * (n - i) + 1) / 2) * a[i];
        }
        return res;
    }
}