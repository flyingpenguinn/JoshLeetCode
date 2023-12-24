import java.util.Arrays;

public class FindPoloygonWithLargestPerimeter {
    public long largestPerimeter(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        long rest = 0;
        for (int ai : a) {
            rest += ai;
        }
        for (int i = n - 1; i >= 0; --i) {
            rest -= a[i];
            if (rest > a[i]) {
                return rest + a[i];
            }
        }
        return -1;
    }
}
