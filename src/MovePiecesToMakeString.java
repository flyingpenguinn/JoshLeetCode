import java.util.*;

public class MovePiecesToMakeString {
    // s's L indexes must be bigger, R indexes must be smaller than t
    public boolean canChange(String start, String target) {
        // relative order must be the same
        if (!(start.replaceAll("_", "")).equals(target.replaceAll("_", ""))) {
            return false;
        }
        int n = start.length();
        int i = 0;
        int j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == '_') {
                ++i;
            }
            while (j < n && target.charAt(j) == '_') {
                ++j;
            }
            if (i < n && j < n && start.charAt(i) != target.charAt(j)) {
                return false;
            }
            if (i < n && j < n && (start.charAt(i) == 'L' && i < j || target.charAt(j) == 'R' && i > j)) {
                return false;
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
