import java.util.Arrays;

public class ConstructUniformParityArrayIandII {
    // i is also about checking odd but range is whole array, so just return true
    public boolean uniformArray(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        return all(a, 0) || all(a, 1);
    }

    private boolean all(int[] a, int mod) {
        int n = a.length;
        boolean hasodd = false;
        for (int i = 0; i < n; ++i) {

            if (a[i] % 2 == mod) {
                //
            } else {
                if (!hasodd) {
                    return false;
                }
            }
            if (a[i] % 2 == 1) {
                hasodd = true;
            }
        }
        return true;
    }
}
