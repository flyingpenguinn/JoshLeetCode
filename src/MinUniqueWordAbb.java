/*
LC#411
A string such as "word" contains the following abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with the smallest possible length such that it does not conflict with abbreviations of the strings in the dictionary.

Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.

Note:
In the case of multiple answers as shown in the second example below, you may return any one of them.
Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
Examples:
"apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")

"apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").
 */
public class MinUniqueWordAbb {
    // gen abbreviations then compare with the dict words. prune if needed
    // overall it needs 2^m*n = 2^(logn +m) . we know the latter number is <=20
    private String min = null;
    private int minlen = 1000000;

    public String minAbbreviation(String t, String[] ds) {
        doa(t, 0, 0, ds, "", 0);
        return min;
    }

    void doa(String t, int i, int accu, String[] ds, String cur, int curlen) {
        if (min != null && curlen >= minlen) {
            return;
        }
        int n = t.length();
        if (i == n) {
            cur += accu > 0 ? accu : "";
            for (String d : ds) {
                if (hasconflict(cur, d)) {
                    return;
                }
            }
            if (curlen < minlen) {
                minlen = curlen;
                min = cur;
            }
            return;
        }
        doa(t, i + 1, accu + 1, ds, cur, curlen);
        cur += accu > 0 ? accu : "";
        curlen += accu > 0 ? 1 : 0;
        doa(t, i + 1, 0, ds, cur + t.charAt(i), curlen + 1);
    }

    boolean hasconflict(String abb, String d) {
        int i = 0;
        int j = 0;
        int accu = 0;
        while (i < abb.length() && j < d.length()) {
            char cabb = abb.charAt(i);
            if (Character.isDigit(cabb)) {
                accu = accu * 10 + (cabb - '0');
                i++;
            } else if (accu > 0) {
                j += accu;
                accu = 0;
            } else {
                if (cabb != d.charAt(j)) {
                    return false;
                }
                i++;
                j++;
            }
        }
        if (accu > 0) {
            j += accu;
        }
        return i == abb.length() && j == d.length();
    }

    public static void main(String[] args) {
        String[] dict = {"blade"};
        // String[] dict = {"aabaxaa", "aaxadaa"};
        System.out.println(new MinUniqueWordAbb().minAbbreviation("apple", dict));

    }
}