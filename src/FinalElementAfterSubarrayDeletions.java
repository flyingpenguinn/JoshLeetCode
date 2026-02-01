public class FinalElementAfterSubarrayDeletions {
    public int finalElement(int[] a) {
        int n = a.length;
        if (n == 1) {
            return a[0];
        }
        return Math.max(a[0], a[n - 1]);
    }
}
