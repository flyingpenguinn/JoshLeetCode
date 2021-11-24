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
        dfs(0, n, new char[n]);
        return res;
    }

    private void dfs(int index, int n, char[] a) {
        if (index >= (n + 1) / 2) {
            res.add(new String(a));
            return;
        }
        int start = 0;
        if(n>1 && index==0){
            start = 1;
        }
        for (int i = start; i < m.length; ++i) {
            if (m[i] == -1) {
                continue;
            }
            if (index == n - 1 - index) {
                if (m[i] == i) {
                    a[index] = (char) (i + '0');
                }else{
                    continue;
                }
            } else {
                a[index] = (char) (i + '0');
                a[n - 1 - index] = (char) (m[i] + '0');
            }
            dfs(index + 1, n, a);
        }
    }

    public static void main(String[] args) {
        System.out.println(new StrobogrammaticNumberII().findStrobogrammatic(1));
    }
}
