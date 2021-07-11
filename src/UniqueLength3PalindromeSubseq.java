import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UniqueLength3PalindromeSubseq {
    // for each char, first and last pos. then count unique chars in between
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[] first = new int[26];
        Arrays.fill(first, -1);
        int[] last = new int[26];
        for (int i = 0; i < n; i++) {
            int cind = s.charAt(i) - 'a';
            if (first[cind] == -1) {
                first[cind] = i;
            }
            last[cind] = i;
        }
        int res = 0;
        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) {
                continue;
            }
            Set<Character> seen = new HashSet<>();
            for (int j = first[i]+1; j <= last[i]-1; j++) {
                seen.add(s.charAt(j));
            }
            int cur = seen.size();
            res += cur;

        }
        return res;
    }
}
