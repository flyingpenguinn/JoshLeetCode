import java.util.HashMap;
import java.util.Map;

public class SubarrayDistinctElementsSumOfSquaresII {
    class Node {
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

    class SegTree {
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
            long newsum = left.sum + right.sum;

            return new Node(
                    left.l,
                    right.r,
                    Math.min(left.min, right.min),
                    Math.max(left.max, right.max),
                    newsum
            );

        }

        private void apply(int idx, long delta) {
            Node cur = tree[idx];

            cur.min += delta;
            cur.max += delta;
            int clen = cur.len();
            cur.sum += delta * clen;
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

        public void pointAdd(int q, long delta) {
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
    }

    public int sumCounts(int[] a) {
        int n = a.length;
        long[] dp = new long[n];
        Map<Integer, Integer> last = new HashMap<>();
        int[] cnt = new int[n];
        SegTree seg = new SegTree(cnt);
        long res = 0;
        long mod = (long) (1e9 + 7);
        // 第 i 轮结束后，Segment Tree 中的 cnt[j] 恰好等于 distinct(a[j..i])
        for (int i = 0; i < n; ++i) {
            int v = a[i];
            int pre = last.getOrDefault(v, -1);

            long base = i == 0 ? 0 : dp[i - 1];
            Node node = seg.query(pre + 1, i - 1);
            long delta = 1;
            if (node != null) {

                long sum = node.sum;
                long len = i - pre - 1;
                delta += 2 * sum + len;
            }
            dp[i] = base + delta;

            res += dp[i];
            res %= mod;
            last.put(v, i);
            seg.rangeAdd(pre + 1, i, 1);
        }
        return (int) res;
    }
}
