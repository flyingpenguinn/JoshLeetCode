import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/*
LC#318
Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:

Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4
Explanation: The two words can be "ab", "cd".
Example 3:

Input: ["a","aa","aaa","aaaa"]
Output: 0
Explanation: No such pair of words.
 */
public class MaxProductWordLength {
    // use bit manipulation m[i]&m[j] to judge common word
    // at most 2^26 unique common words so the complexity can be decided solely on how many uniue common words we have
    // filter out shorter ones that share the same bit with the longer ones
    public int maxProduct(String[] a) {
        int n = a.length;
        Map<Long, Integer> m = new HashMap<>();
        long[] cm = new long[n];
        for (int i = 0; i < n; i++) {
            long code = code(a[i]);
            cm[i] = code;
            int ol = m.getOrDefault(code, 0);
            if (a[i].length() > ol) {
                m.put(code, a[i].length());
            }
        }
        int max = 0;
        for (long k1 : m.keySet()) {
            for (long k2 : m.keySet()) {
                if ((k1 & k2) == 0) {
                    max = Math.max(max, m.get(k1) * m.get(k2));
                }
            }
        }
        return max;
    }

    long code(String s) {
        int r = 0;
        for (int i = 0; i < s.length(); i++) {
            r |= (1 << (s.charAt(i) - 'a'));
        }
        return r;
    }
}
