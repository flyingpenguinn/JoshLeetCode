import java.util.*;

public class AlternatingGroupsIII {
    private int[] bit1;
    private int[] bit2;
    private int n;
    private Set<Integer> st;

    public List<Integer> numberOfAlternatingGroups(int[] colors, int[][] queries) {
        n = colors.length;
        bit1 = new int[n + 1];
        bit2 = new int[n + 1];
        st = new TreeSet<>();

        // Initialize Fenwick trees with zeros
        Arrays.fill(bit1, 0);
        Arrays.fill(bit2, 0);

        for (int i = 0; i < n; i++) {
            if (colors[i] == colors[(i + 1) % n]) ins(i);
        }

        List<Integer> ans = new ArrayList<>();
        for (int[] qry : queries) {
            if (qry[0] == 1) {
                if (st.isEmpty()) {
                    ans.add(n);
                } else {
                    int sm = p(bit2, n) - p(bit2, qry[1] - 1);
                    int cnt = p(bit1, n) - p(bit1, qry[1] - 1);
                    ans.add(sm - cnt * qry[1] + cnt);
                }
            } else {
                int idx = qry[1];
                int col = qry[2];
                int prv = (idx - 1 + n) % n;
                int nxt = (idx + 1) % n;
                if (colors[prv] == colors[idx]) del(prv);
                if (colors[idx] == colors[nxt]) del(idx);
                colors[idx] = col;
                if (colors[prv] == colors[idx]) ins(prv);
                if (colors[idx] == colors[nxt]) ins(idx);
            }
        }
        return ans;
    }

    private int p(int[] b, int i) {
        int r = 0;
        while (i > 0) {
            r += b[i];
            i -= i & (-i);
        }
        return r;
    }

    private void u(int[] b, int i, int v) {
        while (i < b.length) {
            b[i] += v;
            i += i & (-i);
        }
    }

    private void update(int L, int R, int pos, int k) {
        int old = (R - L + n) % n;
        if (old == 0) old = n;
        u(bit1, old, -k);
        u(bit2, old, -k * old);

        int len = (pos - L + n) % n;
        if (len == 0) len = n;
        u(bit1, len, k);
        u(bit2, len, k * len);
        len = (R - pos + n) % n;
        if (len == 0) len = n;
        u(bit1, len, k);
        u(bit2, len, k * len);
    }

    private void ins(int pos) {
        if (st.isEmpty()) {
            st.add(pos);
            u(bit1, n, 1);
            u(bit2, n, n);
        } else {
            st.add(pos);
            Iterator<Integer> it = st.iterator();
            Integer[] elements = new Integer[st.size()];
            int index = 0;
            while (it.hasNext()) {
                elements[index++] = it.next();
            }
            int L = (index == 0 ? elements[elements.length - 1] : elements[index - 1]);
            int R = (index == elements.length - 1 ? elements[0] : elements[index + 1]);
            update(L, R, pos, 1);
        }
    }

    private void del(int pos) {
        if (st.size() == 1) {
            st.remove(pos);
            u(bit1, n, -1);
            u(bit2, n, -n);
        } else {
            st.remove(pos);
            Iterator<Integer> it = st.iterator();
            Integer[] elements = new Integer[st.size()];
            int index = 0;
            while (it.hasNext()) {
                elements[index++] = it.next();
            }
            int L = (index == 0 ? elements[elements.length - 1] : elements[index - 1]);
            int R = (index == elements.length - 1 ? elements[0] : elements[index]);
            update(L, R, pos, -1);
        }
    }
}
