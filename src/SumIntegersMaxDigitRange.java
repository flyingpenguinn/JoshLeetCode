public class SumIntegersMaxDigitRange {
    public int maxDigitRange(int[] a) {
        int n = a.length;
        int maxdiff = 0;
        int res = 0;
        for (int ai : a) {
            int[] cnt = getcount(ai);
            int maxi = -1;
            int mini = 10;
            for (int i = 0; i < 10; ++i) {
                if (cnt[i] > 0) {
                    maxi = Math.max(maxi, i);
                    mini = Math.min(mini, i);
                }
            }
            int diff = maxi - mini;
            if (diff > maxdiff) {
                maxdiff = diff;
                res = ai;
            } else if (diff == maxdiff) {
                res += ai;
            }

        }
        return res;
    }

    private int[] getcount(int num) {
        int[] cnt = new int[10];
        if (num == 0) {
            ++cnt[0];
            return cnt;
        }
        while (num > 0) {
            int d = num % 10;
            ++cnt[d];
            num /= 10;
        }
        return cnt;
    }
}
