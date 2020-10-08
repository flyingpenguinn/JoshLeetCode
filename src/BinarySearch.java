public class BinarySearch {
    public int search(int[] a, int t) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] == t) {
                return mid;
            } else if (a[mid] > t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
}
