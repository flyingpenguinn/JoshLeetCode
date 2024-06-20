import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinMovesPeacefulBoard {
    // decompose row and column!
    // then imilar to distribute coins in binary tree: for each pos move = abs(what we have so far - what is really needed) then add them together
    public int minMoves(int[][] a) {
        int n = a.length;
        int[] r = new int[n];
        int[] c = new int[n];
        for (int[] ai : a) {
            ++r[ai[0]];
            ++c[ai[1]];
        }
        ////   System.out.println(Arrays.toString(r));
        //   System.out.println(Arrays.toString(c));
        int rr = solve(r);
        int cr = solve(c);
        //  System.out.println(rr+" "+cr);
        return rr + cr;
    }

    private int solve(int[] a) {
        int n = a.length;
        int res = 0;
        int have = 0;
        for (int i = 0; i < n; ++i) {
            have += a[i];
            int delta = Math.abs(have - (i + 1));
            res += delta;
        }
        return res;
    }

}


class MinMovesPeacefulBoardSorting {
    // another way based on sorting
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
