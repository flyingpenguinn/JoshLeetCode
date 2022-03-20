public class MaxNumberOfSubsequences {
    public long maximumSubsequenceCount(String text, String pattern) {
        return Math.max(times("" + pattern.charAt(0) + text, pattern), times(text + pattern.charAt(1), pattern));
    }

    private long times(String s, String t) {
        long c0 = 0;
        long res = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == t.charAt(0)) {
                ++c0;
            }
            if (s.charAt(i) == t.charAt(1)) {
                res += (t.charAt(0) == t.charAt(1)) ? c0 - 1 : c0; // trap. in case t contains two same chars
            }
        }
        return res;
    }
}
