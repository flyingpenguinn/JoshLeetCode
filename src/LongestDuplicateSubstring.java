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
    // TODO: understand this suffix array better
    class SuffixArray {
        private int[] sa, lcp;
        private String s;

        SuffixArray(String s) {
            this.s = s;
            this.sa = buildSA(this.s);
            this.lcp = buildLCP(this.s, this.sa);
        }

        int[] getSA() {
            return this.sa;
        }

        int[] getLCP() {
            return this.lcp;
        }

        private void countSort(int k, int[] sa, int[] ra) {
            int n = sa.length, N = n + 256;
            int[] nsa = new int[n], cnt = new int[N];
            System.arraycopy(sa, 0, nsa, 0, n);
            for (int i = 0; i < n; i++) {
                cnt[ra[i]]++;
                nsa[i] = (nsa[i] - k + n) % n;
            }
            for (int i = 1; i < N; i++) cnt[i] += cnt[i - 1];
            for (int i = n - 1; i >= 0; i--) sa[--cnt[ra[nsa[i]]]] = nsa[i];
        }

        private int[] buildSAUTIL(String s) {
            int n = s.length();
            int[] sa = new int[n], ra = new int[n];
            for (int i = 0; i < n; i++) {
                sa[i] = i;
                ra[i] = s.charAt(i);
            }
            for (int k = 0; k < n; k = k != 0 ? k << 1 : k + 1) {
                int[] nra = new int[n];
                countSort(k, sa, ra);
                int r = 0;
                for (int i = 1; i < n; i++) {
                    if (ra[sa[i]] != ra[sa[i - 1]] || ra[(sa[i] + k) % n] != ra[(sa[i - 1] + k) % n]) r++;
                    nra[sa[i]] = r;
                }
                ra = nra;
            }
            return sa;
        }

        private int[] buildSA(String s) {
            s += '@';
            int[] sa = buildSAUTIL(s);
            return Arrays.copyOfRange(sa, 1, sa.length);
        }

        private int[] buildLCP(String s, int[] sa) {
            int n = s.length(), k = 0;
            int[] lcp = new int[n - 1], ra = new int[n];
            for (int i = 0; i < n; i++) ra[sa[i]] = i;
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
    }

    private String getLRS(String s) {
        int max = 0, start = 0, end = 0;
        int n = s.length();
        SuffixArray array = new SuffixArray(s);
        int[] sa = array.getSA(), lcp = array.getLCP();
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