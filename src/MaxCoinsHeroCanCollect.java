import java.util.Arrays;

public class MaxCoinsHeroCanCollect {
    public long[] maximumCoins(int[] heros, int[] monsters, int[] coins) {
        int mn = monsters.length;
        long[][] mc = new long[mn][2];
        for (int i = 0; i < mn; ++i) {
            mc[i] = new long[]{monsters[i], coins[i]};
        }
        Arrays.sort(mc, (x, y) -> Long.compare(x[0], y[0]));
        int hn = heros.length;
        int[][] hc = new int[hn][2];
        for (int i = 0; i < hn; ++i) {
            hc[i] = new int[]{heros[i], i};
        }
        Arrays.sort(hc, (x, y) -> Integer.compare(x[0], y[0]));
        int i = 0;
        int j = 0;
        long[] res = new long[hn];
        long cur = 0;
        while (i < hn) {
            while (j < mn && hc[i][0] >= mc[j][0]) {
                cur += mc[j][1];
                ++j;
            }
            res[hc[i][1]] = cur;
            ++i;
        }
        return res;
    }
}
