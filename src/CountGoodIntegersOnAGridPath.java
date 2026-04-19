import java.util.Arrays;

public class CountGoodIntegersOnAGridPath {
    class Solution {
        public long countGoodIntegersOnPath(long l, long r, String s) {
            return solve(r, s) - solve(l - 1, s);
        }

        private long solve(long num, String ins) {
            int n = ins.length();
            String str = String.valueOf(num);
            while (str.length() < 16) {
                str = "0" + str;
            }
            char[] c = str.toCharArray();
            for (int i = 0; i < 16; ++i) {
                for (int j = 0; j < 7; ++j) {
                    for (int k = 0; k < 16; ++k) {
                        for (int p = 0; p < 2; ++p) {
                            Arrays.fill(dp[i][j][k][p], -1);
                        }
                    }
                }
            }
            return dpsolve(c, 0, ins, 0, 0, 0, 0);
        }


        private static long[][][][][] dp = new long[16][7][16][2][10];

        private long dpsolve(char[] c, int i, String ins, int j, int next, int sm, int pre) {
            int n = c.length;
            if (i == n) {
                return 1;
            }
            if (dp[i][j][next][sm][pre] != -1) {
                return dp[i][j][next][sm][pre];
            }
            long res = 0;
            int cdig = c[i] - '0';
            for (int dig = 0; dig <= 9; ++dig) {
                if (sm == 0 && dig > cdig) {
                    break;
                }
                if (i == next) {

                    if (dig < pre) {
                        continue;
                    }
                }
                int nsm = dig < cdig ? 1 : sm;
                int nnext = next;
                int npre = pre;
                int nj = j;
                if (i == next) {
                    npre = dig;
                    nj = j + 1;
                    if (j < ins.length() && ins.charAt(j) == 'D') {
                        nnext = i + 4;
                    } else {
                        nnext = i + 1;
                    }

                }
                long cur = dpsolve(c, i + 1, ins, nj, nnext, nsm, npre);
                res += cur;
            }
            dp[i][j][next][sm][pre] = res;
            return res;
        }

        private long brute(long l, long r, String ins) {
            long res = 0;
            for (long i = l; i <= r; ++i) {
                String str = String.valueOf(i);
                while (str.length() < 16) {
                    str = "0" + str;
                }
                int[][] grid = new int[4][4];
                for (int p = 0; p < str.length(); ++p) {
                    grid[p / 4][p % 4] = str.charAt(p) - '0';
                }
                int ci = 0;
                int cj = 0;
                int ik = 0;
                int prev = 0;
                boolean bad = false;
                while (ik < ins.length()) {
                    if (ins.charAt(ik) == 'D') {
                        ++ci;
                    } else {
                        ++cj;
                    }
                    int num = grid[ci][cj];
                    if (num < prev) {
                        bad = true;
                        break;
                    }
                    prev = num;
                    ++ik;
                }
                if (!bad) {
                    ++res;
                }
            }
            return res;
        }
    }
}
