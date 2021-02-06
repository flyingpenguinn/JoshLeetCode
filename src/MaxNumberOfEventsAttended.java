import java.util.Arrays;
import java.util.TreeMap;

public class MaxNumberOfEventsAttended {
    private TreeMap<Integer, Integer> tm = new TreeMap<>();
    private Long[][] dp;

    public int maxValue(int[][] a, int k) {
        int n = a.length;
        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));

        for (int i = 0; i < n; i++) {
            tm.putIfAbsent(a[i][0], i);
        }
        //   System.out.println(tm);
        dp = new Long[n][k + 1];
        return (int) domax(a, 0, k, tm);
    }

    private long domax(int[][] a, int i, int k, TreeMap<Integer, Integer> tm) {
        int n = a.length;
        if (k == 0) {
            return 0;
        }
        if (i == n) {
            return 0;
        }
        if (dp[i][k] != null) {
            return dp[i][k];
        }
        long way1 = Long.MIN_VALUE;
        Integer later = tm.higherKey(a[i][1]);

        int laterindex = later == null ? n : tm.get(later);
        //    System.out.println(i+" "+laterindex);
        way1 = a[i][2] + domax(a, laterindex, k - 1, tm);

        long way2 = domax(a, i + 1, k, tm);
        dp[i][k] = Math.max(way1, way2);
        return dp[i][k];
    }
}
