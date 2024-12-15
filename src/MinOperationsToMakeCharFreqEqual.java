public class MinOperationsToMakeCharFreqEqual {
    // TODO: do it myself
    public int makeStringGood(String s) {
        int n = s.length();
        // Count occurrences of each letter
        int[] cnt = new int[27];
        for (char c : s.toCharArray()) {
            cnt[c - 'a' + 1]++;
        }

        // Function to calculate minimum operations for each frequency Y
        return calculateMinOperations(cnt, n);
    }

    private int calculateMinOperations(int[] cnt, int n) {
        final int INF = (int) 1e9;
        int ans = n;

        for (int Y = 1; Y <= n; Y++) {
            int[][] f = new int[27][2];
            for (int i = 0; i <= 26; i++) {
                f[i][0] = INF;
                f[i][1] = INF;
            }
            f[0][0] = 0;

            for (int i = 1; i <= 26; i++) {
                // Transition one
                f[i][0] = Math.min(f[i - 1][0], f[i - 1][1]) + cnt[i];
                // Transition two
                if (cnt[i] >= Y) {
                    f[i][1] = Math.min(f[i - 1][0], f[i - 1][1]) + cnt[i] - Y;
                } else {
                    int det = Y - cnt[i];
                    f[i][1] = Math.min(
                            // Transition three
                            f[i - 1][0] + det - Math.min(det, cnt[i - 1]),
                            // Transition four
                            f[i - 1][1] + det - Math.min(det, Math.max(0, cnt[i - 1] - Y))
                    );
                }
            }
            ans = Math.min(ans, Math.min(f[26][0], f[26][1]));
        }

        return ans;
    }
}


