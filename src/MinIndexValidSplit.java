import java.util.List;

public class MinIndexValidSplit {
    // must be the dominant number
    public int minimumIndex(List<Integer> a) {
        int n = a.size();
        int cand = a.get(0);
        int times = 1;
        for (int i = 1; i < a.size(); i++) {
            if (cand == a.get(i)) {
                times++;
            } else if (times == 0) {
                cand = a.get(i);
            } else {
                times--;

            }
        }
        int lc = 0;
        int rc = 0;
        for (int i = 0; i < n; ++i) {
            if (a.get(i) == cand) {
                ++rc;
            }
        }

        for (int i = 0; i < n; ++i) {
            if (a.get(i) == cand) {
                ++lc;
                --rc;
            }
            int lsize = i + 1;
            int rsize = n - 1 - i;
            if (lc * 2 > lsize && rc * 2 > rsize) {
                return i;
            }
        }
        return -1;
    }
}
