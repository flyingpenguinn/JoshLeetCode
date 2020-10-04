import java.util.Arrays;

public class SpecialArrayWithXGreaterThanX {
    public int specialArray(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        int l = a[0] - 1;
        int u = a[n - 1];
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int ith = binaryFirstNonSmall(a, mid);
            int bigger = n - ith;
            if (bigger == mid) {
                return mid;
            } else if (bigger > mid) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return -1;
    }

    private int binaryFirstNonSmall(int[] a, int t) {
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
