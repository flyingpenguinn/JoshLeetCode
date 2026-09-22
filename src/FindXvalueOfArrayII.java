import base.ArrayUtils;

import java.util.Arrays;

public class FindXvalueOfArrayII {
    static final int SIZE = 5;

    static class Node {
        int l, r;
        int prod;
        int[] cnt = new int[SIZE];

        Node(int l, int r, int prod, int[] cnt) {
            this.l = l;
            this.r = r;
            this.prod = prod;
            this.cnt = Arrays.copyOf(cnt, SIZE);
        }

        int len() {
            return r - l + 1;
        }
    }

    static class SegTree {
        Node[] tree;
        int k;
        int n;

        SegTree(int[] a, int k) {
            this.n = a.length;
            this.tree = new Node[4 * n + 5];
            this.k = k;
            build(1, 0, n - 1, a);
        }

        private void build(int idx, int l, int r, int[] a) {
            if (l == r) {
                int v = a[l];
                int mod = v % k;
                int[] cnt = new int[SIZE];
                ++cnt[mod];
                tree[idx] = new Node(l, r, mod, cnt);
                return;
            }

            int mid = l + (r - l) / 2;
            build(idx * 2, l, mid, a);
            build(idx * 2 + 1, mid + 1, r, a);

            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }

        private Node merge(Node left, Node right) {
            int modv = (left.prod * right.prod) % k;
            Node nn = new Node(left.l, right.r, modv, new int[SIZE]);
            for (int i = 0; i < SIZE; ++i) {
                int nv = (left.prod * i) % k;
                nn.cnt[nv] += right.cnt[i];
            }
            for (int i = 0; i < SIZE; ++i) {
                nn.cnt[i] += left.cnt[i];
            }
            return nn;
        }

        private void apply(int idx, int nv) {
            // update is always on single point
            Node cur = tree[idx];
            if (cur.len() > 1) {
                throw new IllegalArgumentException("Must update single point...");
            }
            cur.cnt[cur.prod] -= 1;
            cur.prod = nv % k;
            cur.cnt[cur.prod] = 1;
        }


        public void pointUpdate(int q, int nv) {
            pointUpdate(1, q, q, nv);
        }


        private void pointUpdate(int idx, int ql, int qr, int nv) {
            Node cur = tree[idx];

            if (qr < cur.l || cur.r < ql) {
                return;
            }

            if (ql <= cur.l && cur.r <= qr) {
                apply(idx, nv);
                return;
            }
            pointUpdate(idx * 2, ql, qr, nv);
            pointUpdate(idx * 2 + 1, ql, qr, nv);

            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }

        public Node query(int ql, int qr) {
            return query(1, ql, qr);
        }

        private Node query(int idx, int ql, int qr) {
            Node cur = tree[idx];

            if (qr < cur.l || cur.r < ql) {
                return null;
            }

            if (ql <= cur.l && cur.r <= qr) {
                return cur;
            }


            Node left = query(idx * 2, ql, qr);
            Node right = query(idx * 2 + 1, ql, qr);

            if (left == null) {
                return right;
            }
            if (right == null) {
                return left;
            }
            return merge(left, right);
        }
    }

    public int[] resultArray(int[] a, int k, int[][] qs) {

        int n = a.length;
        int qn = qs.length;
        int[] res = new int[qn];
        SegTree seg = new SegTree(a, k);

        for (int p = 0; p < qn; ++p) {
            int[] q = qs[p];
            int index = q[0];
            int value = q[1];
            seg.pointUpdate(index, value);
            int start = q[2];
            int xi = q[3];
            Node cur = seg.query(start, n - 1);
            res[p] = cur.cnt[xi];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindXvalueOfArrayII().resultArray(ArrayUtils.read1d("[1,2,3,4,5]"), 3, ArrayUtils.read("[[2,2,0,2],[3,3,3,0],[0,1,0,1]]"))));
    }

}
