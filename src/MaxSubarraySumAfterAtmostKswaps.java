import java.util.Arrays;

public class MaxSubarraySumAfterAtmostKswaps {
    // TODO
    private static class Fenwick {
        private final int n;
        private final int[] vals;
        private final int[] cnt;
        private final long[] sum;
        private final int[] allCnt;
        private final long[] allSum;

        Fenwick(int[] vals, int[] freq) {
            n = vals.length;
            this.vals = vals;
            cnt = new int[n + 1];
            sum = new long[n + 1];
            allCnt = new int[n + 1];
            allSum = new long[n + 1];

            for (int i = 1; i <= n; ++i) {
                allCnt[i] = freq[i - 1];
                allSum[i] = (long) freq[i - 1] * vals[i - 1];
            }

            for (int i = 1; i <= n; ++i) {
                int j = i + (i & -i);
                if (j <= n) {
                    allCnt[j] += allCnt[i];
                    allSum[j] += allSum[i];
                }
            }
        }

        private void clear() {
            Arrays.fill(cnt, 0);
            Arrays.fill(sum, 0);
        }

        private void add(int p, int x) {
            for (int i = p + 1; i <= n; i += i & -i) {
                ++cnt[i];
                sum[i] += x;
            }
        }

        private int countPrefix(int p) {
            int res = 0;

            for (int i = p; i > 0; i -= i & -i) {
                res += cnt[i];
            }

            return res;
        }

        private long sumSmallestInside(int k) {
            return sumSmallest(k, false);
        }

        private long sumSmallestOutside(int k) {
            return sumSmallest(k, true);
        }

        private long sumSmallest(int k, boolean outside) {
            if (k == 0) {
                return 0;
            }

            int p = 0;
            int c = 0;
            long s = 0;

            for (int step = Integer.highestOneBit(n);
                 step > 0;
                 step >>= 1) {
                int np = p + step;

                if (np > n) {
                    continue;
                }

                int nc;
                long ns;

                if (outside) {
                    nc = allCnt[np] - cnt[np];
                    ns = allSum[np] - sum[np];
                } else {
                    nc = cnt[np];
                    ns = sum[np];
                }

                if (c + nc < k) {
                    p = np;
                    c += nc;
                    s += ns;
                }
            }

            return s + (long) (k - c) * vals[p];
        }
    }

    public long maxSum(int[] a, int k) {
        int n = a.length;

        int[] b = a.clone();
        Arrays.sort(b);

        int v = 0;
        for (int x : b) {
            if (v == 0 || b[v - 1] != x) {
                b[v++] = x;
            }
        }

        int[] vals = Arrays.copyOf(b, v);
        int[] rank = new int[n];
        int[] freq = new int[v];

        for (int i = 0; i < n; ++i) {
            rank[i] = Arrays.binarySearch(vals, a[i]);
            ++freq[rank[i]];
        }

        int[] allPrefix = new int[v + 1];

        for (int i = 0; i < v; ++i) {
            allPrefix[i + 1] = allPrefix[i] + freq[i];
        }

        int[] cross = new int[n + 1];
        int p = 0;

        for (int outSize = 0; outSize <= n; ++outSize) {
            while (p <= v && allPrefix[p] < outSize) {
                ++p;
            }

            cross[outSize] = p;
        }

        long allTotal = 0;

        for (int x : a) {
            allTotal += x;
        }

        Fenwick fw = new Fenwick(vals, freq);
        long res = Long.MIN_VALUE;

        for (int l = 0; l < n; ++l) {
            fw.clear();
            long cur = 0;

            for (int r = l; r < n; ++r) {
                fw.add(rank[r], a[r]);
                cur += a[r];

                int len = r - l + 1;
                int outSize = n - len;
                int cp = cross[outSize];

                int inLow = fw.countPrefix(cp);
                int outHigh = outSize
                        - (allPrefix[cp] - inLow);

                int q = Math.min(inLow, outHigh);

                if (cp > 0) {
                    int pp = cp - 1;

                    inLow = fw.countPrefix(pp);
                    outHigh = outSize
                            - (allPrefix[pp] - inLow);

                    q = Math.max(
                            q,
                            Math.min(inLow, outHigh)
                    );
                }

                q = Math.min(q, k);

                long cand = cur;

                if (q > 0) {
                    long outTotal = allTotal - cur;

                    long largestOutside = outTotal
                            - fw.sumSmallestOutside(outSize - q);

                    cand = cur
                            - fw.sumSmallestInside(q)
                            + largestOutside;
                }

                res = Math.max(res, cand);
            }
        }

        return res;
    }
}
