public class MinimumOperationsToMakeARotatedPalindromeII {
    // todo convolution/fft
    private static final int MOD = 998244353;
    private static final int ROOT = 3;

    public int minOperations(String s) {
        int n = s.length();

        int sz = 1;
        while (sz < 2 * n) {
            sz <<= 1;
        }

        long[] sum = new long[n];

        for (int c = 0; c < 26; ++c) {
            int[] a = new int[sz];
            int[] b = new int[sz];

            boolean has = false;

            for (int i = 0; i < n; ++i) {
                int v = s.charAt(i) - 'a';

                if (v == c) {
                    a[i] = 1;
                    has = true;
                }

                b[i] = dist(c, v);
            }

            if (!has) {
                continue;
            }

            ntt(a, false);
            ntt(b, false);

            for (int i = 0; i < sz; ++i) {
                a[i] = (int) ((long) a[i] * b[i] % MOD);
            }

            ntt(a, true);

            for (int i = 0; i < n; ++i) {
                sum[i] += a[i];

                if (i + n < 2 * n - 1) {
                    sum[i] += a[i + n];
                }
            }
        }

        long res = Long.MAX_VALUE;

        for (int r = 0; r < n; ++r) {
            int c = (2 * r + n - 1) % n;

            long change = sum[c] / 2;
            res = Math.min(res, r + change);
        }

        return (int) res;
    }

    private int dist(int a, int b) {
        int d = Math.abs(a - b);
        return Math.min(d, 26 - d);
    }

    private void ntt(int[] a, boolean invert) {
        int n = a.length;

        for (int i = 1, j = 0; i < n; ++i) {
            int bit = n >> 1;

            while ((j & bit) != 0) {
                j ^= bit;
                bit >>= 1;
            }

            j ^= bit;

            if (i < j) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }

        for (int len = 2; len <= n; len <<= 1) {
            int wlen = pow(ROOT, (MOD - 1) / len);

            if (invert) {
                wlen = pow(wlen, MOD - 2);
            }

            for (int i = 0; i < n; i += len) {
                long w = 1;
                int half = len >> 1;

                for (int j = 0; j < half; ++j) {
                    int u = a[i + j];
                    int v = (int) ((long) a[i + j + half] * w % MOD);

                    int x = u + v;
                    if (x >= MOD) {
                        x -= MOD;
                    }

                    int y = u - v;
                    if (y < 0) {
                        y += MOD;
                    }

                    a[i + j] = x;
                    a[i + j + half] = y;

                    w = w * wlen % MOD;
                }
            }
        }

        if (invert) {
            long invN = pow(n, MOD - 2);

            for (int i = 0; i < n; ++i) {
                a[i] = (int) (a[i] * invN % MOD);
            }
        }
    }

    private int pow(long a, long b) {
        long res = 1;

        while (b > 0) {
            if ((b & 1) != 0) {
                res = res * a % MOD;
            }

            a = a * a % MOD;
            b >>= 1;
        }

        return (int) res;
    }
}
