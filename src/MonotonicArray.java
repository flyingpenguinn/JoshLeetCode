public class MonotonicArray {

    public boolean isMonotonic(int[] a) {
        // increase, 1. decrease, -1
        return mono(a, 1) || mono(a, -1);
    }

    boolean mono(int[] a, int direction) {
        for (int i = 1; i < a.length; i++) {
            if ((a[i] - a[i - 1]) * direction < 0) {
                return false;
            }
        }
        return true;
    }
}
