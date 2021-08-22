public class NumberOfWaysToSeparateNumber {
    // use radix sort to decide relationship for the "nosmaller" part
    int mod = 1000000007;
    public void radixSort(int[] rank, int[] index, int[] res, int max, int len) {
        int[] sum = new int[max + 1];
        for (int i : index) {
            sum[i + len < rank.length ? rank[i + len] : 0]++;
        }
        for (int i = 1; i <= max; i++) {
            sum[i] += sum[i - 1];
        }
        for (int i = index.length - 1; i >= 0; i--) {
            res[--sum[index[i] + len < rank.length ? rank[index[i] + len] : 0]] = index[i];
        }
    }
    public int numberOfCombinations(String num) {
        char[] c = num.toCharArray();
        int[] dp = new int[c.length];
        int[] sum = new int[c.length + 1];
        int[] rank0 = new int[c.length];
        int[] rank1 = new int[c.length];
        int[] index = new int[c.length];
        int[] temp = new int[c.length];
        for (int i = 0; i < c.length; i++) {
            rank0[i] = c[i] - '0' + 1;
            index[i] = i;
        }
        sum[0] = 1;
        int max = 26;
        for (int k = 0; k < c.length; k++) {
            radixSort(rank0, index, temp, 26, k);
            radixSort(rank1, temp, index, max, 0);
            int cur0 = -1;
            int cur1 = -1;
            int t = 0;
            for (int i : index) {
                if (rank1[i] > cur1 || (i + k < rank0.length ? rank0[i + k] : 0) > cur0) {
                    cur1 = rank1[i];
                    cur0 = i + k < rank0.length ? rank0[i + k] : 0;
                    rank1[i] = ++t;
                } else {
                    rank1[i] = t;
                }
            }
            max = t;
            for (int i = 0; i + k < c.length; i++) {
                if (c[i] == '0') {
                    dp[i] = 0;
                    continue;
                }
                dp[i] = sum[i];
                if (i - k - 1 >= 0 && rank1[i - k - 1] > rank1[i]) {
                    dp[i] = ((dp[i] - dp[i - k - 1]) % mod + mod) % mod;
                }
                sum[i + k + 1] = (sum[i + k + 1] + dp[i]) % mod;
            }
        }
        return sum[c.length];
    }
}


class NumberOfWaysToSeparateNumbersNormalDp{
    // TLE if we dont do radix sort
    private long Mod = 1000000007;

    public int numberOfCombinations(String s) {
        int n = s.length();
        long[][] dp = new long[n + 1][n];
        long[][] sum = new long[n + 1][n];
        for (int i = n; i >= 0; i--) {
            for (int pre = 0; pre <= Math.max(0, i - 1); pre++) {
                if (i == n) {
                    dp[i][pre] = 1;
                } else if (s.charAt(i) != '0') {
                    int prelen = i - pre;
                    long res = 0;
                    if (prelen > 0 && i + prelen - 1 < n && noSmall(s, pre, i - 1, i, i + prelen - 1)) {
                        res += dp[i + prelen][i];
                        res %= Mod;
                    }
                    if (i + prelen + 1 <= n) {
                        long delta = sum[i + prelen + 1][i];     // from i+prelen+1 till n
                        res += delta;
                        res %= Mod;
                    }
                    dp[i][pre] = res;
                }
                sum[i][pre] = dp[i][pre] + (i + 1 > n ? 0 : sum[i + 1][pre]);
                sum[i][pre] %= Mod;
            }
        }
        return (int) dp[0][0];
    }

    private boolean noSmall(String s, int i, int j, int k, int l) {
        while (i <= j && k <= l) {
            if (s.charAt(k) > s.charAt(i)) {
                return true;
            } else if (s.charAt(k) < s.charAt(i)) {
                return false;
            }
            i++;
            k++;
        }
        return true;
    }
}
