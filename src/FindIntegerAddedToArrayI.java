import java.util.Arrays;

public class FindIntegerAddedToArrayI {
    public int addedInteger(int[] a, int[] b) {
        Arrays.sort(a);
        Arrays.sort(b);
        return b[0] - a[0];
    }
}
