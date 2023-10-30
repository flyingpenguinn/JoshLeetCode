public class MinChangesToMakeBinaryStringBeautiful {
    public int minChanges(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int res = 0;
        for (int i = 1; i < n; i += 2) {
            if (c[i] != c[i - 1]) {
                ++res;
            }
        }
        return res;
    }
}
