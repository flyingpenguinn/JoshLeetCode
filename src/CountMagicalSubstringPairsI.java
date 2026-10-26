import java.util.HashMap;
import java.util.Map;

public class CountMagicalSubstringPairsI {
    private void update(Map<Integer, Integer> m, int k, int d) {
        int nv = m.getOrDefault(k, 0) + d;
        if (nv <= 0) {
            m.remove(k);
        } else {
            m.put(k, nv);
        }
    }

    public int countMagicalPairs(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int res = 0;
        for (int j = 0; j < n; ++j) {
            Map<Integer, Integer> left = new HashMap<>();
            int[] lcnt = new int[27];
            for (int i = j; i >= 0; --i) {
                int iind = c[i] - 'a';
                update(left, iind, 1);
                int cdistleft = left.size();                
                ++lcnt[cdistleft];
            }
            Map<Integer, Integer> right = new HashMap<>();

            for (int k = j + 1; k < n; ++k) {
                int kind = c[k] - 'a';
                update(right, kind, 1);
                int cdistright = right.size();
                int leftcount = lcnt[cdistright];
                res += leftcount;
            }
        }
        return res;
    }
}
