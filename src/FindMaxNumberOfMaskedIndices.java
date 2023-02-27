import java.util.Arrays;

public class FindMaxNumberOfMaskedIndices {
    public int maxNumOfMarkedIndices(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int res = 0;
        int j = 0;
        int i = n / 2;
        for (; i < n && j < n / 2; ++i) {
            if (a[i] >= a[j] * 2) {
                res += 2;
                ++j;
            }
        }
        return res;
    }
}
