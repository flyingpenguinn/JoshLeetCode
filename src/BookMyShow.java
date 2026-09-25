import java.util.Arrays;

class BookMyShow {
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

        int n;

        SegTree(int[] a) {
            this.n = a.length;
            this.tree = new Node[4 * n + 5];

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
            if (cur.len() > 1) {
                throw new IllegalArgumentException("Only allowed to be point add");
            }
            cur.min += delta;
            cur.max += delta;
            cur.sum += delta * cur.len();

        }


        public void pointAdd(int q, long delta) {
            pointAdd(1, q, q, delta);
        }

        private void pointAdd(int idx, int ql, int qr, long delta) {
            Node cur = tree[idx];

            if (qr < cur.l || cur.r < ql) {
                return;
            }

            if (ql <= cur.l && cur.r <= qr) {
                apply(idx, delta);
                return;
            }


            pointAdd(idx * 2, ql, qr, delta);
            pointAdd(idx * 2 + 1, ql, qr, delta);

            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }

        public Node lookup(int ql, int qr, int t) {
            return lookup(1, ql, qr, t);
        }

        private Node lookup(int idx, int ql, int qr, int t) {
            Node cur = tree[idx];

            if (qr < cur.l || cur.r < ql) {
                return null;
            }
            if (cur.len() == 1) {
                if (cur.sum >= t) {
                    return cur;
                } else {
                    return null;
                }
            }
            if (tree[idx * 2].max >= t) {
                return lookup(idx * 2, ql, qr, t);
            } else if (tree[idx * 2 + 1].max >= t) {
                return lookup(idx * 2 + 1, ql, qr, t);
            } else {
                return null;
            }
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

    private int[] rows;
    private int m;
    private int n;
    private int start = 0;
    private SegTree seg;

    public BookMyShow(int n, int m) {
        rows = new int[n];
        Arrays.fill(rows, m);
        this.m = m;
        seg = new SegTree(rows);
    }

    public int[] gather(int k, int maxRow) {
        Node cur = seg.lookup(0, maxRow, k);
        if (cur != null) {
            int len = (int) cur.sum;
            int start = m - len;
            seg.pointAdd(cur.l, -k);
            return new int[]{cur.l, start};
        } else {
            return new int[0];
        }
    }

    public boolean scatter(int k, int maxRow) {
        Node cur = seg.query(0, maxRow);
        if (cur.sum < k) {
            return false;
        }
        Node start = seg.lookup(0, maxRow, 1);
        // we must terminate quickly for k
        for (int j = start.l; j <= maxRow && k > 0; ++j) {
            Node rem = seg.query(j, j);
            int taken = Math.min((int) rem.sum, k);
            seg.pointAdd(j, -taken);
            k -= taken;
        }
        return true;
    }
}