import java.util.Arrays;

/*
LC#1163
Given a string s, return the last substring of s in lexicographical order.



Example 1:

Input: "abab"
Output: "bab"
Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
Example 2:

Input: "leetcode"
Output: "tcode"


Note:

1 <= s.length <= 4 * 10^5
s contains only lowercase English letters.
 */
public class LastSubstringLexicographicalOrder {

    public String lastSubstring(String s) {
        int r = 0;
        int n = s.length();

        int i = 1;
        while (i < n) {
            if (s.charAt(i) > s.charAt(r)) {
                r = i;
            } else if (s.charAt(i) == s.charAt(r)) {
                int pr = r + 1;
                int pi = i + 1;
                while (pi < n) {
                    char ci = s.charAt(pi);
                    char cr = s.charAt(pr);
                    if (ci > cr) {
                        r = i;
                        break;
                    } else if (ci < cr) {
                        // key: when i is defeated, r....pr > i... pi. we should set i to pi+1 in the next round
                        // this is because any middle i2...pi < some middle r2.....pr
                        // note it won't happen that there is a better start after r and between i, because otherwise we would have known
                        i = pi;
                        break;
                    } else {
                        pi++;
                        pr++;
                    }
                }
                // r can hold i out till n, it's bigger than anything else
                if (pi == n) {
                    break;
                }
            }
            i++;
        }
        return s.substring(r);
    }


    public static void main(String[] args) {
        //   System.out.println(new LastSubstringLexicographicalOrder().lastSubstring("banana"));
        System.out.println(new LastSubstringLexicographicalOrder().lastSubstring("zzaazzbbzc"));

    }
}


//TLE... nlgn solution won't work...
class LastSubstringSuffixArray {
    // find biggest suffix. use suffix array
    class Entry implements Comparable<Entry> {
        int v1;
        int v2;
        // record the "i" in the algo, i.e. the starting point
        int start;

        public Entry(int v1, int v2, int start) {
            this.v1 = v1;
            this.v2 = v2;
            this.start = start;
        }

        @Override
        public int compareTo(Entry o) {
            if (v1 != o.v1) {
                return Integer.compare(v1, o.v1);
            } else {
                return Integer.compare(v2, o.v2);
            }
        }
    }

    public String lastSubstring(String s) {
        int n = s.length();
        int m = (int) (Math.log(n) / Math.log(2) + 1.5);
        int[][] p = new int[m + 1][n];
        Entry[] l = new Entry[n];

        for (int i = 0; i < n; i++) {
            int v = s.charAt(i) - 'a';
            //allocate initial rank to each char
            p[0][i] = v;
        }
        int step = 1;
        int max = -1;
        int maxi = -1;
        int buflen = Math.max(27, n + 1);
        int[] buf = new int[buflen];
        Entry[] r = new Entry[n];
        for (int len = 1; len <= n; len *= 2, step++) {
            for (int i = 0; i < n; i++) {
                // we have results for i..i+len-1 and i+len...i+2len-1 for previous step. now merge the scores for sorting
                // if overflow then minimal value -1
                l[i] = new Entry(p[step - 1][i], i + len < n ? p[step - 1][i + len] : -1, i);
            }
            // v2 first then v1 for two keys counting sort
            countingSort(l, buf, r, false);
            countingSort(l, buf, r, true);
            max = -1;
            maxi = -1;
            for (int i = 0; i < n; i++) {
                boolean same = i > 0 && l[i].compareTo(l[i - 1]) == 0;
                // either stay with before or a new one. start here can be inconsecutive but it's fine
                int index = l[i].start;
                p[step][index] = same ? p[step][l[i - 1].start] : i;
                if (p[step][index] > max) {
                    max = p[step][index];
                    maxi = index;
                }
            }
        }
        return s.substring(maxi);
    }


    private void countingSort(Entry[] l, int[] buf, Entry[] output, boolean usev1) {
        int n = l.length;
        Arrays.fill(buf, 0);
        // buf size n+1 = 0 to n, we map -1 to 0. we need 26 at least for coverage of the alphabet
        for (int i = 0; i < n; i++) {
            int rv = usev1 ? l[i].v1 + 1 : l[i].v2 + 1;
            buf[rv]++;
        }
        for (int i = 1; i < buf.length; i++) {
            buf[i] += buf[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            int rv = usev1 ? l[i].v1 + 1 : l[i].v2 + 1;
            output[buf[rv] - 1] = l[i];
            buf[rv]--;
        }
        for (int i = 0; i < n; i++) {
            l[i] = output[i];
        }
    }
}

