package base;

public class SegTrees {
    // seg tree support range update
    static class Node {
        long max;
        long min;

        Node(long max, long min) {
            this.max = max;
            this.min = min;
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
            return new Node(v, v);
        }

        private Node merge(Node left, Node right) {
            return new Node(
                    Math.max(left.max, right.max),
                    Math.min(left.min, right.min)
            );
        }

        private void apply(int idx, long delta) {
            t[idx].max += delta;
            t[idx].min += delta;
            lazy[idx] += delta;
        }

        private void push(int idx) {
            if (lazy[idx] == 0) return;

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
                return new Node(Long.MIN_VALUE, Long.MAX_VALUE);
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


}