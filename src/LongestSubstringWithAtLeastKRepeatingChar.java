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

    // key insight: we loop the big array once. if there are chars that dont show up >= k times
    // we take them out and do recursion on smaller ones
    // note there could be multiple such "hole"s but we just need to divide once- other holes will be divided later anyway
    // in avg O(nlgn)
    public int longestSubstring(String s, int k) {
        return doFind(s, k, 0, s.length() - 1);
    }

    private int doFind(String s, int k, int l, int u) {
        if (l > u) {
            return 0;
        }
        int[] map = new int[26];
        for (int i = l; i <= u; i++) {
            char c = s.charAt(i);
            map[c - 'a']++;
        }
        if (isGood(map, k)) {
            return u - l + 1;
        }
        for (int i = l; i <= u; i++) {
            if (map[s.charAt(i) - 'a'] < k) {
                int ll = doFind(s, k, l, i - 1);
                int lr = doFind(s, k, i + 1, u);
                return Math.max(ll, lr);
            }
        }
        return 0;
    }

    private boolean isGood(int[] map, int k) {
        for (int i = 0; i < map.length; i++) {
            if (map[i] != 0 && map[i] < k) {
                return false;
            }
        }
        return true;
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
                // note after low+= we may not catch each case of k distinct chars,
                // but we want the longest and at least k repeated, so what we get won't be worse than that
                count[sc[low++] - 'a']--;
            }
        }
        return max;
    }
}