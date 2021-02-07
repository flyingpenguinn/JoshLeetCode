public class CheckIfArraySortedAndRotated {
    // first and last connected, > can happen at most once
    public boolean check(int[] a) {
        int n = a.length;
        boolean seen = false;
        for (int i = 0; i < n; i++) {
            if (a[i] > a[(i + 1) % n]) {
                if (seen) {
                    return false;
                }
                seen = true;
            }
        }
        return true;
    }
}
