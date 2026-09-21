import java.util.Arrays;

public class CountIntersectingIntervalPairsII {
    public long countIntersectingIntervals(int[][] a) {
        int n = a.length;
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; ++i) {
            int si = a[i][0];
            int ei = a[i][1];
            starts[i] = si;
            ends[i] = ei;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        long res = 0;
        for (int i = 0; i < n; ++i) {
            long cres = 0;
            int ei = a[i][1];
            int pos1 = binaryfirstbigger(starts, ei);
            long counts1 = n - pos1;
            cres += counts1;

            int si = a[i][0];
            int pos2 = binarylastsmaller(ends, si);
            long counts2 = pos2 + 1;
            cres += counts2;
            long other = n - 1 - cres;
            res += other;
        }
        return res / 2;

    }

    private int binaryfirstbigger(int[] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] > t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int binarylastsmaller(int[] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] < t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}
