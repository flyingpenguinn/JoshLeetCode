public class LongestOddEvenSubarray {
    public int longestAlternatingSubarray(int[] a, int t) {
        int n = a.length;
        int res = 0;
        int i = 0;
        while (i < n) {
            int d = 0;
            int j = i;
            while (j < n) {
                if (a[j] <= t && a[j] % 2 == d) {
                    d = d ^ 1;
                    int diff = j - i + 1;
                    res = Math.max(res, diff);
                    ++j;
                } else {
                    break;
                }

            }
            i = j == i ? i + 1 : j;
        }
        return res;
    }
}
