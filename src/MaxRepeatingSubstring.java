public class MaxRepeatingSubstring {
    public int maxRepeating(String s, String w) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int j = i;
            int cur = 0;
            while (j < s.length()) {
                if (s.startsWith(w, j)) {
                    cur++;
                    j += w.length();
                } else {
                    break;
                }
            }
            res = Math.max(res, cur);
        }
        return res;
    }
}
