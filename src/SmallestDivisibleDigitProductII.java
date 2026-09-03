import java.util.Arrays;

public class SmallestDivisibleDigitProductII {
    // use dp to figure out length. once we can fix and reason about length everything is good
    private int[] primes = {2, 3, 5, 7};
    private int Max = (int) 1e9;
    private int[] cnt2 = {0, 0, 1, 0, 2, 0, 1, 0, 3, 0};
    private int[] cnt3 = {0, 0, 0, 1, 0, 0, 1, 0, 0, 2};

    public String smallestNumber(String num, long t) {
        int[] cnt = new int[4];
        for (int i = 0; i < 4; ++i) {
            int cp = primes[i];
            if (t % cp != 0) {
                continue;
            }

            while (t % cp == 0) {
                t /= cp;
                ++cnt[i];
            }
        }
        if (t > 1) {
            return "-1";
        }

        int[][] dp = new int[cnt[0] + 1][cnt[1] + 1];
        for (int i = 0; i < dp.length; ++i) {
            Arrays.fill(dp[i], Max);
        }
        for (int i = 0; i <= cnt[0]; ++i) {
            for (int j = 0; j <= cnt[1]; ++j) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                }
                for (int k = 2; k <= 9; ++k) {
                    if (k == 5 || k == 7) {
                        continue;
                    }
                    int ni = Math.max(0, i - cnt2[k]);
                    int nj = Math.max(0, j - cnt3[k]);
                    dp[i][j] = Math.min(dp[i][j], dp[ni][nj] + 1);
                }
            }
        }
        int[] ncnt = new int[4];

        int firstZero = -1;
        for (int i = 0; i < num.length(); ++i) {
            int cind = num.charAt(i) - '0';
            if (cind == 0) {
                firstZero = i;
                break;
            }
            for (int j = 0; j < 4; ++j) {
                while (cind % primes[j] == 0) {
                    ++ncnt[j];
                    cind /= primes[j];
                }
            }
        }
        if (firstZero == -1 && cover(ncnt, cnt)) {
            return num;
        }
        int n = num.length();


        for (int i = n - 1; i >= 0; --i) {
            if (firstZero != -1 && firstZero < i) {
                continue;
            }

            int cind = num.charAt(i) - '0';
            update(ncnt, cind);
            int[] ccnt = Arrays.copyOf(cnt, 4);
            reduce(ccnt, ncnt);
            for (int d = cind + 1; d <= 9; ++d) {
                int needed = getlen(ccnt, d, dp);
                int remlen = n - 1 - i;
                if (needed > remlen) {
                    continue;
                }
                StringBuilder res = new StringBuilder(num.substring(0, i) + d);
                update(ccnt, d);
                build(res, remlen, ccnt, dp);
                return res.toString();
            }
        }

        // if here it means we cant find same len...
        StringBuilder res = new StringBuilder();
        int remlen = Math.max(
                num.length() + 1,
                dp[cnt[0]][cnt[1]] + cnt[2] + cnt[3]
        );
        build(res, remlen, cnt, dp);
        return res.toString();
    }

    private void reduce(int[] ccnt, int[] ncnt) {
        for (int i = 0; i < 4; ++i) {
            ccnt[i] -= ncnt[i];
            ccnt[i] = Math.max(0, ccnt[i]);
        }
    }

    private void build(StringBuilder res, int remlen, int[] ccnt, int[][] dp) {
        if (remlen == 0) {
            return;
        }
        for (int d = 1; d <= 9; ++d) {
            int crem = getlen(ccnt, d, dp);
            if (crem > remlen - 1) {
                continue;
            }
            res.append(d);
            update(ccnt, d);
            build(res, remlen - 1, ccnt, dp);
            break;
        }
    }

    private boolean cover(int[] ncnt, int[] cnt) {
        for (int i = 0; i < 4; ++i) {
            if (ncnt[i] < cnt[i]) {
                return false;
            }
        }
        return true;
    }

    private void update(int[] cnt, int digit) {
        if (digit == 5) {
            --cnt[2];
            cnt[2] = Math.max(cnt[2], 0);
        }
        if (digit == 7) {
            --cnt[3];
            cnt[3] = Math.max(cnt[3], 0);
        }
        int ia = cnt2[digit];
        int ib = cnt3[digit];
        cnt[0] -= ia;
        cnt[0] = Math.max(cnt[0], 0);
        cnt[1] -= ib;
        cnt[1] = Math.max(cnt[1], 0);
    }

    private int getlen(int[] cnt, int digit, int[][] dp) {
        int[] ncnt = Arrays.copyOf(cnt, 4);
        update(ncnt, digit);
        return dp[ncnt[0]][ncnt[1]] + ncnt[2] + ncnt[3];
    }
}
