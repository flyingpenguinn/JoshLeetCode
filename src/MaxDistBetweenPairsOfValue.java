public class MaxDistBetweenPairsOfValue {
    public int maxDistance(int[] a, int[] b) {
        int j = b.length - 1;
        int i = a.length - 1;
        int res = 0;
        while (j >= 0) {
            while (i >= 0 && a[i] <= b[j]) {
                i--;
            }
            // i+1... a[n-1] <= bj
            // i+1... j is the good part
            // note we must check that i != n because in that case there is no good a at all
            if (i + 1 < a.length) {
                res = Math.max(res, j - i - 1);
            }
            j--;
        }
        return res;
    }
}

class MaxDistBetweenPairsBinarySearch {
    public int maxDistance(int[] a, int[] b) {
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            int pos = binary(b, a[i]);
            // 0... pos >= a[i]

            if (pos >= i && pos >= 0) {
                res = Math.max(res, pos - i);
            }
        }
        return res;
    }

    // a non increasing, find biggest i to be >=t
    private int binary(int[] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] >= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}
