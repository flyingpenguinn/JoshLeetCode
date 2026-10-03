import java.util.ArrayList;
import java.util.List;

public class MinAdjSwapsForKConsecutives {
    /*
   第 oi 个 1 在原位置 i
→ normalized position = i - oi
然后对于每个连续 k 个 1 的窗口：
取 normalized positions 的 median
→ 算所有点到 median 的绝对距离和
    */
    private int getcount(int[] sum, int j, int i) {
        return sum[j] - (i == 0 ? 0 : sum[i - 1]);
    }

    public int minMoves(int[] a, int k) {
        if (k == 1) {
            return 0;
        }
        int n = a.length;
        int oi = 0;
        List<Integer> ol = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (a[i] == 1) {
                ol.add(i - oi);
                ++oi;
            }
        }

        int on = ol.size();
        int[] olsum = new int[on];
        for (int i = 0; i < on; ++i) {
            olsum[i] = (i == 0 ? 0 : olsum[i - 1]) + ol.get(i);
        }
        int res = (int) (1e9);
        for (int i = 0; i < on; ++i) {
            int j = i + k - 1;
            if (j >= on) {
                break;
            }
            int p = (i + j) / 2;
            int lsum = getcount(olsum, p, i);
            int rsum = getcount(olsum, j, p + 1);
            int lcount = p - i + 1;
            int rcount = k - lcount;
            int lr = lcount * ol.get(p) - lsum;
            int rr = rsum - rcount * ol.get(p);
            int cur = lr + rr;
            res = Math.min(res, cur);
        }
        return res;
    }
}
