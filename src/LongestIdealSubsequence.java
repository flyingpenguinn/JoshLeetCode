public class LongestIdealSubsequence {
    public int longestIdealString(String s, int k) {
        int n = s.length();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = s.charAt(i) - 'a';
        }
        int[] dp = new int[26];
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int cur = a[i];
            int cv = 0;
            for (int j = Math.max(cur - k, 0); j <= Math.min(cur + k, 25); ++j) {
                // System.out.println("cur= "+cur+" j="+j+" value= "+(dp[j]+1));
                cv = Math.max(cv, dp[j] + 1);
            }
            cv = Math.max(cv, 1);
            dp[cur] = cv;
            res = Math.max(res, dp[cur]);
        }
        return res;
    }
}
