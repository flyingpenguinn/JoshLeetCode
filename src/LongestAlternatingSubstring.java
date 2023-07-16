public class LongestAlternatingSubstring {
    public int alternatingSubarray(int[] a) {
        int n = a.length;
        int i = 0;
        int res = -1;
        while (i + 1 < n) {
            int j = i + 1;
            int should = 1;
            while (j < n && a[j] - a[j - 1] == should) {
                res = Math.max(res, j - i + 1);
                ++j;
                should = should == 1 ? -1 : 1;
            }
            if (j < n && a[j] - a[j - 1] == 1) {
                i = j - 1;
            } else {
                i = j;
            }
        }
        return res;
    }
}
