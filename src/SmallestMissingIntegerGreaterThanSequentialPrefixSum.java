import java.util.HashSet;
import java.util.Set;

public class SmallestMissingIntegerGreaterThanSequentialPrefixSum {
    public int missingInteger(int[] a) {
        int n = a.length;
        int i = 0;
        int j = 1;
        int sum = a[i];
        while (j < n && a[j] == a[j - 1] + 1) {
            sum += a[j];
            ++j;
        }
        Set<Integer> sa = new HashSet<>();
        for (int ai : a) {
            sa.add(ai);
        }
        while (sa.contains(sum)) {
            ++sum;
        }
        return sum;
    }
}
