public class CountBinaryPalinNumbers {
    public int countBinaryPalindromes(long n) {
        String str = Long.toBinaryString(n);
        int len = str.length();
        if (len == 1) {
            return (int) (n + 1);
            // 0->1 , 1->2
        }
        int res = 0;
        for (int i = 1; i <= len - 1; ++i) {
            int half = (i + 1) / 2;
            // first digit fixed at 1
            int cur = 1 << (half - 1);
            res += cur;
        }
        long half = (len + 1) / 2;
        long base = 1 << (half - 1);
        final String halfstr = str.substring(0, (int) half);
        long diff = Long.parseLong(halfstr, 2) - base;
        res += diff;
        String made = makepalin(str);
        long madenum = Long.parseLong(made, 2);
        if (n >= madenum) {
            ++res;
        }
        return res + 1;
    }

    private String makepalin(String str) {
        char[] s = str.toCharArray();
        int n = s.length;
        for (int i = 0; i < n / 2; ++i) {
            s[n - 1 - i] = s[i];
        }
        return new String(s);
    }


    public static void main(String[] args) {
        System.out.println(new CountBinaryPalinNumbers().countBinaryPalindromes(10));
    }
}

class CountBinaryPalinDigialDp {
    public int countBinaryPalindromes(long n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 2;
        }
        char[] sn = Long.toBinaryString(n).toCharArray();
        int nn = sn.length;
        int res = 0;
        for (int len = 1; len < nn; ++len) {
            int cur = 0;
            if (len == 1) {
                cur = 2;

            } else {
                int half = len / 2;
                cur = (1 << (half - 1));
                if (len % 2 == 1) {
                    cur *= 2;
                }
            }
            res += cur;
        }
        int end = (nn + 1) / 2;
        dp = new Integer[end][2][2];

        int rem = solve(0, nn, 0, 0, sn);
        res += rem;
        return res;
    }

    private Integer[][][] dp;

    private int solve(int i, int nn, int sm, int bg2, char[] sn) {
        int end = (nn + 1) / 2;
        if (i == end) {
            if (sm == 1) {
                return 1;
            } else if (bg2 == 1) {
                return 0;
            } else {
                return 1;
            }
        }
        if (dp[i][sm][bg2] != null) {
            return dp[i][sm][bg2];
        }
        int way1 = 0;
        int way2 = 0;
        if (sm == 1) {
            way1 = solve(i + 1, nn, 1, bg2, sn);
            way2 = solve(i + 1, nn, 1, bg2, sn);
        } else {
            int mapi = nn - 1 - i;
            if (i == 0) {
                if (sn[nn - 1] == '0') {
                    way1 = solve(i + 1, nn, 0, 1, sn);
                } else {
                    way1 = solve(i + 1, nn, sm, bg2, sn);
                }
            } else if (sn[i] == '1' && sn[mapi] == '1') {
                way1 = solve(i + 1, nn, sm, bg2, sn);
                way2 = solve(i + 1, nn, 1, 0, sn);
            } else if (sn[i] == '1') {
                // but mapi is 0
                way1 = solve(i + 1, nn, sm, 1, sn);
                way2 = solve(i + 1, nn, 1, bg2, sn);
            } else {
                if (sn[mapi] == '1') {
                    way2 = solve(i + 1, nn, sm, 0, sn);
                } else {
                    way2 = solve(i + 1, nn, sm, bg2, sn);
                }

            }
        }
        int res = way1 + way2;
        dp[i][sm][bg2] = res;
        return res;
    }


    private int brute(int n) {
        int res = 0;
        for (int i = 0; i <= n; ++i) {
            char[] str = Long.toBinaryString(i).toCharArray();
            if (ispalin(str)) {
                //    System.out.println(i + " " + Long.toBinaryString(i));
                ++res;
            }
        }
        return res;
    }

    private boolean ispalin(char[] str) {
        int i = 0;
        int j = str.length - 1;
        while (i < j) {
            if (str[i++] != str[j--]) {
                return false;
            }
        }
        return true;
    }
}
