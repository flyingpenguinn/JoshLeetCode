import java.util.Arrays;

public class MaxPointsAfterEnemyBattles {
    public long maximumPoints(int[] a, long power) {
        int n = a.length;
        Arrays.sort(a);
        int i = 0;
        int j = n - 1;
        long score = 0;
        long res = 0;
        while (i <= j) {
            if (power >= a[i]) {
                score += power / a[i];
                power %= a[i];
            } else if (score > 0) {
                power += a[j];
                --j;
            } else {
                break;
            }
            res = Math.max(res, score);
        }
        return res;

    }
}
