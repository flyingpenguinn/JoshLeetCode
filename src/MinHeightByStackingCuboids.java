import java.util.Arrays;

public class MinHeightByStackingCuboids {
    // almost last min ac in mock contest...
    // note it's asking for a subset, not a subsequence. the diff is in subsequence we can in subset we dont need to conform to an order!

    // two observations:
    //  sort by smallest edge, this gives us the sequence we need to iterate. now it becomes max increasing subsequence
    //  height must be the longest edge. because if height is not the longest edge:
    // 1. if it's the top one, then we simply rotate. the area will be smaller anyway
    // 2. otheerwise it's in the middle: a1, a3, a2 => b1, b2, b3. b is put on a
    // a2 >=b3>=b2
    // a3 >=a2>=b3
    // hence we can rotate and make the whole thing taller
    public int maxHeight(int[][] a) {
        // longest edge must be height. we fix 1st dimension <= 2nd. then we can sort by shortest dimension
        int n = a.length;
        for (int i = 0; i < n; i++) {
            Arrays.sort(a[i]);
        }
        // must put bigger first when there is dimenstions equal
        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0]) {
                return Integer.compare(y[0], x[0]);
            } else if (x[1] != y[1]) {
                return Integer.compare(y[1], x[1]);
            } else {
                return Integer.compare(y[2], x[2]);
            }
        });
        //  System.out.println(Arrays.deepToString(a));
        int[] dp = new int[n];
        // max stack height ending at i
        int max = 0;
        for (int i = 0; i < n; i++) {
            int cur = a[i][2];
            for (int j = 0; j < i; j++) {
                if (a[i][0] <= a[j][0] && a[i][1] <= a[j][1] && a[i][2] <= a[j][2]) {
                    cur = Math.max(cur, a[i][2] + dp[j]);
                }
            }
            dp[i] = cur;
            //   System.out.println(Arrays.toString(a[i])+" "+cur );
            max = Math.max(max, cur);
        }
        return max;
    }
}
