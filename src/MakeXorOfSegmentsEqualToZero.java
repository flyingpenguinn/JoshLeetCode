public class MakeXorOfSegmentsEqualToZero {
    // we differentiate "in set" and "out set" cases because for outset we dont rely on dp[i][j] at all. we can take any previous j and map it to a needed number. if it's in set, we covered in inset already. if not, we know how many we need to change
    public int minChanges(int[] a, int k) {
        int n = a.length;
        // group numbers to i%k and count them
        Map<Integer, Integer>[] m = new HashMap[k];
        for (int i = 0; i < k; i++) {
            m[i] = new HashMap<>();
        }
        int[] count = new int[k];
        for (int i = 0; i < n; i++) {
            int mod = i % k;
            m[mod].put(a[i], m[mod].getOrDefault(a[i], 0) + 1);
            count[mod]++;
        }
        // cost to make 0...i-1 xor equal toj
        dp = new int[k + 1][1024];
        for (int i = 0; i <= k; i++) {
            Arrays.fill(dp[i], Max);
        }
        dp[0][0] = 0;
        //     dp[i][j] = min(dp[i-1][j2])+ changes from j2 to j
        int prevmin = 0;
        for (int i = 0; i < k; i++) {

            int mod = i % k;
            for (int j = 0; j < 1024; j++) {
                // cost to make 0...i xor to j. now we try out numbers
                Map<Integer, Integer> group = m[mod];
                for (int cand : group.keySet()) {
                    // we know where we are now. change to one of the numbers in set already
                    int curnums = group.get(cand);
                    int allnums = count[mod];
                    int change = allnums - curnums;
                    dp[i + 1][j ^ cand] = Math.min(dp[i + 1][j ^ cand], change + dp[i][j]);
                }

            }
            int prevmin2 = Max;
            for (int j = 0; j < 1024; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], count[mod] + prevmin);
                prevmin2 = Math.min(prevmin2, dp[i + 1][j]);
            }
            prevmin = prevmin2;
        }
        return dp[k][0];
    }

    private int Max = 10000000;
    private int[][] dp;
}
