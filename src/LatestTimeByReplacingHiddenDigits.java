public class LatestTimeByReplacingHiddenDigits {
    private int lastt = -1;
    private String last = null;

    public String maximumTime(String s) {
        dfs(new StringBuilder(s), 0);
        return last;
    }

    private void dfs(StringBuilder s, int i) {
        if (i == 2) {
            dfs(s, i + 1);
        }
        if (i == 5) {
            String hour = s.substring(0, 2);
            String min = s.substring(3, 5);
            int hint = Integer.valueOf(hour);
            int mint = Integer.valueOf(min);
            if (hint <= 23 && mint <= 59) {
                int mins = hint * 60 + mint;
                //  System.out.println(mins+" "+s.toString());
                if (mins > lastt) {
                    lastt = mins;
                    last = s.toString();
                }
            }
            return;
        }
        if (s.charAt(i) != '?') {
            dfs(s, i + 1);
        } else {
            for (char j = '0'; j <= '9'; j++) {
                s.setCharAt(i, j);
                dfs(s, i + 1);
                s.setCharAt(i, '?');
            }
        }
    }
}
