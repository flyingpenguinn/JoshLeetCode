/*
LC#28
Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */

public class ImplementstrStr {
    // rabin karp pattern matching. assuming all lower case
    // find p in s
    public int strStr(String s, String p) {
        int k = p.length();
        if (k == 0) {
            return 0;
        }
        int base = 26;
        int mod = 1000000007;
        long phash = 0;
        for (int i = 0; i < p.length(); i++) {
            phash = phash * base + toint(p.charAt(i));
            phash %= mod;
        }
        long powbase = power(base, k - 1, mod);
        long hash = 0;
        for (int i = 0; i < s.length(); i++) {
            hash = hash * base + toint(s.charAt(i));
            hash %= mod;
            int start = i - k + 1;
            if (start >= 0) {
                if (hash == phash && match(s, i, p)) {
                    return start;
                }
                hash = (hash - toint(s.charAt(start)) * powbase) % mod;
                if (hash < 0) {
                    hash += mod;
                }
            }
        }
        return -1;

    }

    private long power(int base, int k, int mod) {
        if (k == 0) {
            return 1L;
        }
        long raw = base * power(base, k - 1, mod);
        return raw % mod;
    }

    private boolean match(String s, int i, String p) {
        int j = p.length() - 1;
        while (j >= 0) {
            if (p.charAt(j--) != s.charAt(i--)) {
                return false;
            }
        }
        return true;
    }

    int toint(char c) {
        return c - 'a';
    }

    public static void main(String[] args) {
        System.out.println(new StrStrKmp().strStr("abaabab", "abab"));
        System.out.println(new StrStrKmp().strStr("hello", "ll"));
        System.out.println(new StrStrKmp().strStr("mississippi", "pi"));
        System.out.println(new StrStrKmp().strStr("mississippi", "issippi"));

    }
}

class StrstrBruteForce {
    public int strStr(String s, String p) {
        if (p.isEmpty()) {
            return 0;
        }
        int plen = p.length();
        for (int i = 0; i + plen - 1 < s.length(); i++) {
            for (int j = 0; j < plen; j++) {
                if (s.charAt(i + j) != p.charAt(j)) {
                    break;
                }
                if (j == plen) {
                    return i;
                }
            }
        }
        return -1;
    }
}


class StrStrKmp {
    // kmp indexes from 0. strstr is similar to nexts method. change assignment to next array to k
    public int strStr(String s, String p) {
        int pn = p.length();
        int sn = s.length();
        if (pn == 0) {
            return 0;
        }
        if (sn < pn) {
            return -1;
        }
        int[] next = nexts(p);
        int k = 0;
        for (int i = 0; i < sn; i++) {
            char c = s.charAt(i);
            // here k is like next[i-1] in nexts method, keeping the char in pattern up to which i-1 can match with
            while (k > 0 && c != p.charAt(k)) {
                k = next[k - 1];
            }
            if (c == p.charAt(k)) {
                // if k==0 and equal, k would be 1
                k++;
                if (k == p.length()) {
                    return i - pn + 1;
                }
            }
        }
        return -1;
    }

    private int[] nexts(String s) {
        int n = s.length();
        int[] next = new int[n];
        next[0] = 0;// length of the longest prefix: 0...next[i] -1 == the prefix. the suffix ends at i
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            // abaaabab, macthing at the last b
            // when we mismatch with pos j, we try out next[j-1] it's a shorter prefix but may give new hope
            int j = next[i - 1];
            while (j > 0 && c != s.charAt(j)) {
                j = next[j - 1];
            }
            // if j==0 give a chance to match s[0]
            if (c == s.charAt(j)) {
                // if j==0 and equal, j would be 1
                next[i] = j + 1;
            }
        }
        return next;
    }

}