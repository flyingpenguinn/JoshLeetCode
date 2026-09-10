package base;

import java.util.ArrayList;
import java.util.List;

public class KmpTemplate {
    public List<Integer> kmpSearch(String s, String a) {
        List<Integer> indices = new ArrayList<>();
        int[] lps = computeLPSArray(a);

        int j = 0; // index for a (pattern)
        int i = 0; // index for s (text)
        while (i < s.length()) {
            if (a.charAt(j) == s.charAt(i)) {
                j++;
                i++;
            }
            if (j == a.length()) {
                indices.add(i - j);
                j = lps[j - 1];
            } else if (i < s.length() && a.charAt(j) != s.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i = i + 1;
                }
            }
        }
        return indices;
    }

    private int[] computeLPSArray(String a) {
        int[] lps = new int[a.length()];
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        while (i < a.length()) {
            if (a.charAt(i) == a.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
        return lps;
    }
}
