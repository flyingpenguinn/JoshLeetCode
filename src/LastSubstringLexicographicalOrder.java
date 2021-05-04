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
    // duval algo. rule out impossible as r
    // if l+m > r+m, then we know given r+ any in [0...m] there is a bigger l+ any in [0...m] > it, so anything from r to r+m won't be qualified as starting point. so we skip them
    // note l+m can be bigger than l yes but we will figure it out later
    // if l+m < r+m, then we know similarly anything between l...any in [0...m] < r+ any in [0...m] so i should be l+m+1.
    // but anything before r is already defeated by this l, so we can move further
    public String lastSubstring(String input) {
        int l = 0;
        int r = 1;
        int i = 0;
        int n = input.length();
        char[] s = input.toCharArray();
        while (l + i < n && r + i < n) {
            if (s[l + i] == s[r + i]) {
                i++;
            } else if (s[l + i] > s[r + i]) {
                r = r + i + 1;
                i = 0;
            } else {
                l = Math.max(l + i + 1, r);
                r = l + 1;
                i = 0;
            }
        }
        return input.substring(l);
    }


    public static void main(String[] args) {
        //   System.out.println(new LastSubstringLexicographicalOrder().lastSubstring("banana"));
        System.out.println(new LastSubstringLexicographicalOrder().lastSubstring("cacacacb"));

    }
}


class LastSubstringSuffixArray {
    // nlogn suffix array solution
    // actually sorting all circular substrings of s+"$"
    // https://cp-algorithms.com/string/suffix-array.html
    // sa[i] means the ith suffix starts with sa[i]
    // ra[i] means the rank of the suffix starting with i
    // count[i] counts accumulative sums of possible ra[i] values
    // nsa, nra are tmp copies of them.
    // use counting sort to sort sa in logn times

    // counting sort using ra and get result in sa. we have the suffix array for k/2. now we need to get k sorted according to the "lower" k/2
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

    public String lastSubstring(String s) {
        s += "$";
        int[] sa = buildsa(s);
        return s.substring(sa[s.length()], s.length() - 1);
        //real length is s.len+1 after + $ so this index is correct
    }
}

