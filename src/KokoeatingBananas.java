public class KokoeatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int n = piles.length;
        int max = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(piles[i], max);
            sum += piles[i];
        }
        int l = Math.max(sum / h, 1);
        int u = max;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            boolean can = caneat(piles, mid, h);
            if (can) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }


    boolean caneat(int[] p, int mid, int h) {
        for (int i = 0; i < p.length && h >= 0; i++) {
            int hour = p[i] / mid;
            if (p[i] % mid != 0) {
                hour++;
            }
            h -= hour;
        }
        if (h < 0) {
            return false;
        }
        return true;
    }
}
