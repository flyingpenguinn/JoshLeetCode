import java.util.Arrays;

public class FindIndexOfFirstOccurInString {
    public int strStr(String str, String pattern) {
        int[] lps = computeLps(pattern);
        //System.out.println(Arrays.toString(lps));
        int n = str.length();
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();
        int len = 0;
        for (int i = 0; i < n; ++i) {
            while (len > 0 && s[i] != p[len]) {
                len = lps[len - 1];
            }
            if (s[i] == p[len]) {
                ++len;
            }
            if (len == p.length) {
                return i - len + 1;
            }
        }
        return -1;
    }

    private int[] computeLps(String pattern) {
        int n = pattern.length();
        char[] p = pattern.toCharArray();
        int[] lps = new int[n];
        lps[0] = 0; // lps[0] is always 0
        int len = 0;

        for (int i = 1; i < n; ++i) {
            while (len > 0 && p[len] != p[i]) {
                len = lps[len - 1];
            }
            if (p[len] == p[i]) {
                ++len;
            }
            lps[i] = len;
        }
        return lps;
    }

    public static void main(String[] args) {
        System.out.println(new FindIndexOfFirstOccurInString().strStr("aaaa", "bba"));
    }
}
