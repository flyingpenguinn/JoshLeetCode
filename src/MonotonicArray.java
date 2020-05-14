public class MonotonicArray {
    public boolean isMonotonic(int[] a) {
        boolean bg = false;
        boolean sm = false;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[i - 1]) {
                if (sm) {
                    return false;
                }
                bg = true;
            } else if (a[i] < a[i - 1]) {
                if (bg) {
                    return false;
                }
                sm = true;
            }
        }
        return true;
    }
}
