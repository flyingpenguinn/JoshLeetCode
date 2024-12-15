import base.ArrayUtils;

public class CountBeautifulSplitsInSubarray {
    // use max prefix length for "this subarray is a prefix of another subarray" problems
    public int beautifulSplits(int[] a) {
        int n = a.length;
        int[][] f = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (a[i] == a[j]) {
                    f[i][j] = f[i + 1][j + 1] + 1;
                    //  System.out.println(i + "," + j + "=" + f[i][j]);
                }
            }
        }
        int res = 0;
        for (int i = 1; i < n - 1; ++i) {
            for (int j = i; j < n - 1; ++j) {
                int flen1 = f[0][i];
                int flen2 = f[i][j + 1];
                int len1 = i;
                int len2 = j - i + 1;
                int len3 = n - j;
                final boolean good1 = (len2 >= len1) && flen1 >= len1;
                final boolean good2 = (len3 >= len2) && flen2 >= len2;
                if (good1 || good2) {
                    ++res;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new CountBeautifulSplitsInSubarray().beautifulSplits(ArrayUtils.read1d("[2,3,2,2,1]")));
    }
}

class CountBeautifulSplitSubarrayRollingHash {
    // in the contest i got the rolling hash, but note we know len1 and len 2. we thus know which substring we need to check!
    private long mod = (long) (1e9 + 7);
    private long base = 101;
    private long[][] hm;
    private int Max = (int) 1e9;

    public int beautifulSplits(int[] a) {
        int n = a.length;
        hm = new long[n][n];
        for (int i = 0; i < n; ++i) {
            long hash = 0;
            for (int j = i; j < n; ++j) {
                hash = hash * base + (a[j] + 1);
                hash %= mod;
                hm[i][j] = hash;
            }
        }
        int res = 0;
        for (int i = 1; i < n - 1; ++i) {
            for (int j = i; j < n - 1; ++j) {
                long hash1 = hm[0][i - 1];
                int len1 = i;
                int len2 = j - i + 1;
                int len3 = n - j;
                long hash1comp = i + len1 - 1 >= n ? -1 : hm[i][i + len1 - 1];
                long hash2 = hm[i][j];
                long hash2comp = j + len2 >= n ? -1 : hm[j + 1][j + len2];
                boolean good1 = len1 <= len2 && hash1 == hash1comp;
                boolean good2 = len2 <= len3 && hash2 == hash2comp;
                if (good1 || good2) {
                    ++res;
                }

            }
        }
        return res;
    }
}
