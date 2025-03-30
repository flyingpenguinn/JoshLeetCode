public class FindMinAmountTimeToBrewPotions {
    public long minTime(int[] s, int[] m) {
        int sn = s.length;
        int mn = m.length;
        long[][] done = new long[sn][mn];
        for (int j = 0; j < mn; ++j) {
            for (int i = 0; i < sn; ++i) {
                final long ccost = s[i] * m[j];
                done[i][j] = Math.max(done[i][j], (i - 1 < 0 ? 0 : done[i - 1][j]) + ccost);
                if (j - 1 >= 0) {
                    done[i][j] = Math.max(done[i][j], done[i][j - 1] + ccost);
                }
            }
            for (int i = sn - 1; i >= 1; --i) {
                done[i - 1][j] = done[i][j] - s[i] * m[j];
            }
        }
        return done[sn - 1][mn - 1];
    }

}

class FindMinAmountTimeToBrewPotionsBinarySearch {
    public long minTime(int[] is, int[] im) {
        int sn = is.length;
        int mn = im.length;
        long[] s = new long[sn];
        long[] m = new long[mn];
        for (int i = 0; i < sn; ++i) {
            s[i] = is[i];
        }
        for (int i = 0; i < mn; ++i) {
            m[i] = im[i];
        }
        long upper = 0;
        for (int i = 0; i < sn; ++i) {
            for (int j = 0; j < mn; ++j) {
                upper += s[i] * m[j];
            }
        }
        long res = 0;
        long[] fin = new long[sn];
        for (int j = 0; j < mn; ++j) {
            long l = 0;
            long u = upper;
            while (l <= u) {
                long mid = l + (u - l) / 2;
                if (good(mid, fin, s, m[j])) {
                    u = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
            fin[0] = l + s[0] * m[j];
            res = Math.max(res, fin[0]);
            for (int i = 1; i < sn; ++i) {
                fin[i] = fin[i - 1] + s[i] * m[j];
                res = Math.max(fin[i], res);
            }
        }
        return res;
    }

    private boolean good(long mid, long[] fin, long[] s, long mana) {
        int sn = s.length;
        if (mid < fin[0]) {
            return false;
        }
        long cur = mid + s[0] * mana;
        for (int i = 1; i < sn; ++i) {
            if (cur < fin[i]) {
                return false;
            }

            cur = cur + s[i] * mana;
            if (cur < fin[i]) {
                return false;
            }
        }
        return true;
    }
}
