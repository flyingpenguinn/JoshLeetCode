import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RangeSumOfSortedSubarraySums {
    public int rangeSum(int[] a, int n, int left, int right) {
        // check null etc
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += a[j];
                list.add(sum);
            }
        }
        Collections.sort(list);
        long r = 0;
        for (int i = left - 1; i <= right - 1; i++) {
            r += list.get(i);
            r %= 1000000007;
        }
        return (int) r;
    }
}
