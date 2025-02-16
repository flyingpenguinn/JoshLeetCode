import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SelectKDisjointSpecialSubstrings {
    // assume each char's first appearance is the start, figure out the intervals
    private int[] last = new int[26];
    private int[] first = new int[26];

    public boolean maxSubstringLength(String s, int k) {
        int n = s.length();
        Arrays.fill(first, -1);
        char[] c = s.toCharArray();
        for (int i = 0; i < n; ++i) {
            int cind = c[i] - 'a';
            first[cind] = first[cind] == -1 ? i : first[cind];
            last[cind] = i;
        }
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int cind = c[i] - 'a';
            if (i != first[cind]) {
                continue;
            }
            int realend = last[cind];
            boolean bad = false;
            for (int j = first[cind]; j <= last[cind]; ++j) {
                int jind = c[j] - 'a';
                if (first[jind] < first[cind]) {
                    bad = true;
                    break;
                }
                realend = Math.max(realend, last[jind]);
            }
            if (!bad && !(first[cind] == 0 && realend == n - 1)) {

                list.add(new int[]{first[cind], realend});
            }
        }
        Collections.sort(list, (x, y) -> Integer.compare(x[1], y[1]));
        if(list.isEmpty()){
            return k==0;
        }
        int segs = 1;
        int start = list.get(0)[0];
        int end = list.get(0)[1];
        for (int i = 1; i < list.size(); ++i) {
            if (list.get(i)[0] > end) {
                ++segs;
                end = list.get(i)[1];
            }
        }
        return segs >= k;
    }
}
