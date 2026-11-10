package base;

public class SegTrees {
    // seg tree support range update
    static class Node {
        int l, r;
        long min, max, sum;

        Node(int l, int r, long min, long max, long sum) {
            this.l = l;
            this.r = r;
            this.min = min;
            this.max = max;
            this.sum = sum;
        }

        int len() {
            return r - l + 1;
        }
    }

    static class SegTree {
        Node[] tree;
        long[] lazy;
        int n;

        SegTree(int[] a) {
            this.n = a.length;
            this.tree = new Node[4 * n + 5];
            this.lazy = new long[4 * n + 5];
            build(1, 0, n - 1, a);
        }

        private void build(int idx, int l, int r, int[] a) {
            if (l == r) {
                long v = a[l];
                tree[idx] = new Node(l, r, v, v, v);
                return;
            }

            int mid = l + (r - l) / 2;
            build(idx * 2, l, mid, a);
            build(idx * 2 + 1, mid + 1, r, a);

            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }

        private Node merge(Node left, Node right) {
            return new Node(
                    left.l,
                    right.r,
                    Math.min(left.min, right.min),
                    Math.max(left.max, right.max),
                    left.sum + right.sum
            );
        }

        private void apply(int idx, long delta) {
            Node cur = tree[idx];

            cur.min += delta;
            cur.max += delta;
            cur.sum += delta * cur.len();

            lazy[idx] += delta;
        }

        private void push(int idx) {
            if (lazy[idx] == 0) {
                return;
            }

            long delta = lazy[idx];

            apply(idx * 2, delta);
            apply(idx * 2 + 1, delta);

            lazy[idx] = 0;
        }

        public void pointAdd(int q, long delta){
            rangeAdd(1, q, q, delta);
        }

        public void rangeAdd(int ql, int qr, long delta) {
            rangeAdd(1, ql, qr, delta);
        }

        private void rangeAdd(int idx, int ql, int qr, long delta) {
            Node cur = tree[idx];

            if (qr < cur.l || cur.r < ql) {
                return;
            }

            if (ql <= cur.l && cur.r <= qr) {
                apply(idx, delta);
                return;
            }

            push(idx);

            rangeAdd(idx * 2, ql, qr, delta);
            rangeAdd(idx * 2 + 1, ql, qr, delta);

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

            push(idx);

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

        // leftmost index in [ql, qr] whose value >= t, or -1. O(log n)
        // e.g. LC 2286 gather: first row <= maxRow with at least k free seats
        public int firstAtLeast(int ql, int qr, long t) {
            return firstAtLeast(1, ql, qr, t);
        }

        private int firstAtLeast(int idx, int ql, int qr, long t) {
            Node cur = tree[idx];
            if (qr < cur.l || cur.r < ql || cur.max < t) {
                return -1;
            }
            if (cur.l == cur.r) {
                return cur.l;
            }
            push(idx);
            int res = firstAtLeast(idx * 2, ql, qr, t);
            if (res != -1) {
                return res;
            }
            return firstAtLeast(idx * 2 + 1, ql, qr, t);
        }

        // smallest r >= ql such that sum[ql..r] >= t, or -1. values must be non-negative. O(log n)
        // caller checks r <= qr. e.g. LC 2286 scatter: last row touched when seating t people from row 0
        public int prefixReach(int ql, long t) {
            return prefixReach(1, ql, new long[]{t});
        }

        private int prefixReach(int idx, int ql, long[] need) {
            Node cur = tree[idx];
            if (cur.r < ql) {
                return -1;
            }
            if (ql <= cur.l && cur.sum < need[0]) {
                need[0] -= cur.sum; // whole node consumed, keep going right
                return -1;
            }
            if (cur.l == cur.r) {
                return cur.l;
            }
            push(idx);
            int res = prefixReach(idx * 2, ql, need);
            if (res != -1) {
                return res;
            }
            return prefixReach(idx * 2 + 1, ql, need);
        }
    }
}