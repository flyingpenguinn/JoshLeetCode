public class MaxAbsSumOfSubarray {
    public int maxAbsoluteSum(int[] a) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int pmax = 0;
        int pmin = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            if (pmax < 0) {
                pmax = a[i];
            } else {
                pmax = pmax + a[i];
            }
            max = Math.max(max, pmax);
            if (pmin > 0) {
                pmin = a[i];
            } else {
                pmin = pmin + a[i];
            }
            min = Math.min(min, pmin);
        }
        int rt = Math.max(Math.abs(max), Math.abs(min));
        return rt;
    }
}
