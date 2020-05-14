import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
LC#506
Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

Example 1:
Input: [5, 4, 3, 2, 1]
Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal".
For the left two athletes, you just need to output their relative ranks according to their scores.
Note:
N is a positive integer and won't exceed 10,000.
All the scores of athletes are guaranteed to be unique.
 */
public class RelativeRanks {
    // typical way of getting ranks, for fenwick tree
    public String[] findRelativeRanks(int[] a) {
        int n = a.length;
        int[] ca = Arrays.copyOf(a, n);
        Arrays.sort(ca);
        Map<Integer, Integer> rankm = new HashMap<>();
        int cr = 1;
        for (int i = n - 1; i >= 0; i--) {
            rankm.put(ca[i], cr++);
        }
        String[] r = new String[n];
        for (int i = 0; i < n; i++) {
            int rank = rankm.get(a[i]);
            if (rank == 1) {
                r[i] = "Gold Medal";
            } else if (rank == 2) {
                r[i] = "Silver Medal";
            } else if (rank == 3) {
                r[i] = "Bronze Medal";
            } else {
                r[i] = String.valueOf(rank);
            }
        }
        return r;
    }
}

