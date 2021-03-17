import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxNumberOfNonoverlappingStrings {

    // 1. find good substrings first, at most 26 of them starting from a left
    // 2. use normal interval way to pick the most non conflicting intervals
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] right = new int[26];
        int[] left = new int[26];
        for (int i = 0; i < n; i++) {
            right[s.charAt(i) - 'a'] = i;
        }
        for (int i = n - 1; i >= 0; i--) {
            left[s.charAt(i) - 'a'] = i;
        }
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int cind = s.charAt(i) - 'a';
            if (left[cind] == i) {
                // doing this at most 26 times. for each start we find the end and validate if this is a valid substring
                int cend = extend(s, i, right[cind], left, right);
                if (cend != -1) {
                    intervals.add(new int[]{i, cend});
                }
            }
        }
        List<String> res = new ArrayList<>();
        Collections.sort(intervals, (x, y) -> Integer.compare(x[1], y[1]));
        int end = -1;
        for (int i = 0; i < intervals.size(); i++) {
            int cs = intervals.get(i)[0];
            int ce = intervals.get(i)[1];
            if (cs > end) {
                res.add(s.substring(cs, ce + 1));
                end = ce;
            }
        }
        return res;
    }

    private int extend(String s, int i, int j, int[] left, int[] right) {

        int end = j;
        for (int k = i; k <= end; k++) {
            int cind = s.charAt(k) - 'a';
            if (left[cind] < i) {
                return -1;
            } else if (right[cind] > end) {
                end = right[cind];
            }
        }
        return end;
    }
}
