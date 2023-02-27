public class FindDivisibilityArrayOfString {
    private long mod = (long) (1e9 + 7);

    public int[] divisibilityArray(String word, int m) {
        int n = word.length();
        int[] res = new int[n];
        long cur = 0;
        for (int i = 0; i < n; ++i) {
            int cind = word.charAt(i) - '0';
            cur = cur * 10 + cind;
            cur %= m;
            if (cur == 0) {
                res[i] = 1;
            }
        }
        return res;
    }
}
