public class KthMissingPositiveNumber {
    public int findKthPositive(int[] a, int k) {
        int first = a[0] - 1;
        if (first >= k) {
            return k;
        }
        k -= first;
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int th = a[mid] - a[0] - mid;
            if (th >= k) {
                // we are looking for th < k
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        // au is the last number that from a0... au there are <k numbers missing
        k -= (a[u] - a[0] - u);
        return a[u] + k;
    }
}
