import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReplaceQuestionMarkInString {
    public String minimizeStringValue(String s) {
        long[] c = new long[26];

        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) != '?') {
                int cind = s.charAt(i) - 'a';
                ++c[cind];
            }
        }
        char[] res = s.toCharArray();
        List<Character> marks = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '?') {
                long mindelta = (long) (1e18);
                int minj = -1;
                for (int j = 0; j < 26; ++j) {
                    long ov = c[j];
                    long nv = ov + 1;
                    long delta = nv * (nv - 1) / 2 - ov * (ov - 1) / 2;
                    if (delta < mindelta) {
                        mindelta = delta;
                        minj = j;
                    }
                }
                ++c[minj];
                marks.add((char) ('a' + minj));
            }
        }
        Collections.sort(marks);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            if (res[i] == '?') {
                res[i] = marks.get(j++);
            }
        }
        return new String(res);
    }
}
