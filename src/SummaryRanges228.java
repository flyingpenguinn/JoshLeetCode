import java.util.ArrayList;
import java.util.List;

/*
LC#228
Given a sorted integer array without duplicates, return the summary of its ranges.

Example 1:

Input:  [0,1,2,4,5,7]
Output: ["0->2","4->5","7"]
Explanation: 0,1,2 form a continuous range; 4,5 form a continuous range.
Example 2:

Input:  [0,2,3,4,6,8,9]
Output: ["0","2->4","6","8->9"]
Explanation: 2,3,4 form a continuous range; 8,9 form a continuous range.
 */
public class SummaryRanges228 {
    public List<String> summaryRanges(int[] a) {
        // start is 0 or when we deviate
        int n = a.length;
        List<String> r = new ArrayList<>();
        int si = 0;
        for (int i = 1; i <= n; i++) {
            if (i == n || a[i] > a[i - 1] + 1) {
                r.add(rang(a, si, i - 1));
                si = i;
            }
        }
        return r;
    }

    String rang(int[] a, int i, int j) {
        if (i == j) {
            return a[i] + "";
        } else {
            return a[i] + "->" + a[j];
        }
    }
}
