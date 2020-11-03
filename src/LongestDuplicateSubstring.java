import java.io.*;
import java.util.*;

/*
LC#1044
Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)

Return any duplicated substring that has the longest possible length.  (If S does not have a duplicated substring, the answer is "".)



Example 1:

Input: "banana"
Output: "ana"
Example 2:

Input: "abcd"
Output: ""


Note:

2 <= S.length <= 10^5
S consists of lowercase English letters.
 */
public class LongestDuplicateSubstring {
    // binary search on len then rolling hash
    // because if a longer len works, smaller ones will work too
    // TODO: use suffix array and lcp
    public String longestDupSubstring(String s) {
        int l = 1;
        int u = s.length() - 1;
        int lastend = -1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int end = hasdup(s, mid);
            if (end != -1) {
                lastend = end;
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return lastend == -1 ? "" : s.substring(lastend - u + 1, lastend + 1);
    }

    private int toint(char c) {
        return c - 'a' + 1;
    }

    private long mod = 1000000007L;
    private long base = 31L;

    private int hasdup(String s, int len) {
        Map<Long, List<Integer>> m = new HashMap<>();
        long hash = 0;
        long topbase = 1;

        for (int i = 0; i < s.length(); i++) {
            hash = hash * base + toint(s.charAt(i));
            hash %= mod;
            int head = i - len + 1;
            if (head >= 0) {
                if (m.containsKey(hash)) {
                    List<Integer> ends = m.get(hash);
                    for (int end : ends) {
                        if (s.substring(end - len + 1, end).equals(s.substring(head, i))) {
                            return end;
                        }
                    }
                }
                m.computeIfAbsent(hash, k -> new ArrayList<>()).add(i);
                hash -= topbase * toint(s.charAt(head));
                hash %= mod;
                if (hash < 0) {
                    hash += mod;
                }
            } else {
                topbase *= base;
                topbase %= mod;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        String file = "E:\\dev\\project\\JoshLeet\\tests\\longestdupsubstring.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String s = reader.readLine();
        System.out.println(new LongestDuplicateSubstring().longestDupSubstring("aaaabbb"));

    }
}
