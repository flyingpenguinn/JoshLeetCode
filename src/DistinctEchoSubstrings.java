import java.util.HashSet;
import java.util.Set;

/*
LC#1316
Return the number of distinct non-empty substrings of text that can be written as the concatenation of some string with itself.



Example 1:

Input: text = "abcabcabc"
Output: 3
Explanation: The 3 substrings are "abcabc", "bcabca" and "cabcab".
Example 2:

Input: text = "leetcodeleetcode"
Output: 2
Explanation: The 2 substrings are "ee" and "leetcodeleetcode".
s

Constraints:

1 <= text.length <= 2000
text has only lowercase English letters.
 */
public class DistinctEchoSubstrings {
    long mod = 100000000000007L;

    public int distinctEchoSubstrings(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        Set<Long> set = new HashSet<>();
        long base = 1L;
        for (int l = 1; 2 * l - 1 < n; l++) {
            base *= 26;
            base %= mod;
            long hash1 = getinitialhash(cs, 0, l - 1);
            long hash2 = getinitialhash(cs, l, 2 * l - 1);
            if (hash1 == hash2 && doesEqual(cs, 0, l, l)) {
                set.add(hash1);
            }
            for (int i = 1; i + 2 * l - 1 < n; i++) {
                hash1 = getHash(cs, hash1, base, i - 1, i + l - 1);
                hash2 = getHash(cs, hash2, base, i - 1 + l, i + 2 * l - 1);
                if (hash1 == hash2 && doesEqual(cs, i, i + l, l)) {
                    set.add(hash1);
                }
            }
        }
        return set.size();
    }

    private long getinitialhash(char[] cs, int i, int j) {
        long hash = 0L;
        for (int k = i; k <= j; k++) {
            hash = hash * 26 + (tocode(cs[k]));
            hash %= mod;
        }
        return hash;
    }

    private long getHash(char[] cs, long hash, long headexp, int index1, int index2) {
        hash = hash * 26 + (tocode(cs[index2]));
        hash = (hash - headexp * tocode(cs[index1])) % mod;
        if (hash < 0) {
            hash += mod;
        }
        return hash;
    }

    // don't really need this on OJ but more mathematically sound
    private boolean doesEqual(char[] cs, int i, int j, int l) {
        for (int k = 0; k < l; k++) {
            if (cs[i + k] != cs[j + k]) {
                return false;
            }
        }
        return true;
    }

    private int tocode(char c) {
        return c - 'a' + 1;
        // can't be c-a, otherwies aaaa and aa can't be distinguished
    }
}
