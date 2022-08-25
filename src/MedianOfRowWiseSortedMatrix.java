public class MedianOfRowWiseSortedMatrix {
    public int matrixMedian(int[][] a) {
        int l = 1;
        int u = (int) 1e6;
        int m = a.length;
        int n = a[0].length;
        int all = m * n;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int cur = 0;
            for (int i = 0; i < m; ++i) {
                int pos = binary(a[i], mid);
                cur += pos;
            }
            //  System.out.println(l+" "+u+" "+mid+" "+cur);
            if (cur > all / 2) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return u;
    }

    private int binary(int[] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] >= t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
