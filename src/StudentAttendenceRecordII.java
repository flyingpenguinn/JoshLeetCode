import java.util.Arrays;

/*
LC#552
Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.

A student attendance record is a string that only contains the following three characters:

'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).

Example 1:
Input: n = 2
Output: 8
Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times.
Note: The value of n won't exceed 100,000.
 */
public class StudentAttendenceRecordII {
    // dp, but with rolling array to save space
    long Mod = 1000000007;

    public int checkRecord(int n) {
        int[][][] dp = new int[2][2][3];
        for (int i = n; i >= 0; i--) {
            int mi = i % 2;
            int mpi = (i + 1) % 2;
            for (int j = 0; j <= 1; j++) {
                for (int k = 0; k <= 2; k++) {
                    if (i == n) {
                        dp[mi][j][k] = 1;
                    } else {
                        int adda = j == 0 ? dp[mpi][j + 1][0] : 0;
                        int addl = k <= 1 ? dp[mpi][j][k + 1] : 0;
                        int addp = dp[mpi][j][0];
                        long cur = (0L + adda + addl + addp) % Mod;
                        dp[mi][j][k] = (int) cur;
                    }
                }
            }
        }
        return dp[0][0][0];
    }
}

class StudentAttendenceRecordTopdown {
    // "consecutive" ls we should use a counter too

    long[][][] dp;
    long Mod = 1000000007;

    public int checkRecord(int n) {
        dp = new long[n][2][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);

            }
        }
        return (int) doc(0, 0, 0, n);
    }

    // a==0, l == 1, p==2
    private long doc(int i, int as, int ls, int n) {
        if (i == n) {
            return 1L;
        }
        if (dp[i][as][ls] != -1) {
            return dp[i][as][ls];
        }
        long puta = 0;
        if (as == 0) {
            puta = doc(i + 1, as + 1, 0, n);
        }
        long putl = 0;
        if (ls <= 1) {
            putl = doc(i + 1, as, ls + 1, n);
        }
        long putp = doc(i + 1, as, 0, n);
        long r = (puta + putl + putp) % Mod;
        dp[i][as][ls] = r;
        return r;
    }
}