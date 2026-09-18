import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxNumberOfNonoverlappingStrings {

    // 1. find good substrings first, at most 26 of them starting from a left
    // 2. use normal interval way to pick the most non conflicting intervals
    private int[][] range = new int[26][2];


    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        for (int i = 0; i < 26; ++i) {
            Arrays.fill(range[i], -1);
        }
        for (int i = 0; i < n; ++i) {
            int cind = s.charAt(i) - 'a';
            if (range[cind][0] == -1) {
                range[cind][0] = i;
            }
            range[cind][1] = i;
        }
        List<int[]> l = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            int start = range[i][0];
            int end = range[i][1];
            if (start == -1) {
                continue;
            }
            boolean bad = false;
            for (int j = start; j <= end; ++j) {
                int jv = s.charAt(j) - 'a';
                if (range[jv][0] < start) {
                    bad = true;
                    break;
                } else {
                    end = Math.max(end, range[jv][1]);
                }
            }
            if (!bad) {
                l.add(new int[]{start, end});
            }
        }
        Collections.sort(l, (x, y) -> {
            if (x[1] != y[1]) {
                return Integer.compare(x[1], y[1]);
            } else {
                return Integer.compare(y[0], x[0]);
            }
        });
        int start = -1;
        int end = -1;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < l.size(); ++i) {
            if (l.get(i)[0] > end) {
                if (start != -1) {
                    res.add(s.substring(start, end + 1));
                }
                start = l.get(i)[0];
                end = l.get(i)[1];
            }
        }
        if (start != -1) {
            res.add(s.substring(start, end + 1));
        }
        return res;
    }
}
