import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LengthOfLongestValidSubstring {
    public int longestValidSubstring(String word, List<String> forbidden) {
        Set<Long> fs = new HashSet<>();
        int flen = 0;
        for (String f : forbidden) {
            long code = 0;
            for (int i = f.length() - 1; i >= 0; --i) {
                int cind = f.charAt(i) - 'a' + 1;
                code = code * 31 + cind;
            }
            fs.add(code);
            flen = Math.max(flen, f.length());
        }
        int j = 0;
        int n = word.length();
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int start = Math.max(j, i - flen + 1);
            if (!walkbad(fs, start, i, word)) {
                // j...i good
                res = Math.max(res, i - j + 1);
            } else {
                j = start + 1;
                while (walkbad(fs, j, i, word)) {
                    ++j;
                }
            }
        }
        return res;
    }

    private boolean walkbad(Set<Long> fs, int start, int end, String word) {
        long code = 0;
        for (int i = end; i >= start; --i) {
            int cind = word.charAt(i) - 'a' + 1;
            code = code * 31 + cind;
            if (fs.contains(code)) {
                return true;
            }
        }
        return false;
    }
}
