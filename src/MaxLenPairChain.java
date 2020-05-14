import java.util.Arrays;

public class MaxLenPairChain {
    public int findLongestChain(int[][] p) {
        // Greedy, select max num of interval, sort by end time
        if (p.length == 0) {
            return 0;
        }
        Arrays.sort(p, (a, b) -> a[1] != b[1] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        int curend = p[0][1];
        int len = 1;
        for (int i = 1; i < p.length; i++) {
            if (p[i][0] > curend) {
                len++;
                curend = p[i][1];
            }
        }
        return len;
    }
}
