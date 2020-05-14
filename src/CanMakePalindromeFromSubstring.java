import java.util.ArrayList;
import java.util.List;

public class CanMakePalindromeFromSubstring {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> r = new ArrayList<>();
        int[][] map = new int[s.length()][26];
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - 'a';
            map[i][c]++;
            if (i > 0) {
                for (int j = 0; j < 26; j++) {
                    map[i][j] += map[i - 1][j];
                }
            }
        }
        for (int[] q : queries) {
            int hi = q[1];
            int lo = q[0];
            int k = q[2];
            int oddcount = 0;
            // how many distinct chars are in odd count
            for (int j = 0; j < 26; j++) {
                int jc = map[hi][j] - (lo == 0 ? 0 : map[lo - 1][j]);
                if (jc % 2 == 1) {
                    oddcount++;
                }
            }
            // for each of the distinct chars we can turn one of them to another making both even
            int len = hi - lo + 1;
            boolean canconvert = false;
            // there are 2,4..(even) number of odd chars. like 3,5,7,9. turn 7,9 to 6,8 and 3,5 to 4,6
            if (len % 2 == 0) {
                if (oddcount / 2 <= k) {
                    canconvert = true;
                }
            } else {
                // there are odd number of odd char. like 3,5,7. turn 3,7 to 4,6 and 5 can be left alone
                if ((oddcount - 1) / 2 <= k) {
                    canconvert = true;
                }
            }
            r.add(canconvert);
        }
        return r;
    }
}
