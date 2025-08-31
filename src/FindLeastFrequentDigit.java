public class FindLeastFrequentDigit {
    public int getLeastFrequentDigit(int n) {
        String sn = String.valueOf(n);
        int[] cnt = new int[10];
        for (int i = 0; i < sn.length(); ++i) {
            char c = sn.charAt(i);
            int cind = c - '0';
            ++cnt[cind];
        }
        int mincnt = n;
        for (int i = 0; i < 10; ++i) {
            if (cnt[i] > 0) {
                mincnt = Math.min(mincnt, cnt[i]);
            }
        }
        for (int i = 0; i < 10; ++i) {
            if (cnt[i] == mincnt) {
                return i;
            }
        }
        return -1;
    }
}
