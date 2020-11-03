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


class LongestDuplciateSubstringSuffixArray {
    // TODO: fix this suffix array solution. most of the code is copied from last substring in lexi order
    private int[] buildLCP(String s, int[] sa) {
        int n = s.length(), k = 0;
        int ranks = Math.max(n + 1, 256);
        int[] lcp = new int[n - 1], ra = new int[ranks];
        for (int i = 0; i < n; i++)
            ra[sa[i]] = i;
        for (int i = 0; i < n; i++) {
            if (ra[i] == n - 1) {
                k = 0;
                continue;
            }
            int j = sa[ra[i] + 1];
            while (i + k < n && j + k < n && s.charAt(i + k) == s.charAt(j + k)) k++;
            lcp[ra[i]] = k;
            if (k > 0) k--;
        }
        return lcp;
    }

    private void countingsort(int[] sa, int[] ra, int k) {
        int n = sa.length;
        int ranks = Math.max(n, 256);
        int[] count = new int[ranks];
        int[] nsa = new int[n];
        for (int i = 0; i < n; i++) {
            count[ra[i]]++;
            nsa[i] = k + 1 <= n ? (sa[i] - k) % n : sa[i];
            // when k>n we know this is the last sort we won't adjust nsa further
            if (nsa[i] < 0) {
                nsa[i] += n;
            }
        }
        for (int i = 1; i < ranks; i++) {
            count[i] += count[i - 1];
        }
        /*
        typical counting sort, scan from the back so that for same elements, the later ones ranked later too
        for (int i = n-1; i >=0; i--)
            p[--cnt[s[i]]] = i;
        here i == nsa[i] are indexes
        */
        for (int i = n - 1; i >= 0; i--) {
            sa[--count[ra[nsa[i]]]] = nsa[i];
        }
    }

    private int[] buildsa(String str) {
        str += '$';
        char[] s = str.toCharArray();
        int n = s.length;
        int[] sa = new int[n];
        int ranks = Math.max(n, 256);
        int[] ra = new int[ranks];
        for (int i = 0; i < n; i++) {
            sa[i] = i;
            ra[i] = s[i];
        }
        // k+1 is actually length of the substring we check
        // no fear of k>n because we mod and it's circular...
        for (int k = 0; (k + 1) / 2 <= n; k = (k == 0) ? k + 1 : k * 2) {
            countingsort(sa, ra, k);
            // counting sort for k/2. adjust sa if k<=n meaning we want to do another round of sorting for higher k/2
            if (k + 1 > n) {
                break;
            }
            // if k<=n, after this, sa is sorted as of lower k/2 using ra that is corresponding to k/2, but all sa[i] are adjusted with i-k/2. we then sort the renmaining "higher" k/2 to get ranking for k
            int[] nra = new int[ranks];
            int rank = 0;
            // sa[i-1] is what's before sa[i]. if they differ then we found a new rank note sa is already sorted by k/2  we are now calculating the ranking for k
            nra[sa[0]] = 0;
            for (int i = 1; i < n; i++) {
                // comparing the combined str at
                // sa[i], sa[i]+k vs sa[i-1], sa[i-1]+k. the former must have ranking >= the latter
                // ra[i] is the ranking of len k. sa[i] and sa[i-1] are already adjusted for k
                if (ra[sa[i]] != ra[sa[i - 1]] || ra[(sa[i] + k) % n] != ra[(sa[i - 1] + k) % n]) {
                    rank++;
                }
                nra[sa[i]] = rank;
            }
            ra = nra;
            // ra now has the right ranking for k
        }
        return sa;
    }

    private String getLRS(String s) {
        s += "$";
        int max = 0, start = 0, end = 0;
        int n = s.length();

        int[] sa = buildsa(s), lcp = buildLCP(s, sa);
        for (int i = 0; i < n - 1; i++) {
            int len = lcp[i];
            if (len > max) {
                max = len;
                start = sa[i];
                end = sa[i] + len;
            }
        }
        return s.substring(start, end);
    }

    public String longestDupSubstring(String s) {
        return s.isEmpty() ? "" : getLRS(s);
    }
}