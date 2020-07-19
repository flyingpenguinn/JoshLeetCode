import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxNumberOfNonoverlappingStrings {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] right = new int[26];
        int[] left = new int[26];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        for (int i = 0; i < n; i++) {
            int cind = s.charAt(i) - 'a';
            right[cind] = i;
            if (left[cind] == -1) {
                left[cind] = i;
            }
        }
        List<String> r = new ArrayList<>();
        String pending = new String();
        int lastright = n-1;
        for (int i = 0; i < s.length(); i++) {
            if (i > lastright) {
                if (!pending.isEmpty()) {
                    r.add(pending);
                    pending = new String();
                }
            }
            char c = s.charAt(i);
            int cind = c - 'a';
            if (i == left[cind]) {
                int newright = checkright(s, i, right[cind], right, left);
                if (newright != -1) {
                    lastright = newright;
                    pending = s.substring(i, newright + 1);
                }
            }
        }
        if (!pending.isEmpty()) {
            r.add(pending);
        }
        return r;
    }

    private int checkright(String s, int start, int end, int[] right, int[] left) {
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            int cind = c - 'a';
            if (left[cind] < start) {
                return -1;
            } else if (right[cind] > end) {
                end = right[cind];
            }
        }
        return end;
    }
}
