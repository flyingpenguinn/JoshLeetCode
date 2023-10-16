import java.util.HashMap;
import java.util.Map;

public class ShortestAndLexicographiccallySmallestString {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = s.charAt(i) - '0';
        }
        Map<Integer, Integer> ones = new HashMap<>();
        int cstart = -1;
        int cend = -1;
        int accu = 0;
        ones.put(0, -1);
        for (int i = 0; i < n; ++i) {
            accu += a[i];
            int t = accu - k;
            if (ones.containsKey(t)) {
                int prev = ones.get(t);
                int start = prev + 1;
                if (smaller(a, start, i, cstart, cend)) {
                    cstart = start;
                    cend = i;
                }
            }
            ones.put(accu, i);
        }
        if (cstart == -1) {
            return "";
        } else {
            return s.substring(cstart, cend + 1);
        }
    }

    private boolean smaller(int[] a, int i1, int j1, int i2, int j2) {
        if (i2 == -1) {
            return true;
        }
        if (j1 - i1 < j2 - i2) {
            return true;
        }
        if (j1 - i1 > j2 - i2) {
            return false;
        }
        int k = i1;
        int p = i2;
        while (k <= j1 && p <= j2) {
            if (a[k] > a[p]) {
                return false;
            }
            if (a[k] < a[p]) {
                return true;
            }
            ++k;
            ++p;
        }
        return true;
    }
}
