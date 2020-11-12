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
    private int[] m = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
    private List<String> res = new ArrayList<>();

    public List<String> findStrobogrammatic(int n) {
        dfs(0, n, "", "");
        return res;
    }

    private void dfs(int i, int n, String p1, String p2) {
        int start = (i > 0 || n == 1) ? 0 : 1;
        if (i == n / 2) {
            if (n % 2 == 1) {
                for (int j = start; j < 10; j++) {
                    if (m[j] == j) { // excluding 6 and 9
                        res.add(p1 + j + p2);
                    }
                }
            } else {
                res.add(p1 + p2);
            }
            return;
        }
        for (int j = start; j < 10; j++) {
            if (m[j] != -1) { // can allow all 5
                dfs(i + 1, n, p1 + j, m[j] + p2);
            }
        }
    }
}
