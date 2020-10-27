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
    public String longestDupSubstring(String s) {
        int l = 1;
        int u = s.length();
        int lastgood = -1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int res = good(s, mid);
            if (res >= 0) {
                lastgood = res;
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        if (u > 0) {
            return s.substring(lastgood, lastgood + u);
        }
        return "";
    }

    long HashMod = 1000000000000007L;
    int prime = 31;

    private int good(String s, int len) {
        long hash = 0;
        Set<Long> seen = new HashSet<>();
        long base = 1;
        for (int i = 0; i < len - 1; i++) {
            hash = hash * prime + (s.charAt(i) - 'a');
            hash %= HashMod;
            base *= prime;
            base %= HashMod;
        }
        for (int i = len - 1; i < s.length(); i++) {
            hash = hash * prime + (s.charAt(i) - 'a');
            hash %= HashMod;
            if (seen.contains(hash)) {
                return i - len + 1;
            }
            seen.add(hash);
            hash = hash - base * (s.charAt(i - len + 1) - 'a');
            if (hash < 0) {
                hash = hash + prime * HashMod;
            }
            hash = hash % HashMod;
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
