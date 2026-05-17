public class LargestLocalValuesInMatrixII {
    // seg tree template for max
    class SegTree {


        class Node {
            int value; // after mod k

        }

        Node[] t;
        int n;

        public SegTree(int[] a, int n) {
            this.t = new Node[4 * n + 1];
            this.n = n;
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
            cur.value = Math.max(left.value, right.value);
            return cur;
        }

        private Node makeleaf(int v) {
            Node n = new Node();
            n.value = v;
            return n;
        }

        // Range query [ql..qr], returns identity‑if‑empty
        private Node query(int idx, int l, int r, int ql, int qr) {
            if (qr < l || r < ql) {
                // identity node: prod=1, cnt all zero
                Node id = new Node();
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

    public int countLocalMaximums(int[][] a) {
        int m = a.length;
        int n = a[0].length;
        SegTree[] rseg = new SegTree[m];
        SegTree[] cseg = new SegTree[n];
        for (int i = 0; i < m; ++i) {
            SegTree cs = new SegTree(a[i], n);
            rseg[i] = cs;
        }
        for (int j = 0; j < n; ++j) {
            int[] col = new int[m];
            for (int i = 0; i < m; ++i) {
                col[i] = a[i][j];
            }
            SegTree cs = new SegTree(col, m);
            cseg[j] = cs;
        }
        int res = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                int v = a[i][j];
                if (v == 0) {
                    continue;
                }
                int rstart1 = Math.max(0, i - v + 1);
                int rend1 = Math.min(m - 1, i + v - 1);
                int cstart1 = Math.max(0, j - v);
                int cend1 = Math.min(n - 1, j + v);
                boolean bad = false;
                for (int k = rstart1; k <= rend1; ++k) {
                    int cv = rseg[k].query(1, 0, n - 1, cstart1, cend1).value;
                    if (cv > v) {
                        bad = true;
                        break;
                    }
                }
                if (bad) {
                    continue;
                }
                int rstart2 = Math.max(0, i - v);
                int rend2 = Math.min(m - 1, i + v);
                int cstart2 = Math.max(0, j - v + 1);
                int cend2 = Math.min(n - 1, j + v - 1);
                for (int k = cstart2; k <= cend2; ++k) {
                    int cv = cseg[k].query(1, 0, m - 1, rstart2, rend2).value;
                    if (cv > v) {
                        bad = true;
                        break;
                    }
                }
                if (bad) {
                    continue;
                }
                ++res;
            }
        }
        return res;
    }
}
