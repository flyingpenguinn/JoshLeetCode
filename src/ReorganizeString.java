import java.util.*;

/*
LC#767
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
Note:

S will consist of lowercase letters and have length in range [1, 500].
 */
public class ReorganizeString {
    // almost same as task scheduler, interval is 1 here while in that problem it's k
    public String reorganizeString(String s) {
        int intv = 1;
        int[][] f = new int[26][2];
        for (int i = 0; i < 26; i++) {
            f[i][0] = i;
        }
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int cind = s.charAt(i) - 'a';
            f[cind][1]++;
        }
        StringBuilder sb = new StringBuilder();
        while (!allzero(f)) {
            Arrays.sort(f, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o2[1], o1[1]);
                }
            });
            int cg = 0;
            for (int i = 0; i < 26; i++) {
                if (f[i][1] == 0) {
                    continue;
                }
                //   System.out.println(f[i][0]+" "+f[i][1]);
                sb.append((char) ('a' + f[i][0]));
                f[i][1]--;
                cg++;
                if (cg == intv + 1) {
                    break;
                }
            }
            if (!allzero(f) && cg < intv + 1) {
                return "";
            }
        }
        return sb.toString();
    }

    boolean allzero(int[][] f) {
        for (int i = 0; i < 26; i++) {
            if (f[i][1] > 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        ReorganizeString rs = new ReorganizeString();

        System.out.println("[" + rs.reorganizeString("aab") + "]");
/*
        System.out.println("[" + rs.reorganizeString("aab") + "]");
        System.out.println("[" + rs.reorganizeString("aaab") + "]");
        System.out.println("[" + rs.reorganizeString("aabbbccd") + "]");
        */

    }
}