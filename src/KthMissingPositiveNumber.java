public class KthMissingPositiveNumber {
    public int findKthPositive(int[] a, int k) {
        int n = a.length;
        int l = 0;
        int u = n - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int gap = a[mid] - (mid + 1);
            if (gap < k) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        // between u and l, l can be n, u can be -1
        if (u < 0) {
            return k;
        } else {
            int cgap = a[u] - (u + 1);
            return a[u] + (k - cgap);
        }
    }
}
