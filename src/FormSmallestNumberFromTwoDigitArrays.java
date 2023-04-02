import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FormSmallestNumberFromTwoDigitArrays {
    public int minNumber(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        Set<Integer> sa = new HashSet<>();
        for (int ai : a) {
            sa.add(ai);
        }
        Set<Integer> sb = new HashSet<>();
        for (int bi : b) {
            sb.add(bi);
        }
        for (int i = 1; i <= 9; ++i) {
            if (sa.contains(i) && sb.contains(i)) {
                return i;
            }
        }
        int min1 = Math.min(a[0], b[0]);
        int min2 = Math.max(a[0], b[0]);
        return min1 * 10 + min2;
    }
}
