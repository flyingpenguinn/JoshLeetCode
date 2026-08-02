import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountSubarrayWithEvenOddRatio {
    static class FenWick {
        private int[] bit;

        public FenWick(int n) {
            this.bit = new int[n];
        }

        private int q(int i) {
            int res = 0;
            while (i > 0) {
                res += bit[i];
                i -= i & (-i);
            }
            return res;
        }

        private void u(int i, int d) {

            while (i < bit.length) {
                bit[i] += d;
                i += i & (-i);
            }
        }
    }

    public int countRatioSubarrays(int[] v, int a, int b) {
        int n = v.length;
        int[] cnt = new int[2];

        long[] lv = new long[n];
        for (int i = 0; i < n; ++i) {
            long cv = v[i];
            ++cnt[(int) (cv % 2)];

            long pv = cnt[0] * b - a * cnt[1];
            lv[i] = pv;
        }
        long[] sv = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            sv[i] = lv[i];
        }
        sv[n] = 0;
        Arrays.sort(sv);
        int rank = 1;
        Map<Long, Integer> rm = new HashMap<>();
        for (int i = 0; i <= n; ++i) {
            if (i == 0 || sv[i] != sv[i - 1]) {
                rm.put(sv[i], rank);
                ++rank;
            }
        }
        FenWick fen = new FenWick(rank);
        int j = -1;
        long res = 0;
        int lastodd = -1;
        for (int i = 0; i < n; ++i) {
            int crank = rm.get(lv[i]);
            if (v[i] % 2 == 1) {
                lastodd = i;
            }
            long qualified = 0;
            if (v[i] % 2 == 0) {
                while (j < lastodd) {
                    int jrank = rm.get((j == -1 ? 0 : lv[j]));
                    fen.u(jrank, 1);
                    ++j;
                }
                qualified = i + 1 - (i - lastodd);
            } else {
                while (j < i) {
                    int jrank = rm.get((j == -1 ? 0 : lv[j]));
                    fen.u(jrank, 1);
                    ++j;
                }

                qualified = i + 1;
            }
            long count = fen.q(crank - 1);
            long rem = qualified - count;
            res += rem;
        }
        return (int) res;
    }
}
