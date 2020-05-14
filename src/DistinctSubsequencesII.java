import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class DistinctSubsequencesII {
    private static long MOD = 1000000007;

    private int[] dp;

    public int distinctSubseqII(String s) {
        int n = s.length();
        dp = new int[n];
        return dodp(0, s) - 1;
    }

    private int dodp(int i, String s) {
        if (i == s.length()) {
            return 1;
        }
        if (dp[i] != 0) {
            return dp[i];
        }
        // for abc, count bc then abc is 2*count of bc
        // similar to subset generation: with and without
        int without = dodp(i + 1, s);
        int with = dodp(i + 1, s);
        for (int j = i + 1; j < s.length(); j++) {
            if (s.charAt(j) == s.charAt(i)) {
                // note we counted empty set in. so -1 is not needed here because it's deducted due to the empty set
                // for aa, a+empty = a. so when we -dodp we deducted empty set, i.e. the duplicated a
                with -= dodp(j + 1, s);
                break;
            }
        }
        dp[i] = (int) ((without + with + MOD) % MOD);
        return dp[i];
    }


    public static void main(String[] args) {
        System.out.println(new DistinctSubsequencesII().distinctSubseqII("aaa"));
    }
}
