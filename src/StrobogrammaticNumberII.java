import java.util.ArrayList;
import java.util.List;

/*
LC#247
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:

Input:  n = 2
Output: ["11","69","88","96"]
 */
public class StrobogrammaticNumberII {
    // trap: can't start with 0 when len>1. when len==1 we can always start with 0
    int[] m = {0, 1, -2, -3, -4, -5, 9, -7, 8, 6};
    List<String> r = new ArrayList<>();

    public List<String> findStrobogrammatic(int n) {
        if (n % 2 == 1) {
            for (int i = 0; i <= 9; i++) {
                if (m[i] == i) {
                    dof(n - 1, String.valueOf(i));
                }
            }
        } else {
            dof(n, "");
        }
        return r;
    }

    void dof(int n, String inner) {
        if (n == 0) {
            r.add(inner);
            return;
        }
        int start = n == 2 ? 1 : 0;
        for (int i = start; i <= 9; i++) {
            if (m[i] >= 0) {
                dof(n - 2, i + inner + m[i]);
            }
        }
    }


}
