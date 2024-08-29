import base.ArrayUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountAlmostEqualPairsII {
    // pad every number
    // brute force using while loop on the indexes we need to change. note it's two nested steps so we can use a loop
    // then check which of the transforms hit previous numbers
    private int maxDigit = 0;

    public int countPairs(int[] a) {
        int n = a.length;
        Map<String, Integer> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            String sa = String.valueOf(a[i]);
            maxDigit = Math.max(maxDigit, sa.length());
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            String stra = String.valueOf(a[i]);
            stra = pad(stra);
            Set<String> cand = find(a[i]);
            for (String c : cand) {
                int prev = m.getOrDefault(c, 0);
                res += prev;
            }
            m.put(stra, m.getOrDefault(stra, 0) + 1);
        }
        return res;
    }

    private void swap(char[] c, int i, int j) {
        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }

    private Set<String> find(int n) {
        Set<String> res = new HashSet<>();
        String strn = String.valueOf(n);
        strn = pad(strn);
        res.add(strn);
        char[] c = strn.toCharArray();
        for (int i = 0; i < maxDigit; ++i) {
            for (int j = i + 1; j < maxDigit; ++j) {
                swap(c, i, j);
                res.add(new String(c));
                for (int k = 0; k < maxDigit; ++k) {
                    for (int p = k + 1; p < maxDigit; ++p) {
                        swap(c, k, p);
                        res.add(new String(c));
                        swap(c, k, p);
                    }
                }
                swap(c, i, j);
            }
        }
        return res;
    }

    private String pad(String strn) {
        while (strn.length() < maxDigit) {
            strn = "0" + strn;
        }
        return strn;
    }

    public static void main(String[] args) {
        System.out.println(new CountAlmostEqualPairsII().countPairs(ArrayUtils.read1d("[123, 312]")));
    }
}
