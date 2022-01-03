public class NumberOfLaserBeamsInBank {
    public int numberOfBeams(String[] a) {
        int m = a.length;
        int n = a[0].length();
        int prev = 0;
        int res = 0;
        for (int i = 0; i < m; ++i) {
            int cur = 0;
            for (int j = 0; j < n; ++j) {
                char c = a[i].charAt(j);
                if (c == '1') {
                    ++cur;
                }
            }
            if (cur == 0) {
                continue;
            }
            res += prev * cur;
            prev = cur;
        }
        return res;
    }
}
