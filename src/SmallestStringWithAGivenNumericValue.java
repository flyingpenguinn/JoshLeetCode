public class SmallestStringWithAGivenNumericValue {
    // try to make later as big as possible to get a number at the front as small as possible
    public String getSmallestString(int n, int k) {
        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            int later = (n - 1 - i) * 26;
            int cur = Math.max(k - later, 1);
            res[i] = (char) ('a' + cur - 1);
            k -= cur;
        }
        return new String(res);
    }
}
