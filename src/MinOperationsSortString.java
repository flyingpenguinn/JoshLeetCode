import java.util.Arrays;

public class MinOperationsSortString {
    public int minOperations(String s) {
        int n = s.length();
        if (n == 1) {
            return 0;
        }
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        String sorted = new String(cs);
        if (s.equals(sorted)) {
            return 0;
        }
        if (n == 2) {
            return -1;
        }
        char minc = cs[0];
        if (minc == s.charAt(0)) {
            return 1;
        }
        char maxc = cs[n - 1];
        if (maxc == s.charAt(n - 1)) {
            return 1;
        }
        for (int i = 0; i < n - 1; ++i) {
            if (s.charAt(i) == minc) {
                return 2;
            }
        }
        for (int i = n - 1; i > 0; --i) {
            if (s.charAt(i) == maxc) {
                return 2;
            }
        }
        // like 3,2,1. -> 3,1,2,1,3,2,1,2,3, 3 steps
        return 3;
    }
}
