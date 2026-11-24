import java.util.HashMap;
import java.util.Map;

public class SubarrayDistinctElementsSumOfSquaresII {
    // LC 2916, redo 2026-11-23 (was on the bitter list). d^2 does not split into per-element contributions,
    // so sweep the right end i and keep d(l) = #distinct in [l, i] for every l in a lazy segment tree.
    // a[i] only adds 1 to d(l) for l in (prev[a[i]], i]; (d+1)^2 - d^2 = 2d + 1, so the global sum of squares
    // grows by 2 * (range sum of d) + range length. The tree only needs the first power (sum + lazy add);
    // query the old sum before the range add.
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

        SegTree(long[] a) {
            this.n = a.length;
            this.tree = new Node[4 * n + 5];
            this.lazy = new long[4 * n + 5];
            build(1, 0, n - 1, a);
        }

        private void build(int idx, int l, int r, long[] a) {
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
            return new Node(left.l, right.r, Math.min(left.min, right.min), Math.max(left.max, right.max),
                    left.sum + right.sum);
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
    }

    private long Mod = (long) (1e9 + 7);

    public int sumCounts(int[] a) {
        int n = a.length;
        Map<Integer, Integer> pm = new HashMap<>();
        long[] dv = new long[n];
        SegTree seg = new SegTree(dv);
        long cur = 0;
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            int prev = pm.getOrDefault(v, -1);
            Node node = seg.query(prev + 1, i - 1);
            long sum = (node == null) ? 0 : node.sum;
            long cnt = i - 1 - prev;
            long delta = 2 * sum + cnt;
            cur += delta;
            cur += 1;
            cur %= Mod;
            seg.rangeAdd(prev + 1, i, 1);
            res += cur;
            res %= Mod;
            pm.put(v, i);
        }
        return (int) res;
    }
}
