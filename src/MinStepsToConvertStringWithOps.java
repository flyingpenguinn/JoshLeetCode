public class MinStepsToConvertStringWithOps {
    // TODO
    public int minOperations(String w1, String w2) {
        int n = w1.length();
        char[] a = w1.toCharArray(), b = w2.toCharArray();
        int[][] cost = new int[n][n], dp = new int[n][n];

        // 1) build per‐substring cost[i][j]
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                // count direct mismatches
                int diff = 0;
                for (int k = i; k <= j; k++)
                    if (a[k] != b[k]) diff++;

                // count how many disjoint swaps fix two mismatches
                boolean[] used = new boolean[j - i + 1];
                int swp = 0;
                for (int p = i; p <= j; p++) {
                    if (used[p - i] || a[p] == b[p]) continue;
                    for (int q = p + 1; q <= j; q++) {
                        if (used[q - i] || a[q] == b[q]) continue;
                        if (a[p] == b[q] && a[q] == b[p]) {
                            swp++;
                            used[p - i] = used[q - i] = true;
                            break;
                        }
                    }
                }

                // best using only replaces or only swaps
                int best = Math.min(diff, diff - swp);

                // mismatches if you reverse once
                int revMis = 0;
                for (int k = i; k <= j; k++)
                    if (a[k] != b[i + j - k]) revMis++;
                best = Math.min(best, 1 + revMis);

                // build reversed array
                int L = j - i + 1;
                char[] rArr = new char[L];
                for (int k = 0; k < L; k++)
                    rArr[k] = a[j - k];

                // count swaps after reverse
                used = new boolean[L];
                int swpR = 0;
                for (int p = 0; p < L; p++) {
                    if (used[p] || rArr[p] == b[i + p]) continue;
                    for (int q = p + 1; q < L; q++) {
                        if (used[q] || rArr[q] == b[i + q]) continue;
                        if (rArr[p] == b[i + q] && rArr[q] == b[i + p]) {
                            swpR++;
                            used[p] = used[q] = true;
                            break;
                        }
                    }
                }
                // reverse + (some swaps) + replaces
                best = Math.min(best, 1 + (revMis - swpR));

                cost[i][j] = best;
            }
        }

        // 2) now partition DP over cost[i][j]
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1, r = cost[i][j];
                for (int k = i; k < j; k++)
                    r = Math.min(r, dp[i][k] + dp[k + 1][j]);
                dp[i][j] = r;
            }
        }

        return dp[0][n - 1];
    }
}
