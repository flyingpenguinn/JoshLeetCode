public class FindPivotInteger {
    public int pivotInteger(int n) {
        int l = 1;
        int u = n;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int sum1 = (1 + mid) * (mid) / 2;
            int sum2 = (mid + n) * (n - mid + 1) / 2;

            if (sum1 < sum2) {
                l = mid + 1;
            } else if (sum1 > sum2) {
                u = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
