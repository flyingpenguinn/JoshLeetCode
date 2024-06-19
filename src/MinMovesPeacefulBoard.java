import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinMovesPeacefulBoard {
    // decompose row and column!
    // then can also sort rows or columns and assign first row to row1, 2nd row to row 2, and so forth
    public int minMoves(int[][] a) {
        int n = a.length;
        int res = 0;
        for (int index = 0; index < 2; ++index) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                list.add(a[i][index]);
            }
            res += solve(list);
        }
        return res;
    }

    private int solve(List<Integer> a) {
        int n = a.size();
        Collections.sort(a);
        int res = 0;
        for (int i = 0; i < n; ++i) {
            res += Math.abs(i - a.get(i));
        }
        return res;
    }
}
