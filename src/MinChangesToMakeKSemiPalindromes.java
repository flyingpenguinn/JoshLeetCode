import java.util.*;

public class MinChangesToMakeKSemiPalindromes {
    // 2026-11-16 daily redo: 1 WA, then AC (2023-10-23: 5 WA before AC).
    // minc[i][j] = min over divisors d of len (d < len) of mismatches when each residue class mod d must be a palindrome.
    // Then partition DP: solve(i, k) = min over j of minc[i][j] + solve(j + 1, k - 1); every piece has length >= 2.
    private int[][] minc;

    public int minimumChanges(String s, int k) {
        char[] c = s.toCharArray();
        int n = c.length;
        minc = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int minchanges = IMP;
                int len = j - i + 1;
                for (int d = 1; d * d <= len; ++d) {
                    if (len % d != 0) {
                        continue;
                    }
                    int changes = getChanges(i, d, j, c);
                    minchanges = Math.min(minchanges, changes);
                    int other = len / d;
                    if (other != len) {
                        changes = getChanges(i, other, j, c);
                        minchanges = Math.min(minchanges, changes);
                    }

                }
                minc[i][j] = minchanges;
            }
        }
        dp = new int[n][k + 1];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], -1);
        }
        return solve(c, 0, k);
    }

    private static int getChanges(int i, int d, int j, char[] c) {
        int changes = 0;
        for (int start = i; start < i + d; ++start) {
            int diff = start - i;
            int end = j + 1 - d + diff;
            int p = start;
            int q = end;
            while (p < q) {
                if (c[p] != c[q]) {
                    ++changes;
                }
                p += d;
                q -= d;
            }
        }
        return changes;
    }

    private int IMP = (int) 1e9;
    private int[][] dp;

    private int solve(char[] c, int i, int k) {
        if (k < 0) {
            return IMP;
        }
        int n = c.length;

        if (i == n) {
            return k == 0 ? 0 : IMP;
        }
        if (dp[i][k] != -1) {
            return dp[i][k];
        }
        int res = IMP;
        for (int j = i + 1; j < n; ++j) {
            int cur = minc[i][j];
            int later = cur + solve(c, j + 1, k - 1);
            res = Math.min(res, later);
        }
        dp[i][k] = res;
        return res;
    }

    // brute: semi-palindrome cost straight from the definition, partitions by recursion
    private static int pieceCost(String t) {
        int len = t.length(), best = Integer.MAX_VALUE;
        for (int d = 1; d < len; ++d) {
            if (len % d != 0) {
                continue;
            }
            int cost = 0;
            for (int r = 0; r < d; ++r) {
                StringBuilder g = new StringBuilder();
                for (int x = r; x < len; x += d) {
                    g.append(t.charAt(x));
                }
                for (int a = 0, b = g.length() - 1; a < b; ++a, --b) {
                    if (g.charAt(a) != g.charAt(b)) {
                        ++cost;
                    }
                }
            }
            best = Math.min(best, cost);
        }
        return best;
    }

    private static int brute(String s, int k) {
        if (k == 0) {
            return s.isEmpty() ? 0 : (int) 1e9;
        }
        int best = (int) 1e9;
        for (int cut = 2; cut <= s.length(); ++cut) {
            int rest = brute(s.substring(cut), k - 1);
            if (rest < 1e9) {
                best = Math.min(best, pieceCost(s.substring(0, cut)) + rest);
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(new MinChangesToMakeKSemiPalindromes().minimumChanges("abcac", 2)); // 1
        System.out.println(new MinChangesToMakeKSemiPalindromes().minimumChanges("abcdef", 2)); // 2
        System.out.println(new MinChangesToMakeKSemiPalindromes().minimumChanges("aabbaa", 3)); // 0
        Random rd = new Random(2911);
        int bad = 0;
        for (int t = 0; t < 2000; ++t) {
            int n = 2 + rd.nextInt(9);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; ++i) {
                sb.append((char) ('a' + rd.nextInt(3)));
            }
            int k = 1 + rd.nextInt(n / 2);
            if (new MinChangesToMakeKSemiPalindromes().minimumChanges(sb.toString(), k) != brute(sb.toString(), k)) {
                ++bad;
            }
        }
        System.out.println("bad=" + bad);
    }
}
