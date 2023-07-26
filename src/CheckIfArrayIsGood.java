import java.util.Arrays;

public class CheckIfArrayIsGood {
    public boolean isGood(int[] a) {
        Arrays.sort(a);
        int n = a.length;
        if (n <= 1) {
            return false;
        }
        for (int i = 0; i < n - 1; ++i) {
            if (a[i] != i + 1) {
                return false;
            }
        }
        if (a[n - 1] != n - 1 || a[n - 1] != a[n - 2]) {
            return false;
        }
        return true;
    }
}
