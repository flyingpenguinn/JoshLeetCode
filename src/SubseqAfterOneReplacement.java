import java.util.Arrays;

public class SubseqAfterOneReplacement {
    public boolean canMakeSubsequence(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        if (sn == 1) {
            return true;
        }
        int[] dp1 = new int[sn];
        Arrays.fill(dp1, tn + 1);
        int j = 0;
        for (int i = 0; i < tn && j < sn; ++i) {
            if (s.charAt(j) == t.charAt(i)) {
                dp1[j] = i;
                ++j;
            }
        }
        if (j == sn) {
            return true;
        }
        j = sn - 1;
        int[] dp2 = new int[sn];
        Arrays.fill(dp2, -1);
        for (int i = tn - 1; i >= 0 && j >= 0; --i) {
            if (s.charAt(j) == t.charAt(i)) {
                dp2[j] = i;
                --j;
            }
        }
        for (int i = 0; i < sn; ++i) {
            if (i == 0) {
                if (dp2[i + 1] > 0) {
                    return true;
                }
            } else if (i == sn - 1) {
                if (dp1[i - 1] < tn - 1) {
                    return true;
                }
            } else {
                if (dp1[i - 1] < dp2[i + 1] - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
