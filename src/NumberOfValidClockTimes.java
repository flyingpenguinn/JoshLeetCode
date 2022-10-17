public class NumberOfValidClockTimes {
    private int res = 0;

    public int countTime(String time) {
        dfs(time.toCharArray(), 0);
        return res;
    }

    private void dfs(char[] t, int i) {
        if (i == 5) {

            int hour = (t[0] - '0') * 10 + (t[1] - '0');
            int minute = (t[3] - '0') * 10 + (t[4] - '0');
            if (hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) {
                ++res;
            }
            return;
        }
        if (i == 2) {
            dfs(t, i + 1);
        } else if (t[i] == '?') {
            for (char j = '0'; j <= '9'; ++j) {
                t[i] = j;
                dfs(t, i + 1);
                t[i] = '?';
            }
        } else {
            dfs(t, i + 1);
        }
    }
}
