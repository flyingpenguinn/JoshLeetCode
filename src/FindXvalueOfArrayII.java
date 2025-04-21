import base.ArrayUtils;

import java.util.Arrays;

public class FindXvalueOfArrayII {
    // Seg tree template

    private int k;
    private int n;


    class Node {
        int prod; // after mod k
        // number of prefix in this seg that if incoming rem is r0, end up having reminder as p
        int[][] cnt = new int[k][k];
    }

    class SegTree {
        Node[] t;

        public SegTree(int[] a) {
            this.t = new Node[4 * n + 1];

            build(a, 1, 0, a.length - 1);
        }

        public void update(int pos, int nv) {
            update(1, 0, n - 1, pos, nv);
        }

        private void update(int idx, int l, int u, int pos, int nv) {
            if (l == u) {
                t[idx] = makeleaf(nv);
                return;
            }
            int mid = l + (u - l) / 2;
            int left = 2 * idx;
            int right = 2 * idx + 1;
            if (pos <= mid) {
                update(2 * idx, l, mid, pos, nv);
            } else {
                update(2 * idx + 1, mid + 1, u, pos, nv);
            }
            t[idx] = merge(t[left], t[right]);
        }

        private void build(int[] a, int idx, int l, int u) {
            if (l == u) {
                t[idx] = makeleaf(a[l]);
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
            Node cur = new Node();
            cur.prod = (left.prod * right.prod) % k;
            for (int ri = 0; ri < k; ++ri) {
                for (int p = 0; p < k; ++p) {
                    // way1- only use left
                    int way1 = left.cnt[ri][p];
                    // way2 - run through a. now for b it starts with "througha" values
                    int througha = (ri * left.prod) % k;
                    int way2 = right.cnt[througha][p];
                    cur.cnt[ri][p] = way1 + way2;
                }
            }
            return cur;
        }

        private Node makeleaf(int v) {
            Node n = new Node();
            n.prod = v % k;
            for (int ri = 0; ri < k; ++ri) {
                int intv = (int)((1L*ri * v) % k);
                ++n.cnt[ri][intv];
            }
            return n;
        }

        // Range query [ql..qr], returns identity‑if‑empty
        private Node query(int idx, int l, int r, int ql, int qr) {
            if (qr < l || r < ql) {
                // identity node: prod=1, cnt all zero
                Node id = new Node();
                id.prod = 1 % k;
                return id;
            }
            if (ql <= l && r <= qr) {
                return t[idx];
            }
            int mid = l + (r - l) / 2;
            int left = idx * 2;
            int right = idx * 2 + 1;
            Node lr = query(left, l, mid, ql, qr);
            Node rr = query(right, mid + 1, r, ql, qr);
            return merge(lr, rr);
        }


    }

    private int[] res;

    public int[] resultArray(int[] a, int k, int[][] qs) {
        this.n = a.length;
        this.k = k;
        int qn = qs.length;
        res = new int[qn];
        SegTree seg = new SegTree(a);
        for (int i = 0; i < qn; ++i) {
            int posi = qs[i][0];
            int nv = qs[i][1];
            int start = qs[i][2];
            int x = qs[i][3];
            seg.update(posi, nv);
            Node qres = seg.query(1, 0, n - 1, start, n - 1);
            res[i] = qres.cnt[1%k][x];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindXvalueOfArrayII().resultArray(ArrayUtils.read1d("[1,2,3,4,5]"), 3, ArrayUtils.read("[[2,2,0,2],[3,3,3,0],[0,1,0,1]]"))));
    }

}
