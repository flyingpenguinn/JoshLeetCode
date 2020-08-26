import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxNumberOfNonoverlappingStrings {
    /*

    1. find left and right of each char
    2. if i > pending end we know we found a good pending string- the one ending at the "pendingend"
    3. each time if we find a good pending we update pending end. in this way we can SPLIT a longer pending string to smaller ones

     */
    public List<String> maxNumOfSubstrings(String s) {
        int[] left = new int[26];
        int[] right = new int[26];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        int n = s.length();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int cind = c - 'a';
            right[cind] = i;
            if (left[cind] == -1) {
                left[cind] = i;
            }
        }
        int pendingEnd = -1;
        String pending = "";
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i > pendingEnd && !pending.isEmpty()) {
                res.add(pending);
                pending = "";
            }
            char c = s.charAt(i);
            int cind = c - 'a';
            if (i == left[cind]) {
                int curRight = extendRight(i, right[cind], left, right, s);
                if (curRight != -1) {
                    pendingEnd = curRight;
                    pending = s.substring(i, curRight + 1);
                }
            }
        }
        if (!pending.isEmpty()) {
            res.add(pending);
            pending = "";
        }
        return res;
    }

    private int extendRight(int start, int end, int[] left, int[] right, String s) {

        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            int cind = c - 'a';
            if (left[cind] < start) {
                return -1;
            } else {
                end = Math.max(end, right[cind]);
            }
        }
        return end;
    }
}
