public class CountFancyNumbersInARange {
    public long countFancy(long l, long r) {
        quickcheck = new boolean[150];
        for (int i = 0; i < 150; ++i) {
            quickcheck[i] = good(i);
        }
        return solve(r) - solve(l - 1);
    }

    private Long[][][][][][] dp;
    private boolean[] quickcheck;

    private long solve(long num) {
        char[] c = String.valueOf(num).toCharArray();
        int n = c.length;
        if (n == 1) {
            return num ;
        }

        long res = 0;
        for (int len = 1; len <= n; ++len) {
            dp = new Long[len][2][2][2][11][150];
            int smaller = len < n ? 1 : 0;
            long inc = dosolve(c, 0, 1, smaller, 0, 10, 0, len);
            long dec = dosolve(c, 0, 0, smaller, 0, 10, 0, len);
            res += inc;
            res += dec;
        }
        return res;
    }

    private boolean good(int num) {
        int previnc = 10;
        int prevdec = -1;
        boolean inc = true;
        boolean dec = true;
        while (num > 0) {
            int cdig = num % 10;
            if (cdig >= previnc) {
                inc = false;
            }
            if (cdig <= prevdec) {
                dec = false;
            }
            previnc = cdig;
            prevdec = cdig;
            num /= 10;
        }
        return inc || dec;
    }

    // i: 15, smaller: 2, prev: 0 to 10, sum: 135 max
    private long dosolve(char[] c, int i, int inc, int smaller, int notfancy, int prev, int sum, int len) {
        int n = c.length;
        long res = 0;

        if (i == len) {
            if (notfancy == 0) {
                if (len == 1) {
                    return inc == 1 ? 1 : 0;
                } else {
                    if (quickcheck[sum] && inc == 0) {
                        return 0;
                    } else {
                        return 1;
                    }
                }

            } else if (quickcheck[sum] && inc == 1) {
                return 1;
            } else {
                return 0;
            }
        }
        if (dp[i][inc][smaller][notfancy][prev][sum] != null) {
            return dp[i][inc][smaller][notfancy][prev][sum];
        }
        int digc = c[i] - '0';
        int start = i == 0 ? 1 : 0;
        for (int dig = start; dig <= 9; ++dig) {
            if (smaller == 0 && dig > digc) {
                break;
            }
            int nsmaller = smaller;
            if (dig < digc) {
                nsmaller = 1;
            }
            int newnotfancy = notfancy;
            if (prev < 10 && prev >= dig && inc == 1) {
                newnotfancy = 1;
            }
            if (prev < 10 && prev <= dig && inc == 0) {
                newnotfancy = 1;
            }
            int nprev = dig;
            if (prev == 10 && dig == 0) {
                nprev = prev;
            }
            long later = dosolve(c, i + 1, inc, nsmaller, newnotfancy, nprev, sum + dig, len);
            res += later;
        }
        dp[i][inc][smaller][notfancy][prev][sum] = res;
        return res;
    }
}
