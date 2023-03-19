public class NumberOfBeautifulSubsets {
    public int beautifulSubsets(int[] a, int k) {
        int n = a.length;
        int res = 0;
        for (int st = 1; st < (1 << n); ++st) {
            boolean[] set = new boolean[1001];
            boolean bad = false;
            for (int i = 0; i < n; ++i) {
                if (((st >> i) & 1) == 1) {
                    int cur = a[i];
                    if (cur - k >= 1 && set[cur - k]) {
                        bad = true;
                        break;
                    }
                    if (cur + k <= 1000 && set[cur + k]) {
                        bad = true;
                        break;
                    }
                    set[cur] = true;
                }
            }
            if (!bad) {
                ++res;
            }
        }
        return res;
    }
}
