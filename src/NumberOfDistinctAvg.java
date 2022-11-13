import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NumberOfDistinctAvg {
    public int distinctAverages(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int i = 0;
        int j = n - 1;
        Set<Integer> set = new HashSet<>();
        while (i < j) {
            int sum = a[i] + a[j];
            set.add(sum);
            ++i;
            --j;
        }
        return set.size();
    }
}
