import java.util.*;

public class MovePiecesToMakeString {
    // s's L indexes must be bigger, R indexes must be smaller than t
    public boolean canChange(String ss, String tt) {
        int i = 0;
        int j = 0;
        int sn = ss.length();
        int tn = tt.length();
        char[] s = ss.toCharArray();
        char[] t = tt.toCharArray();
        while (i < sn || j < tn) {
            while (i < sn && s[i] == '_') {
                ++i;
            }
            while (j < tn && t[j] == '_') {
                ++j;
            }
            if (i == sn && j == tn) {
                break;
            } else if (i == sn || j == tn) {
                return false;
            }
            if (s[i] != t[j]) {
                return false;
            } else if (s[i] == 'L' && t[j] == 'L') {
                if (i < j) {
                    return false;
                }
            } else if (s[i] == 'R' && t[j] == 'R') {
                if (i > j) {
                    return false;
                }
            }
            ++i;
            ++j;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new MovePiecesToMakeString().canChange(
                "R__L", "_LR_"));
    }
}
