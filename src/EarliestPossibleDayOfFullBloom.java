import java.util.Arrays;

public class EarliestPossibleDayOfFullBloom {
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        int[][] a = new int[n][2];
        for (int i = 0; i < n; ++i) {
            a[i][0] = plantTime[i];
            a[i][1] = growTime[i];
        }
        Arrays.sort(a, (x, y) -> Integer.compare(y[1], x[1]));
        int res = 0;
        int day = 0;
        for (int i = 0; i < n; ++i) {
            //   System.out.println(day + " planting "+a[i][0]);
            day += a[i][0] - 1;
            int end = day + a[i][1] + 1;
            //  System.out.println("will end at "+end);
            res = Math.max(end, res);
            ++day;
        }
        return res;
    }
}
