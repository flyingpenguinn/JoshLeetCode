public class LongestFibonacciSubarray {
    public int longestSubarray(int[] a) {
        int n = a.length;
        int i = 0;
        int res = 0;
        res = Math.min(n, 2);
        while (i + 2 < n) {
            int j = i;
            while (j + 2 < n && a[j] + a[j + 1] == a[j + 2]) {
                ++j;
            }
            // i... j-1+2
            int len = j + 1 - i + 1;
            res = Math.max(len, res);
            i = j + 1;
        }
        return res;
    }
}
