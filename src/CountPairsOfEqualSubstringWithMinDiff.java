import java.util.Arrays;

public class CountPairsOfEqualSubstringWithMinDiff {
    // always single point in both arrays: if we move j it gets further from a
    public int countQuadruples(String s1, String s2) {
        int[] l1 = new int[26];
        Arrays.fill(l1, -1);
        for (int i = s1.length() - 1; i >= 0; i--) {
            l1[s1.charAt(i) - 'a'] = i;
        }
        int mindiff = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < s2.length(); i++) {
            int s2ind = s2.charAt(i) - 'a';
            int vj = l1[s2ind];
            if (vj == -1) {
                continue;
            }
            int diff = vj - i;
            if (mindiff > diff) {
                mindiff = diff;
                res = 1;
            } else if (mindiff == diff) {
                res++;
            }

        }
        return res;
    }
}
