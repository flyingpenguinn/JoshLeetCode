import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberOfBlackBlocks {
    private Map<Integer, Set<Integer>> m = new HashMap<>();
    private long[] res = new long[5];
    private long rows = 0;
    private long cols = 0;

    public long[] countBlackBlocks(long rows, long cols, int[][] coordinates) {
        this.rows = rows;
        this.cols = cols;
        for (int[] cor : coordinates) {
            int r = cor[0];
            int c = cor[1];
            m.computeIfAbsent(r, p -> new HashSet<>()).add(c);
        }

        for (int[] cor : coordinates) {
            int r = cor[0];
            int c = cor[1];
            int c1 = topleft(r, c);
            ++res[c1];
            int c2 = topright(r, c);
            ++res[c2];
            int c3 = bottomleft(r, c);
            ++res[c3];
            int c4 = bottomright(r, c);
            ++res[c4];
            //   System.out.println(c1+" "+c2+" "+c3+" "+c4);
        }
        res[2] /= 2;
        res[3] /= 3;
        res[4] /= 4;
        res[0] = (rows - 1) * (cols - 1) - res[1] - res[2] - res[3] - res[4];
        return res;
    }

    private boolean rowinvalid(int r) {
        return r < 0 || r >= rows;
    }

    private boolean colinvalid(int c) {
        return c < 0 || c >= cols;
    }

    private int topleft(int r, int c) {
        if (colinvalid(c + 1) || rowinvalid(r + 1)) {
            return 0;
        }
        int res = 1;
        int v1 = m.getOrDefault(r, new HashSet<>()).contains(c + 1) ? 1 : 0;
        int v2 = m.getOrDefault(r + 1, new HashSet<>()).contains(c) ? 1 : 0;
        int v3 = m.getOrDefault(r + 1, new HashSet<>()).contains(c + 1) ? 1 : 0;
        res += v1 + v2 + v3;
        return res;
    }


    private int topright(int r, int c) {
        if (colinvalid(c - 1) || rowinvalid(r + 1)) {
            return 0;
        }
        int res = 1;
        int v1 = m.getOrDefault(r, new HashSet<>()).contains(c - 1) ? 1 : 0;
        int v2 = m.getOrDefault(r + 1, new HashSet<>()).contains(c) ? 1 : 0;
        int v3 = m.getOrDefault(r + 1, new HashSet<>()).contains(c - 1) ? 1 : 0;
        res += v1 + v2 + v3;
        return res;
    }

    private int bottomleft(int r, int c) {
        if (colinvalid(c + 1) || rowinvalid(r - 1)) {
            return 0;
        }
        int res = 1;
        int v1 = m.getOrDefault(r, new HashSet<>()).contains(c + 1) ? 1 : 0;
        int v2 = m.getOrDefault(r - 1, new HashSet<>()).contains(c) ? 1 : 0;
        int v3 = m.getOrDefault(r - 1, new HashSet<>()).contains(c + 1) ? 1 : 0;
        res += v1 + v2 + v3;
        return res;
    }

    private int bottomright(int r, int c) {
        if (colinvalid(c - 1) || rowinvalid(r - 1)) {
            return 0;
        }
        int res = 1;
        int v1 = m.getOrDefault(r, new HashSet<>()).contains(c - 1) ? 1 : 0;
        int v2 = m.getOrDefault(r - 1, new HashSet<>()).contains(c) ? 1 : 0;
        int v3 = m.getOrDefault(r - 1, new HashSet<>()).contains(c - 1) ? 1 : 0;
        res += v1 + v2 + v3;
        return res;
    }
}
