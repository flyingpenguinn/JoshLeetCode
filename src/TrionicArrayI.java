public class TrionicArrayI {
    public boolean isTrionic(int[] a) {
        int n = a.length;
        int i = 0;
        while (i + 1 < n && a[i] < a[i + 1]) {
            ++i;
        }
        int p = i;
        if (p == 0) {
            return false;
        }
        int j = p;
        while (j + 1 < n && a[j] > a[j + 1]) {
            ++j;
        }
        int q = j;
        if (p >= q || q == n - 1) {
            return false;
        }
        while (j + 1 < n && a[j] < a[j + 1]) {
            ++j;
        }
        return j == n - 1;
    }
}
