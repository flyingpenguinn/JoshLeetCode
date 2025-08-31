import java.util.Arrays;

public class SortArrayByAbsOrder {
    public int[] sortByAbsoluteValue(int[] a) {
        int n = a.length;
        Integer[] ca = new Integer[n];
        for (int i = 0; i < n; ++i) {
            ca[i] = a[i];
        }
        Arrays.sort(ca, (x, y) -> Integer.compare(Math.abs(x), Math.abs(y)));
        int[] res = new int[n];
        for (int i = 0; i < n; ++i) {
            res[i] = ca[i];
        }
        return res;
    }
}
