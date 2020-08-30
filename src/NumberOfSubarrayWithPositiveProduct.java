public class NumberOfSubarrayWithPositiveProduct {
    // we start a new base when we see prod = 0
    public int getMaxLen(int[] a) {
        int n = a.length;
        int firstpos = -1;
        int firstneg = -1;
        int base = 0; // first non 0 number in this streak
        int prod = 1;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int sign = a[i] == 0 ? 0 : a[i] > 0 ? 1 : -1;
            prod = prod * sign;
            if (prod > 0) {
                max = Math.max(max, i - base + 1);
            }
            if (prod > 0 && firstpos != -1) {
                max = Math.max(max, i - firstpos);
            } else if (prod < 0 && firstneg != -1) {
                max = Math.max(max, i - firstneg);
            }
            if (prod > 0 && firstpos == -1) {
                firstpos = i;
            } else if (prod < 0 && firstneg == -1) {
                firstneg = i;
            } else if (prod == 0) {
                firstpos = -1;
                firstneg = -1;
                base = i + 1;
                prod = 1;
            }
        }
        return max;
    }
}
