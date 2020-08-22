import java.util.Arrays;

public class MagneticForceBetweenBalls {
    // maximize min number, typical binary search
    // this is because for all answers < the result you can do it for sure. for all > the result you can't do it
    // so it has nice cutoff feature
    public int maxDistance(int[] p, int m) {
        Arrays.sort(p);
        int l = 1;
        int u = p[p.length - 1] - p[0];
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (works(p, mid, m)) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private boolean works(int[] p, int mid, int m) {
        int cur = p[0];
        int i = 1;
        while (m > 1) {
            while (i < p.length && p[i] < cur + mid) {
                i++;
            }
            if (i == p.length) {
                return false;
            }
            cur = p[i];
            m--;
        }
        return true;
    }
}
