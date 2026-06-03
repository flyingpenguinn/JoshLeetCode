
import java.util.Arrays;

class BookMyShow {

    // each row is left to right consecutive
    // use seg tree max/sum for maintainence
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
        BookMyShow.Node[] tree;
        long[] lazy;
        int n;

        SegTree(int[] a) {
            this.n = a.length;
            this.tree = new BookMyShow.Node[4 * n + 5];
            this.lazy = new long[4 * n + 5];
            build(1, 0, n - 1, a);
        }

        private void build(int idx, int l, int r, int[] a) {
            if (l == r) {
                long v = a[l];
                tree[idx] = new BookMyShow.Node(l, r, v, v, v);
                return;
            }

            int mid = l + (r - l) / 2;
            build(idx * 2, l, mid, a);
            build(idx * 2 + 1, mid + 1, r, a);

            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }

        private BookMyShow.Node merge(BookMyShow.Node left, BookMyShow.Node right) {
            return new BookMyShow.Node(
                    left.l,
                    right.r,
                    Math.min(left.min, right.min),
                    Math.max(left.max, right.max),
                    left.sum + right.sum
            );
        }

        private void apply(int idx, long delta) {
            BookMyShow.Node cur = tree[idx];

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

        public void pointAdd(int q, long delta) {
            rangeAdd(1, q, q, delta);
        }

        public void rangeAdd(int ql, int qr, long delta) {
            rangeAdd(1, ql, qr, delta);
        }

        private void rangeAdd(int idx, int ql, int qr, long delta) {
            BookMyShow.Node cur = tree[idx];

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

        public BookMyShow.Node query(int ql, int qr) {
            return query(1, ql, qr);
        }

        private BookMyShow.Node query(int idx, int ql, int qr) {
            BookMyShow.Node cur = tree[idx];

            if (qr < cur.l || cur.r < ql) {
                return null;
            }

            if (ql <= cur.l && cur.r <= qr) {
                return cur;
            }

            push(idx);

            BookMyShow.Node left = query(idx * 2, ql, qr);
            BookMyShow.Node right = query(idx * 2 + 1, ql, qr);

            if (left == null) {
                return right;
            }
            if (right == null) {
                return left;
            }
            return merge(left, right);
        }
    }

    private int[] rows;
    int n;
    int m;
    private BookMyShow.SegTree seg;


    public BookMyShow(int n, int m) {
        this.n = n;
        this.m = m;
        rows = new int[n];
        int[] emptyrows = new int[n];
        Arrays.fill(emptyrows, m);
        seg = new BookMyShow.SegTree(emptyrows);
    }

    public int[] gather(int k, int maxRow) {
        int l = 0;
        int u = maxRow;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            long cmax = seg.query(0, mid).max;
            if (cmax >= k) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (l == maxRow + 1) {
            return new int[0];
        } else {
            int oldrowsl = rows[l];
            rows[l] += k;
            seg.pointAdd(l, -k);
            while (firstnonfull < n && rows[firstnonfull] == m) {
                ++firstnonfull;
            }
            return new int[]{l, oldrowsl};
        }
    }

    private int firstnonfull = 0;

    public boolean scatter(int k, int maxRow) {
        int l = 0;
        int u = maxRow;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            long csum = seg.query(0, mid).sum;
            if (csum >= k) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (l == maxRow + 1) {
            return false;
        } else {
            int rem = k;
            for (int i = firstnonfull; i <= maxRow; ++i) {
                int diff = m - rows[i];
                if (diff <= rem) {
                    rows[i] += diff;
                    rem -= diff;
                    seg.pointAdd(i, -diff);
                } else {
                    rows[i] += rem;
                    seg.pointAdd(i, -rem);
                    break;
                }
            }
            while (firstnonfull < n && rows[firstnonfull] == m) {
                ++firstnonfull;
            }
            return true;
        }
    }
}
