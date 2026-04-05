public class CheckIfArraySortedAndRotated {
    // at most one dec point and if so must be head>=tail (cant dec again)
    public boolean check(int[] a) {
        int n = a.length;
        int dec = 0;
        for (int i = 0; i + 1 < n; ++i) {
            if (a[i] > a[i + 1]) {
                ++dec;
                if (dec > 1) {
                    return false;
                }
            }
        }
        if (dec == 0) {
            return true;
        }
        return a[0] >= a[n - 1];
    }
}
