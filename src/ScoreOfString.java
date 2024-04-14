public class ScoreOfString {
    public int scoreOfString(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 1; i < n; ++i) {
            int cur = s.charAt(i) - 'a';
            int prev = s.charAt(i - 1) - 'a';
            res += Math.abs(cur - prev);
        }
        return res;
    }
}
