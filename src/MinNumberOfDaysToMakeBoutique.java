public class MinNumberOfDaysToMakeBoutique {
    public int minDays(int[] b, int m, int k) {
        int l = 1;
        int Max = 1000000000;
        int u = Max;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (make(b, mid, k) >= m) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l > Max ? -1 : l;
    }

    private int make(int[] b, int mid, int k) {
        int n = b.length;
        int i = 0;
        int r = 0;
        while (i < n) {
            if (b[i] <= mid) {
                int j = i;
                while (j < n && b[j] <= mid && j - i < k) {
                    j++;
                }
                if (j - i == k) {
                    r++;
                }
                i = j;
            } else {
                i++;
            }
        }
        return r;
    }
}
