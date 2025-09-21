public class MaxTotalSubarrayValueI {
    public long maxTotalValue(int[] a, int k) {
        int n = a.length;
        long minv = (long) 2e9;
        long maxv = -1;
        for (int ai : a) {
            minv = Math.min(minv, ai);
            maxv = Math.max(maxv, ai);
        }
        long diff = maxv - minv;
        long res = diff * k;
        return res;
    }
}
