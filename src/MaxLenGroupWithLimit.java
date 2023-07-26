import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MaxLenGroupWithLimit {
    /*
    "We sort usageLimits, and go smallest to largest, adding numbers we can use so far.

    When there is enough to form m + 1 groups, we increment m."

     */
    public int maxIncreasingGroups(List<Integer> a) {
        Collections.sort(a);
        long total = 0;
        long count = 0;
        for (int i = 0; i < a.size(); ++i) {
            total += a.get(i);
            if (total >= ((count + 1L) * (count + 2)) / 2) {
                ++count;
            }
        }
        return (int)count;
    }


    public static void main(String[] args) {
        System.out.println(new MaxLenGroupWithLimit().maxIncreasingGroups(List.of(1, 2, 5)));
    }
}
