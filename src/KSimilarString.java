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
        if (a.isEmpty() || b.isEmpty()) {
            return 0;
        }
        if (dp.containsKey(a)) {
            return dp.get(a);
        }
        if (a.charAt(0) == b.charAt(0)) {
            return dok(a.substring(1), b.substring(1));
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(0)) {
                StringBuilder sb = new StringBuilder(a);
                sb.setCharAt(i, a.charAt(0));
                sb.setCharAt(0, b.charAt(0));
                int cur = dok(sb.toString(), b) + 1;
                min = Math.min(cur, min);
            }
        }
        dp.put(a, min);
        return min;
    }
}



