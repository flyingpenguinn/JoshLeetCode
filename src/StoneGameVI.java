import java.util.Arrays;

public class StoneGameVI {
    // each stone's value is av[i]+bv[i]: the value that b will get, for a it's -bv[i], then diff is av[i]+bv[i].
    // we extract the biggest diff, and a goes first
    public int stoneGameVI(int[] av, int[] bv) {
        int n = av.length;
        int[][] st = new int[n][3];
        for (int i = 0; i < n; i++) {
            st[i][0] = av[i];
            st[i][1] = bv[i];
            st[i][2] = av[i] + bv[i];
        }
        Arrays.sort(st, (x, y) -> Integer.compare(y[2], x[2]));
        //  System.out.println(Arrays.deepToString(st));
        int as = 0;
        int bs = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                as += st[i][0];
            } else {
                bs += st[i][1];
            }
        }
        if (as > bs) {
            return 1;
        } else if (as < bs) {
            return -1;
        } else {
            return 0;
        }
    }
}
