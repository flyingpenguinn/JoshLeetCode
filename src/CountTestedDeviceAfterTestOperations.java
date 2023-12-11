public class CountTestedDeviceAfterTestOperations {
    public int countTestedDevices(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] - res > 0) {
                ++res;
            }
        }
        return res;
    }
}
