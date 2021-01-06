public class KthMissingPositiveNumber {
    public int findKthPositive(int[] a, int k) {
        int n = a.length;
        int front = a[0] - 1;
        if (k <= front) {
            return k;
        }
        k -= front;
        int l = 0;
        int u = n - 1;
        // find the last qualifying number that middiff is <k
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int middiff = a[mid] - a[0] - mid;
            if (middiff < k) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        // u is the last <, we add on top of u. note u wont be -1 because k >0 in the while and mid diff for 0 ==0
        int udiff = a[u] - a[0] - u;
        return a[u] + (k - udiff);
    }
}
