public class FindPeakElement {
    public int findPeakElement(int[] a) {
        int n = a.length;
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (mid == 0) {
                // can't go more left
                l = mid + 1;
                continue;
            }
            int left = mid - 1;
            long cur = a[mid];
            long cleft = a[left];
            if (cur >= cleft) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }
}
