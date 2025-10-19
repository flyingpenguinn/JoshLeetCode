import java.util.HashMap;
import java.util.Map;

public class LexicographicallySmallestBalancedSubarrayII {
    // TODO
    int[] mn, mx, lz;
    int n;

    void ap(int id, int v) {
        mn[id] += v;
        mx[id] += v;
        lz[id] += v;
    }

    void dw(int id) {
        int z = lz[id];
        if (z != 0) {
            int L = id << 1, R = L | 1;
            ap(L, z);
            ap(R, z);
            lz[id] = 0;
        }
    }

    void pu(int id) {
        int L = id << 1, R = L | 1;
        mn[id] = Math.min(mn[L], mn[R]);
        mx[id] = Math.max(mx[L], mx[R]);
    }

    void bd(int id, int l, int r) {
        mn[id] = mx[id] = lz[id] = 0;
        if (l == r) return;
        int m = (l + r) >> 1;
        bd(id << 1, l, m);
        bd(id << 1 | 1, m + 1, r);
        pu(id);
    }

    void add(int id, int l, int r, int ql, int qr, int v) {
        if (ql <= l && r <= qr) {
            ap(id, v);
            return;
        }
        dw(id);
        int m = (l + r) >> 1;
        if (ql <= m) add(id << 1, l, m, ql, qr, v);
        if (qr > m) add(id << 1 | 1, m + 1, r, ql, qr, v);
        pu(id);
    }

    int qry(int id, int l, int r, int val) {
        if (l == r) return l;
        dw(id);
        int m = (l + r) >> 1;
        int L = id << 1;
        if (mn[L] <= val && val <= mx[L]) return qry(L, l, m, val);
        return qry(L | 1, m + 1, r, val);
    }

    public int longestBalanced(int[] a) {
        int n0 = a.length;
        n = n0;
        mn = new int[(n + 1) * 4 + 5];
        mx = new int[(n + 1) * 4 + 5];
        lz = new int[(n + 1) * 4 + 5];
        bd(1, 0, n);
        Map<Integer, Integer> last = new HashMap<>();
        int res = 0, now = 0;
        for (int i = 1; i <= n; ++i) {
            int x = a[i - 1];
            int d = ((x & 1) == 1) ? 1 : -1;
            Integer p = last.get(x);
            if (p != null) {
                add(1, 0, n, p, n, -d);
                now -= d;
            }
            last.put(x, i);
            add(1, 0, n, i, n, d);
            now += d;
            int pos = qry(1, 0, n, now);
            int len = i - pos;
            if (len > res) res = len;
        }
        return res;
    }
}
