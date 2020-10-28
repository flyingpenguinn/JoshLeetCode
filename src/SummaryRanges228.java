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
        List<String> res = new ArrayList<>();
        int n = a.length;
        if (n == 0) {
            return res;
        }
        int s = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] != a[i - 1] + 1) {
                res.add(make(s, a[i - 1]));
                s = a[i];
            }
        }
        res.add(make(s, a[n - 1])); // don't miss out the last interval
        return res;
    }

    private String make(int s, int e) {
        return s == e ? String.valueOf(s) : s + "->" + e;
    }
}
