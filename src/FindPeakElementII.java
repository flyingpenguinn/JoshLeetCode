public class FindPeakElementII {
    // compare central column and left/right columns, figure out the column with the biggest number, then recurse on them
    private int max = -1, maxc = -1, maxr = -1;

    public int[] findPeakGrid(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            max = -1;
            maxc = -1;
            maxr = -1;
            int mid = l + (u - l) / 2;
            for (int i = 0; i < m; i++) {
                int left = value(i, mid - 1, a);
                int right = value(i, mid + 1, a);
                int cv = value(i, mid, a);
                process(cv, i, mid);
                process(left, i, mid - 1);
                process(right, i, mid + 1);
            }
            if (maxc == mid) {
                return new int[]{maxr, maxc};
            } else if (maxc == mid - 1) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }

    void process(int v, int r, int c) {
        if (v > max) {
            maxr = r;
            maxc = c;
            max = v;
        }
    }

    int value(int i, int j, int[][] a) {
        if (i < 0 || i >= a.length || j < 0 || j >= a[0].length) {
            return -1;
        }
        return a[i][j];
    }
}
