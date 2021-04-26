import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtLeastKRepeatingChar {

        System.out.println(new LongestSubstringWithAtleastKRepeatingTwoPointer().longestSubstring("dbaaabcb", 3));
}
    // in l...u, bad chars will never have a chance. we need to weed them out.
    // so we dig them out and recursion on smaller ones split by them
    // because we throw away one char at every level it's O(26n)
    public int longestSubstring(String s, int k) {
        return solve(s, 0, s.length() - 1, k);
    }

    private int solve(String s, int start, int end, int k) {
        int[] count = new int[26];
        for (int i = start; i <= end; i++) {
            count[s.charAt(i) - 'a']++;
        }
        boolean[] bad = new boolean[26];
        boolean foundbad = false;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0 && count[i] < k) {
                foundbad = true;
                bad[i] = true;
            }
        }
        if (!foundbad) {
            return end - start + 1;
        }
        int j = -1;
        int res = 0;
        for (int i = start; i <= end; i++) {
            int cind = s.charAt(i) - 'a';
            if (bad[cind] && j != -1) {
                int cur = solve(s, j, i - 1, k);
                res = Math.max(cur, res);
                j = -1;
            } else if (!bad[cind] && j == -1) {
                j = i;
            }
        }
        if (j != -1) {
            int cur = solve(s, j, end, k);
            res = Math.max(cur, res);
        }
        return res;
    }
}

class LongestSubstringWithAtleastKRepeatingTwoPointer {
    // for each at most k chars, check the frequencies. turning this question to at most k distinct chars with freq check

    public int longestSubstring(String s, int target) {
        int max = 0;
        for (int i = 1; i <= 26; i++) {
            max = Math.max(max, domax(s, i, target));
        }
        return max;
    }

    // involve at most k chars, each with target appearances
    private int domax(String s, int k, int target) {
        int n = s.length();
        int[] count = new int[26];
        int start = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int cind = s.charAt(i) - 'a';
            count[cind]++;
            int cur = 0;
            for (int j = 0; j < 26; j++) {
                if (count[j] > 0) {
                    cur++;
                }
            }
            while (cur > k) {
                int sind = s.charAt(start) - 'a';
                count[sind]--;
                if (count[sind] == 0) {
                    cur--;
                }
                start++;
            }
            boolean good = true;
            for (int j = 0; j < 26; j++) {
                if (count[j] > 0 && count[j] < target) {
                    good = false;
                    break;
                }
            }
            if (good) {
                // at most k chars. what if we only need less chars? we have covered it in a lower k
                res = Math.max(res, i - start + 1);
            }
        }
        return res;
    }
}