import java.util.HashSet;
import java.util.Set;

public class UniqueLength3PalindromeSubseq {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int res = 0;
        for (int j = 0; j < 26; j++) {
            Set<Integer> seen = new HashSet<>();
            Set<Integer> confirmed = new HashSet<>();
            int found = 0;
            for (int i = 0; i < n; i++) {
                int cind = s.charAt(i) - 'a';
                if (cind == j) {
                    if (found == 0) {
                        found++;
                    } else {
                        found++;
                        confirmed.addAll(seen);
                        seen = new HashSet<>();
                    }
                } else if (found > 0) {
                    seen.add(cind);
                }
            }
            if (found >= 3) {
                confirmed.add(j);
            }
            if (found >= 2) {
                //   System.out.println(j+" "+seen);
                res += confirmed.size();
            }
        }
        return res;
    }
}
