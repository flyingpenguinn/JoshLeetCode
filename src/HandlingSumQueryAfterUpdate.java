import java.util.ArrayList;
import java.util.List;

public class HandlingSumQueryAfterUpdate {
    // lazy segtree template with flip
    static class Node {

        long len;
        long cnt0;
        long cnt1;

        Node(long len, long cnt0, long cnt1) {
            this.len = len;
            this.cnt0 = cnt0;
            this.cnt1 = cnt1;
        }
    }

    static class SegTree {
        Node[] t;
        long[] lazy;
        int n;

        public SegTree(int[] a) {
            this.n = a.length;
            this.t = new Node[4 * n + 1];
            this.lazy = new long[4 * n + 1];

            build(a, 1, 0, n - 1);
        }

        private void build(int[] a, int idx, int l, int r) {
            if (l == r) {
                t[idx] = makeLeaf(a[l]);
                return;
            }

            int mid = l + (r - l) / 2;
            int left = idx * 2;
            int right = idx * 2 + 1;

            build(a, left, l, mid);
            build(a, right, mid + 1, r);

            t[idx] = merge(t[left], t[right]);
        }

        private Node makeLeaf(long v) {
            long cnt1 = v == 1 ? 1 : 0;
            long cnt0 = v == 0 ? 1 : 0;
            return new Node(1, cnt0, cnt1);
        }

        private Node merge(Node left, Node right) {
            return new Node(
                    left.len + right.len,
                    left.cnt0 + right.cnt0,
                    left.cnt1 + right.cnt1);

        }

        private void apply(int idx, long delta) {

            lazy[idx] += delta;
            if (delta % 2 == 1) {
                long tmp = t[idx].cnt0;
                t[idx].cnt0 = t[idx].cnt1;
                t[idx].cnt1 = tmp;
            }
        }

        private void push(int idx) {
            if (lazy[idx] == 0) {
                return;
            }

            int left = idx * 2;
            int right = idx * 2 + 1;

            apply(left, lazy[idx]);
            apply(right, lazy[idx]);

            lazy[idx] = 0;
        }

        public void rangeAdd(int ql, int qr, long delta) {
            rangeAdd(1, 0, n - 1, ql, qr, delta);
        }

        private void rangeAdd(int idx, int l, int r, int ql, int qr, long delta) {
            if (qr < l || r < ql) {
                return;
            }

            if (ql <= l && r <= qr) {
                apply(idx, delta);
                return;
            }

            push(idx);

            int mid = l + (r - l) / 2;
            int left = idx * 2;
            int right = idx * 2 + 1;

            rangeAdd(left, l, mid, ql, qr, delta);
            rangeAdd(right, mid + 1, r, ql, qr, delta);

            t[idx] = merge(t[left], t[right]);
        }

        public Node query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
        }

        private Node query(int idx, int l, int r, int ql, int qr) {
            if (qr < l || r < ql) {
                return new Node(0, 0, 0);
            }

            if (ql <= l && r <= qr) {
                return t[idx];
            }

            push(idx);

            int mid = l + (r - l) / 2;
            int left = idx * 2;
            int right = idx * 2 + 1;

            Node lr = query(left, l, mid, ql, qr);
            Node rr = query(right, mid + 1, r, ql, qr);

            return merge(lr, rr);
        }
    }

    public long[] handleQuery(int[] a, int[] b, int[][] qs) {
        int an = a.length;
        int qn = qs.length;

        List<Long> res = new ArrayList<>();
        SegTree seg = new SegTree(a);
        long added = 0;
        for (long bi : b) {
            added += bi;
        }
        for (int i = 0; i < qn; ++i) {
            int[] q = qs[i];
            int type = q[0];
            if (type == 1) {
                int l = q[1];
                int r = q[2];
                seg.rangeAdd(l, r, 1);
            } else if (type == 2) {

                long p = q[1];
                long suma = seg.query(0, an - 1).cnt1;
                long av = suma * p;
                added += av;
            } else {
                res.add(added);
            }
        }
        long[] rres = new long[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            rres[i] = res.get(i);
        }
        return rres;
    }
}


class HandleQuerySqrt {
    static class SqrtSum {
        int n;
        int B;
        int m;

        long[] a;
        long[] lazy;
        long[] sums;

        public SqrtSum(int[] arr) {
            this.n = arr.length;
            this.B = (int) Math.sqrt(n) + 1;
            this.m = (n + B - 1) / B;

            this.a = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = arr[i];
            }

            this.lazy = new long[m];
            this.sums = new long[m];

            for (int i = 0; i < m; i++) {
                rebuild(i);
            }
        }

        private int blockLen(int b) {
            int l = b * B;
            int r = Math.min(n - 1, l + B - 1);
            return r - l + 1;
        }

        private void rebuild(int b) {

            int l = b * B;
            int r = Math.min(n - 1, l + B - 1);
            sums[b] = 0;
            for (int i = l; i <= r; i++) {
                sums[b] += a[i];
            }
        }

        private void push(int b) {
            if (lazy[b] == 0) {
                return;
            }

            int l = b * B;
            int r = Math.min(n - 1, l + B - 1);

            for (int i = l; i <= r; i++) {
                if (lazy[b] % 2 == 1) {
                    a[i] ^= 1;
                }
            }

            lazy[b] = 0;
            rebuild(b);
        }

        public void flip(int ql, int qr) {
            int lb = ql / B;
            int rb = qr / B;

            if (lb == rb) {
                push(lb);

                for (int i = ql; i <= qr; i++) {
                    a[i] ^= 1;
                }

                rebuild(lb);
                return;
            }

            push(lb);
            int lend = Math.min(n - 1, (lb + 1) * B - 1);
            for (int i = ql; i <= lend; i++) {
                a[i] ^= 1;
            }
            rebuild(lb);

            push(rb);
            int rstart = rb * B;
            for (int i = rstart; i <= qr; i++) {
                a[i] ^= 1;
            }
            rebuild(rb);

            for (int b = lb + 1; b <= rb - 1; b++) {
                ++lazy[b];
                if (lazy[b] % 2 == 1) {
                    sums[b] = blockLen(b) - sums[b];
                }
            }
        }

        public long sumall() {
            long res = 0;
            for (int b = 0; b < m; b++) {
                res += sums[b];
            }
            return res;
        }
    }

    public long[] handleQuery(int[] a, int[] b, int[][] qs) {
        int an = a.length;
        int qn = qs.length;

        List<Long> res = new ArrayList<>();
        SqrtSum sqrtsum = new SqrtSum(a);
        long added = 0;
        for (long bi : b) {
            added += bi;
        }
        for (int i = 0; i < qn; ++i) {
            int[] q = qs[i];
            int type = q[0];
            if (type == 1) {
                int l = q[1];
                int r = q[2];
                sqrtsum.flip(l, r);
            } else if (type == 2) {

                long p = q[1];
                long suma = sqrtsum.sumall();
                long av = suma * p;
                added += av;
            } else {
                res.add(added);
            }
        }
        long[] rres = new long[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            rres[i] = res.get(i);
        }
        return rres;
    }
}