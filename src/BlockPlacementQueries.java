import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class BlockPlacementQueries {

    // typical segment tree
    private int n = 50000;

    public List<Boolean> getResults(int[][] q) {
        List<Boolean> res = new ArrayList<>();
        int qn = q.length;
        update(0, n, 1, 0, n);
        update(n, 0, 1, 0, n);
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(n);

        for (int i = 0; i < qn; i++) {
            if (q[i][0] == 1) {
                int x = q[i][1];
                int prev = set.floor(x);
                int next = set.ceiling(x);
                set.add(x);
                update(prev, x - prev, 1, 0, n);
                update(x, next - x, 1, 0, n);
            } else { //q[i][0] == 2
                int x = q[i][1];
                int sz = q[i][2];
                if (sz > x) {
                    res.add(false);
                    continue;
                }
                int max = query(1, 0, n, 0, x - sz);
                boolean cres = max >= sz;
                res.add(cres);
            }
        }

        return res;
    }


    private int[] seg = new int[3*n];

    // the index of l...r is idx
    private int query(int idx, int l, int r, int ql, int qr) {
        int mid = l + (r - l) / 2;
        int res = 0;
        if (ql <= l && r <= qr) {
            return seg[idx];
        }
        if (ql <= mid) {
            res = Math.max(res, query(idx * 2, l, mid, ql, qr));
        }
        if (mid < qr) {
            res = Math.max(res, query(idx * 2 + 1, mid + 1, r, ql, qr));
        }
        return res;
    }

    private void update(int ai, int v, int idx, int l, int r) {
        int mid = l + (r - l) / 2;
        if (l == r) {
            seg[idx] = v;
        } else {
            if (ai <= mid) {
                update(ai, v, idx * 2, l, mid);
            } else {
                update(ai, v, idx * 2 + 1, mid + 1, r);
            }
            seg[idx] = Math.max(seg[idx * 2], seg[idx * 2 + 1]);
        }
    }


    public static void main(String[] args) {
        System.out.println(new BlockPlacementQueries().getResults(new int[][]{{1, 7}, {2, 7, 6}, {1, 2}, {2, 7, 5}, {2, 7, 6}}));
    }
}
