import java.util.Arrays;

public class BuildingBoxes {
    // binary search an answer if the base can support up to n. we lay the base as 1,2,3.... to get max coverage
    private static long[] dp;

    public int minimumBoxes(long n) {
        long l = 1;
        long u = 1700000;
        if (dp == null) {
            dp = new long[1700000];
        }
        Arrays.fill(dp, -1);
        while (l <= u) {
            long mid = l + (u - l) / 2;
            if (mid >= dp.length) {
                u = mid - 1;
                continue;
            }
            long calced = calc(mid);
            if (calced >= n) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return (int) l;

    }

    private long calc(long n) {

        //     System.out.println(n);
        if (n <= 2) {
            return n;
        }
        if (n == 3) {
            return 4;
        }
        int intn = (int) n;
        if (dp[intn] != -1) {
            return dp[intn];
        }
        // can do binary search here
        long i = 1;
        long sum = 0;
        long gained = 0;
        while (sum + i <= n) {
            sum += i;
            gained += (i - 1);
            i++;
        }
        long rem = n - sum;
        gained += (rem == 0) ? 0 : rem - 1; // remaining ones like 1,2,2-> 1+1 = 2 got covered
        //      System.out.println("direct gain from "+n+" = "+gained+" rem= "+rem);
        long res = n + calc(gained);
        //     System.out.println(n+" "+res);
        dp[intn] = res;
        return res;
    }
}
