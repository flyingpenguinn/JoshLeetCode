import java.util.*;

/*
LC#854
Strings A and B are K-similar (for some non-negative integer K) if we can swap the positions of two letters in A exactly K times so that the resulting string equals B.

Given two anagrams A and B, return the smallest K for which A and B are K-similar.

Example 1:

Input: A = "ab", B = "ba"
Output: 1
Example 2:

Input: A = "abc", B = "bca"
Output: 2
Example 3:

Input: A = "abac", B = "baca"
Output: 2
Example 4:

Input: A = "aabc", B = "abca"
Output: 2
Note:

1 <= A.length == B.length <= 20
A and B contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}
 */
public class KSimilarString {

    // every time we try to put one char in place. we could have multiple choices.
    // similar to couples holding hands, but there we only have one choice
    Map<String, Integer> dp = new HashMap<>();

    public int kSimilarity(String a, String b) {
        return dok(a, b);
    }

    private int dok(String a, String b) {
        if (dp.containsKey(a)) {
            return dp.get(a);
        }
        int n = a.length();
        int i = 0;
        while (i < n && a.charAt(i) == b.charAt(i)) {
            i++;
        }
        if (i == n) {
            return 0;
        }
        char ca = a.charAt(i);
        char cb = b.charAt(i);
        int min = Integer.MAX_VALUE;
        for (int j = i + 1; j < n; j++) {
            if (a.charAt(j) == cb) {
                StringBuilder sb = new StringBuilder(a);
                sb.setCharAt(j, ca);
                sb.setCharAt(i, cb);
                int cur = dok(sb.toString(), b);
                min = Math.min(min, cur);
            }
        }
        int rt = min + 1;
        dp.put(a, rt);
        return rt;
    }
}



