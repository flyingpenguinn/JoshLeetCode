import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromicPathQueryInATree {
    private int[] hd;
    private int[] to;
    private int[] nx;
    private int ei;

    private int[] p;
    private int[] dep;
    private int[][] up;
    private int B;

    private int[] tin;
    private int[] tout;
    private int[] it;
    private int tim;

    private int[] pref0;
    private int[] m;

    private int[] bit;

    private void ae(int u, int v) {
        to[ei] = v;
        nx[ei] = hd[u];
        hd[u] = ei;
        ei++;
    }

    private void badd(int i, int v) {
        int n = bit.length - 1;
        while (i <= n) {
            bit[i] ^= v;
            i += i & -i;
        }
    }

    private int bsum(int i) {
        int res = 0;
        while (i > 0) {
            res ^= bit[i];
            i -= i & -i;
        }
        return res;
    }

    private void brange(int l, int r, int v) {
        badd(l, v);
        if (r + 1 < bit.length) {
            badd(r + 1, v);
        }
    }

    private int pref(int u) {
        return pref0[u] ^ bsum(tin[u]);
    }

    private int lca(int a, int b) {
        if (dep[a] < dep[b]) {
            int t = a;
            a = b;
            b = t;
        }
        int diff = dep[a] - dep[b];
        for (int k = 0; k < B; k++) {
            if (((diff >> k) & 1) == 1) {
                a = up[k][a];
            }
        }
        if (a == b) {
            return a;
        }
        for (int k = B - 1; k >= 0; k--) {
            if (up[k][a] != up[k][b]) {
                a = up[k][a];
                b = up[k][b];
            }
        }
        return up[0][a];
    }

    private int parseInt(String q, int[] idx) {
        int i = idx[0];
        int n = q.length();
        while (i < n && q.charAt(i) == ' ') {
            i++;
        }
        int x = 0;
        while (i < n) {
            char c = q.charAt(i);
            if (c < '0' || c > '9') {
                break;
            }
            x = x * 10 + (c - '0');
            i++;
        }
        idx[0] = i;
        return x;
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        char[] ch = s.toCharArray();

        hd = new int[n];
        Arrays.fill(hd, -1);
        to = new int[(n - 1) << 1];
        nx = new int[(n - 1) << 1];
        ei = 0;

        for (int i = 0; i < n - 1; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            ae(u, v);
            ae(v, u);
        }

        m = new int[n];
        for (int i = 0; i < n; i++) {
            m[i] = 1 << (ch[i] - 'a');
        }

        p = new int[n];
        dep = new int[n];
        tin = new int[n];
        tout = new int[n];
        it = new int[n];
        pref0 = new int[n];

        B = 1;
        while ((1 << B) <= n) {
            B++;
        }
        up = new int[B][n];

        int[] st = new int[n];
        int sp = 0;

        p[0] = 0;
        dep[0] = 0;
        tim = 0;

        st[sp++] = 0;
        it[0] = hd[0];
        tin[0] = ++tim;
        pref0[0] = m[0];

        while (sp > 0) {
            int u = st[sp - 1];
            int e = it[u];
            if (e == -1) {
                tout[u] = tim;
                sp--;
            } else {
                it[u] = nx[e];
                int v = to[e];
                if (v != p[u]) {
                    p[v] = u;
                    dep[v] = dep[u] + 1;
                    it[v] = hd[v];
                    tin[v] = ++tim;
                    pref0[v] = pref0[u] ^ m[v];
                    st[sp++] = v;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            up[0][i] = p[i];
        }
        for (int k = 1; k < B; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        bit = new int[n + 2];

        int qc = 0;
        for (int i = 0; i < queries.length; i++) {
            if (queries[i].charAt(0) == 'q') {
                qc++;
            }
        }

        ArrayList<Boolean> res = new ArrayList<>(qc);

        int[] idx = new int[1];

        for (int i = 0; i < queries.length; i++) {
            String q = queries[i];
            if (q.charAt(0) == 'u') {
                idx[0] = 6;
                int u = parseInt(q, idx);
                while (idx[0] < q.length() && q.charAt(idx[0]) == ' ') {
                    idx[0]++;
                }
                char c = q.charAt(idx[0]);
                int nm = 1 << (c - 'a');
                int delta = m[u] ^ nm;
                if (delta != 0) {
                    m[u] = nm;
                    ch[u] = c;
                    brange(tin[u], tout[u], delta);
                }
            } else {
                idx[0] = 5;
                int u = parseInt(q, idx);
                int v = parseInt(q, idx);
                int w = lca(u, v);
                int x = pref(u) ^ pref(v) ^ m[w];
                res.add((x & (x - 1)) == 0);
            }
        }
        return res;
    }
}
