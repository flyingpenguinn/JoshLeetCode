import java.util.HashSet;
import java.util.Set;

public class MaxUniqueSubarraySumAfterDeletion {
    public int maxSum(int[] a) {
        int n = a.length;
        Set<Integer> set = new HashSet<>();
        int maxneg = -100000;
        for (int ai : a) {
            if (ai > 0) {
                set.add(ai);
            } else if (ai <= 0) {
                maxneg = Math.max(maxneg, ai);
            }
        }
        if (set.isEmpty()) {
            return maxneg;
        }
        int sum = 0;
        for (int si : set) {
            sum += si;
        }
        return sum;
    }
}
