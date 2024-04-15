public class LastTimeYouCanObtainAfterReplacingChars {
    private String max = "";
    private int maxsec = -1;

    public String findLatestTime(String s) {
        dfs(s, 0, "");
        return max;
    }

    private void dfs(String s, int i, String cur) {
        int n = s.length();
        if (i == n) {
            //   System.out.println(cur);
            int mins = Integer.valueOf(cur.substring(0, 2));
            int secs = Integer.valueOf(cur.substring(3, 5));

            if (mins > 11) {
                return;
            }
            if (secs > 59) {
                return;
            }
            int cursec = mins * 60 + secs;
            if (cursec > maxsec) {
                maxsec = cursec;
                max = cur;
            }
            return;
        }
        if (s.charAt(i) != '?') {
            dfs(s, i + 1, cur + s.charAt(i));
        } else {
            for (int j = 0; j <= 9; ++j) {
                dfs(s, i + 1, cur + j);
            }
        }
    }
}
