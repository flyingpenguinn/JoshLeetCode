import base.ArrayUtils;

import java.util.*;

public class FindBuildingWhereAliceBobMeet {
    public int[] leftmostBuildingQueries(int[] a, int[][] qs) {
        int n = a.length;
        int qn = qs.length;
        int[][] nqs = new int[qn][3];
        for (int i = 0; i < qn; ++i) {
            nqs[i][0] = Math.min(qs[i][0], qs[i][1]);
            nqs[i][1] = Math.max(qs[i][0], qs[i][1]);
            nqs[i][2] = i;
        }
        Arrays.sort(nqs, (x, y) -> Integer.compare(x[1], y[1]));
        int[] res = new int[qn];
        Arrays.fill(res, -1);
        List<Integer> st = new ArrayList<>();
        int qi = qn - 1;
        for (int i = n - 1; i >= 0; --i) {

            while (!st.isEmpty() && a[st.get(st.size() - 1)] <= a[i]) {
                st.remove(st.size() - 1);
            }
            st.add(i);
            while (qi >= 0 && i == nqs[qi][1]) {
                int x = Math.min(nqs[qi][0], nqs[qi][1]);
                int y = Math.max(nqs[qi][0], nqs[qi][1]);
                int idx = nqs[qi][2];
                if (x == y) {
                    res[idx] = x;
                } else if (a[x] < a[y]) {
                    res[idx] = y;
                } else {
                    int pos = binary(a, st, a[x]);
                    if (pos >= 0 && pos < st.size()) {
                        res[idx] = st.get(pos);
                    }
                }

                --qi;
            }
        }
        return res;
    }

    private int binary(int[] a, List<Integer> st, int t) {
        int l = 0;
        int u = st.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[st.get(mid)] > t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindBuildingWhereAliceBobMeet().leftmostBuildingQueries(ArrayUtils.read1d("[1,2,1,2,1,2]"), ArrayUtils.read("[[0,2]]"))));
    }

}

class FindBuildingWhereAliceBobMeetSegTree {
    // seg tree binary search
    static class Node {
        int max = 0;
        int index = -1;

        public Node(int max, int index) {
            this.max = max;
            this.index = index;
        }
    }


    static class SegTree {
        Node[] t;
        int n = 0;

        public SegTree(int[] a) {
            this.n = a.length;
            this.t = new Node[4 * n + 1];
            build(a, 1, 0, a.length - 1);
        }


        private void build(int[] a, int idx, int l, int u) {
            if (l == u) {
                t[idx] = makeleaf(a[l], l);
                return;
            }
            int mid = l + (u - l) / 2;
            int left = 2 * idx;
            int right = 2 * idx + 1;
            build(a, left, l, mid);
            build(a, right, mid + 1, u);
            t[idx] = merge(t[left], t[right]);
        }

        private Node merge(Node left, Node right) {
            Node cur = new Node(0, -1);
            if (left.max >= right.max) {
                cur.max = left.max;
                cur.index = left.index;
            } else {
                cur.max = right.max;
                cur.index = right.index;

            }
            return cur;
        }

        private Node makeleaf(int v, int index) {
            return new Node(v, index);
        }

        // left most >
        private int query(int idx, int l, int r, int ql, int qr, int v) {
            if (qr < l || r < ql || t[idx] == null || t[idx].max <= v) {
                // identity node: prod=1, cnt all zero
                return -1;
            }
            if (ql <= l && r <= qr) {
                if (l == r) {
                    return t[idx].index;
                }
                int mid = l + (r - l) / 2;
                int left = idx * 2;
                int right = idx * 2 + 1;
                if (t[left].max > v) {
                    return query(left, l, mid, ql, qr, v);
                } else {
                    return query(right, mid + 1, r, ql, qr, v);
                }
            }
            int mid = l + (r - l) / 2;
            int left = idx * 2;
            int right = idx * 2 + 1;
            int lr = query(left, l, mid, ql, qr, v);
            int rr = query(right, mid + 1, r, ql, qr, v);
            if (lr != -1) {
                return lr;
            }
            return rr;
        }


    }

    public int[] leftmostBuildingQueries(int[] a, int[][] qs) {
        int n = a.length;
        SegTree sg = new SegTree(a);
        int qn = qs.length;
        int[] res = new int[qn];
        for (int i = 0; i < qn; ++i) {
            int v1 = qs[i][0];
            int v2 = qs[i][1];
            int nv1 = Math.min(v1, v2);
            int nv2 = Math.max(v1, v2);
            if (nv1 == nv2) {
                res[i] = nv2;
            } else if (a[nv1] < a[nv2]) {
                res[i] = nv2;
            } else {
                int index = sg.query(1, 0, n - 1, nv2 + 1, n - 1, a[nv1]);
                res[i] = index;
            }
        }
        return res;
    }
}
