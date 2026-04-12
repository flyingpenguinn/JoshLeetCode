public class GoodDubseqQueries {
    // TODO

    private static final int MX = 50000;
    private static final int LIM = 7;

    private static final int[] sp = new int[MX + 1];
    private static final int[][] pf = new int[MX + 1][LIM];
    private static final int[] pc = new int[MX + 1];

    static {
        for (int i = 2; i <= MX; ++i) {
            if (sp[i] == 0) {
                sp[i] = i;
                if ((long) i * i <= MX) {
                    for (int j = i * i; j <= MX; j += i) {
                        if (sp[j] == 0) {
                            sp[j] = i;
                        }
                    }
                }
            }
        }

        for (int v = 2; v <= MX; ++v) {
            int x = v;
            while (x > 1) {
                int r = sp[x];
                pf[v][pc[v]++] = r;
                while (x % r == 0) {
                    x /= r;
                }
            }
        }
    }

    private int n;
    private int xa;
    private int bc;
    private int[] cnt;
    private int[] xr;
    private int[] fq;
    private int[] bad;

    public int countGoodSubseq(int[] nums, int p, int[][] queries) {
        n = nums.length;

        cnt = new int[MX + 1];
        xr = new int[MX + 1];
        fq = new int[n + 1];
        bad = new int[n];

        xa = 0;
        for (int i = 0; i < n; ++i) {
            xa ^= i;
        }

        int m = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] % p == 0) {
                ++m;
                int x = nums[i] / p;
                for (int k = 0; k < pc[x]; ++k) {
                    int r = pf[x][k];
                    ++cnt[r];
                    xr[r] ^= i;
                }
            }
        }

        for (int r = 2; r <= MX; ++r) {
            if (cnt[r] > 0) {
                ++fq[cnt[r]];
            }
        }

        if (n >= 2) {
            for (int r = 2; r <= MX; ++r) {
                if (cnt[r] == n - 1) {
                    int miss = xa ^ xr[r];
                    addBad(miss);
                }
            }
        }

        int res = 0;
        for (int[] q : queries) {
            int i = q[0];
            int v = q[1];

            if (nums[i] % p == 0) {
                --m;
                int x = nums[i] / p;
                for (int k = 0; k < pc[x]; ++k) {
                    del(pf[x][k], i);
                }
            }

            nums[i] = v;

            if (v % p == 0) {
                ++m;
                int x = v / p;
                for (int k = 0; k < pc[x]; ++k) {
                    add(pf[x][k], i);
                }
            }

            if (ok(m)) {
                ++res;
            }
        }

        return res;
    }

    private boolean ok(int m) {
        if (m == 0) {
            return false;
        }
        if (fq[m] > 0) {
            return false;
        }
        if (m < n) {
            return true;
        }
        return bc < n;
    }

    private void add(int r, int i) {
        if (n >= 2 && cnt[r] == n - 1) {
            int miss = xa ^ xr[r];
            removeBad(miss);
        }

        if (cnt[r] > 0) {
            --fq[cnt[r]];
        }

        ++cnt[r];
        xr[r] ^= i;
        ++fq[cnt[r]];

        if (n >= 2 && cnt[r] == n - 1) {
            int miss = xa ^ xr[r];
            addBad(miss);
        }
    }

    private void del(int r, int i) {
        if (n >= 2 && cnt[r] == n - 1) {
            int miss = xa ^ xr[r];
            removeBad(miss);
        }

        --fq[cnt[r]];
        --cnt[r];
        xr[r] ^= i;

        if (cnt[r] > 0) {
            ++fq[cnt[r]];
        }

        if (n >= 2 && cnt[r] == n - 1) {
            int miss = xa ^ xr[r];
            addBad(miss);
        }
    }

    private void addBad(int i) {
        if (++bad[i] == 1) {
            ++bc;
        }
    }

    private void removeBad(int i) {
        if (--bad[i] == 0) {
            --bc;
        }
    }


}
