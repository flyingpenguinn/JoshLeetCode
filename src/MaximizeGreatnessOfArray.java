import java.util.Arrays;

public class MaximizeGreatnessOfArray {
    public int maximizeGreatness(int[] a) {
        Arrays.sort(a);
        int j = 0;
        for (int i = 0; i < a.length; ++i) {
            if (a[i] > a[j]) {
                ++j;
            }
        }
        return j;
    }
}
