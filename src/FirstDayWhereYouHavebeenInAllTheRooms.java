public class FirstDayWhereYouHavebeenInAllTheRooms {
    // must go from i-1 to i. when we first reach i-1, we need to go back to a[i-1] then go back to i-1, this time going to i
    private int mod = (int) (1e9 + 7);

    public int firstDayBeenInAllRooms(int[] a) {
        int n = a.length;
        long[] psum = new long[n];
        for (int i = 0; i < n - 1; ++i) {
            long cur = 2;
            if (a[i] < i) {
                int pre = a[i];
                cur += (psum[i - 1] - (pre == 0 ? 0 : psum[pre - 1])); // rego from pre to i-1 just one more time
                cur %= mod;
                if (cur < 0) {
                    cur += mod;
                }
            }
            psum[i] = (i == 0 ? 0 : psum[i - 1]) + cur;
            psum[i] %= mod;
        }
        return (int) psum[n - 2];
    }
}
