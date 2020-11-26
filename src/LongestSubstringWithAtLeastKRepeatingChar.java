import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtLeastKRepeatingChar {

    // key insight: when we are at 0...i, if it's all >=k, great. otherwise we try to get rid of <k chars totally
    // we get rid of < k chars by finding out their last position. we must start from max(these last position)
    // finally check after getting rid of all of them, do we have a good k repeating substring
    public int longestSubstring(String s, int target) {
        char[] sc = s.toCharArray();
        int n = sc.length;
        int[][] count = new int[n][26];
        int[] last = new int[26];
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                count[i][j] = (i == 0 ? 0 : count[i - 1][j]);
            }
            count[i][sc[i] - 'a']++;
            last[sc[i] - 'a'] = i;
            int maxpi = -1;
            for (int j = 0; j < 26; j++) {
                if (count[i][j] > 0 && count[i][j] < target) {
                    int li = last[j];
                    maxpi = Math.max(maxpi, li);
                }
            }
            if (maxpi == -1) {
                max = i + 1;
            } else {
                boolean good = true;
                for (int j = 0; j < 26; j++) {
                    if (count[i][j] - count[maxpi][j] > 0 && count[i][j] - count[maxpi][j] < target) {
                        good = false;
                        break;
                    }
                }
                if (good) {
                    max = Math.max(max, i - maxpi);
                }
            }
        }
        return max;
    }


    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithAtleastKRepeatingTwoPointer().longestSubstring("dbaaabcb", 3));
        System.out.println(new LongestSubstringWithAtleastKRepeatingTwoPointer().longestSubstring("dacbcabacbd", 3));
    }
}

class LongestSubstringAtleastKrepeatingDivideAndConquer {
    // in l...u, bad chars will never have a chance. we need to weed them out.
    // so we dig them out and recursion on smaller ones split by them
    // because we throw away one char at every level it's O(26n)
    private int[][] m;

    public int longestSubstring(String s, int k) {
        int n = s.length();
        m = new int[n][26];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                m[i][j] = (i == 0 ? 0 : m[i - 1][j]);
            }
            int cind = s.charAt(i) - 'a';
            m[i][cind]++;
        }
        return dolong(s, 0, n - 1, k);
    }

    private int dolong(String s, int l, int u, int k) {
        if (u - l + 1 < k) {
            return 0;
        }
        if (l > u) {
            return 0;
        }
        int bad = -1;
        for (int i = 0; i < 26; i++) {
            int count = m[u][i] - (l == 0 ? 0 : m[l - 1][i]);
            if (count == 0) {
                continue;
            }
            if (count < k) {
                bad = i;
                break;
            }
        }
        if (bad == -1) {
            return u - l + 1;
        }
        int last = l - 1;
        int res = 0;
        for (int i = l; i <= u; i++) {
            int cind = s.charAt(i) - 'a';
            if (cind == bad) {
                int cur = dolong(s, last + 1, i - 1, k);
                res = Math.max(res, cur);
                last = i;
            }
        }
        int stub = dolong(s, last + 1, u, k);
        res = Math.max(res, stub);
        return res;
    }
}

class LongestSubstringWithAtleastKRepeatingTwoPointer {
    // for each exactly k chars, check the frequencies. turning this question to at most k distinct chars with freq check

    public int longestSubstring(String s, int target) {
        int max = 0;
        for (int i = 1; i < 26; i++) {
            max = Math.max(max, domax(s, i, target));
        }
        return max;
    }

    // involve k chars, each with target appearances
    private int domax(String s, int k, int target) {
        char[] sc = s.toCharArray();
        int high = -1;
        int low = 0;
        int[] count = new int[26];
        int max = 0;
        while (true) {
            int cur = 0;
            boolean good = true;
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    cur++;
                    if (count[i] < target) {
                        good = false;
                    }
                }
            }
            if (cur <= k) {
                if (cur == k && good) {
                    max = Math.max(max, high - low + 1);
                }
                high++;
                if (high == s.length()) {
                    break;
                }
                count[sc[high] - 'a']++;
            } else {
                // note after low++ we may not catch each case of k distinct chars,but we want the longest and at least targeted repeated, so smaller ones won't count anyway
                // this is also to say that if we want longest substring where we have at most k distinct chars, we can use two pointer
                // note this is different from counting all subarrays with k distinct chars. that's #992 and can be done by atmost(m)- atmost(m-1)
                count[sc[low++] - 'a']--;
            }
        }
        return max;
    }
}