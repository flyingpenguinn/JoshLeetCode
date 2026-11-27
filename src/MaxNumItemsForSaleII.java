import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 3947. Daily 2026-11-27 (with coach hints): buying c copies of item i gives c + min(c, other_i) items.
// So item i offers other_i "2-packs" at p_i, plus unlimited "1-packs"; only the cheapest 1-pack (price m) matters.
// Buy 2-packs with p_i <= 2m from cheapest up, then spend the rest on 1-packs at m.
public class MaxNumItemsForSaleII {
    public int maximumSaleItems(int[][] a, int budget) {
        int n = a.length;
        int limit = 100001;
        int maxv = 0;
        int minprice = (int) 1e9 + 5;
        for (int[] ai : a) {
            maxv = Math.max(maxv, ai[0]);
            minprice = Math.min(minprice, ai[1]);
        }
        limit = Math.min(limit, maxv + 1);
        int[] cnt = new int[limit];
        for (int i = 0; i < n; ++i) {
            int f = a[i][0];
            ++cnt[f];
        }
        int[] div = new int[limit];
        for (int i = 1; i < limit; ++i) {
            for (int j = i; j < limit; j += i) {
                div[i] += cnt[j];
            }
        }
        List<int[]> twos = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int other = div[a[i][0]] - 1;
            if (other == 0) {
                continue;
            }
            if (a[i][1] > 2 * minprice) {
                continue;
            }
            twos.add(new int[]{i, other, a[i][1]});
        }
        Collections.sort(twos, (x, y) -> Integer.compare(x[2], y[2]));
        int res = 0;
        for (int i = 0; i < twos.size(); ++i) {
            int[] item = twos.get(i);
            int other = item[1];
            int tobuy = Math.min(other, budget / item[2]);
            int twoitems = Math.min(tobuy, other) + tobuy;
            res += twoitems;
            budget -= tobuy * item[2];
        }
        int extra = budget / minprice;
        res += extra;
        return res;
    }
}
