import java.util.Arrays;

public class MinDeletionsForAtmostKDistinctChars {
    public int minDeletion(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            ++cnt[c - 'a'];
        }
        Arrays.sort(cnt);
        int rem = 0;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0) {
                ++rem;
            }
        }
        int res = 0;
        for (int i = 0; i < 26 && rem > k; ++i) {
            if (cnt[i] == 0) {
                continue;
            }
            res += cnt[i];
            --rem;
        }
        return res;
    }
}
