import base.ArrayUtils;

import java.util.*;

/*
LC#354
You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class RussianDollEnvelopes {
    // key is to put bigger height first so that if we see a smaller height we found an answer
    // rest is the same as 1d longest increasing subsequence, as if applied on height
    // can be extended to higher dimension too
    public int maxEnvelopes(int[][] a) {
        int n = a.length;
        Arrays.sort(a, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return Integer.compare(o1[0], o2[0]);
                } else {
                    // bigger first! in this way
                    // if prev width < then good
                    // otherwise we will skip all width == and go for a width <
                    return Integer.compare(o2[1], o1[1]);
                }
            }
        });
        List<Integer> list = new ArrayList<>();
        int max = 0;
        // find the best insertion point of height
        for (int i = 0; i < n; i++) {
            int pos = Collections.binarySearch(list, a[i][1]);
            if (pos >= 0) {
                continue;// dont need to change anything
            } else {
                int insertion = -(pos + 1);
                if (insertion < list.size()) {
                    list.set(insertion, a[i][1]);
                } else {
                    list.add(a[i][1]);
                }
            }
        }
        return list.size();
    }
}
