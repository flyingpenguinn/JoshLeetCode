import java.util.Arrays;

public class CountDaysWithoutMeetings {
    public int countDays(int days, int[][] a) {
        int n = a.length;
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));
        int lastend = 0;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i][0] > days) {
                break;
            }
            if (a[i][0] > lastend) {
                res += a[i][0] - lastend - 1;
                lastend = a[i][1];
            } else {
                lastend = Math.max(lastend, a[i][1]);
            }
        }
        res += days - lastend;
        return res;
    }
}
