
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class BlockPlacementQueries {

    // typical segment tree
    private int n = 50000;

    public List<Boolean> getResults(int[][] q) {
        List<Boolean> res = new ArrayList<>();
        int qn = q.length;
        update(0, n, 1, 0, n);
        update(n, 0, 1, 0, n);
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(n);

        for (int i = 0; i < qn; i++) {
            if (q[i][0] == 1) {
                int x = q[i][1];
                int prev = set.floor(x);
                int next = set.ceiling(x);
                set.add(x);
                update(prev, x - prev, 1, 0, n);
                update(x, next - x, 1, 0, n);
            } else { //q[i][0] == 2
                int x = q[i][1];
                int sz = q[i][2];
                if (sz > x) {
                    res.add(false);
                    continue;
                }
                int max = query(1, 0, n, 0, x - sz);
                boolean cres = max >= sz;
                res.add(cres);
            }
        }

        return res;
    }


    private int[] seg = new int[3 * n];

    // the index of l...r is idx
    private int query(int idx, int l, int r, int ql, int qr) {
        int mid = l + (r - l) / 2;
        int res = 0;
        if (ql <= l && r <= qr) {
            return seg[idx];
        }
        if (ql <= mid) {
            res = Math.max(res, query(idx * 2, l, mid, ql, qr));
        }
        if (mid < qr) {
            res = Math.max(res, query(idx * 2 + 1, mid + 1, r, ql, qr));
        }
        return res;
    }

    private void update(int ai, int v, int idx, int l, int r) {
        int mid = l + (r - l) / 2;
        if (l == r) {
            seg[idx] = v;
        } else {
            if (ai <= mid) {
                update(ai, v, idx * 2, l, mid);
            } else {
                update(ai, v, idx * 2 + 1, mid + 1, r);
            }
            seg[idx] = Math.max(seg[idx * 2], seg[idx * 2 + 1]);
        }
    }


    public static void main(String[] args) {
        System.out.println(new BlockPlacementQueries().getResults(new int[][]{{1, 7}, {2, 7, 6}, {1, 2}, {2, 7, 5}, {2, 7, 6}}));
    }
}


class BlockPlacementQueriesRangeSegtree {
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

    public List<Boolean> getResults(int[][] queries) {
        int qn = queries.length;
        List<Boolean> res = new ArrayList<>();
        TreeSet<int[]> set = new TreeSet<>((x, y) -> Integer.compare(x[0], y[0]));
        int maxqv = 0;
        for (int[] q : queries) {
            if (q[0] == 1) {
                maxqv = Math.max(maxqv, q[1]);
            } else {
                maxqv = Math.max(maxqv, q[1] + q[2]);
            }
        }
        int[] v = new int[maxqv + 1];
        SegTree seg = new SegTree(v);
        set.add(new int[]{0, maxqv});
        seg.rangeAdd(0, maxqv, maxqv);
        for (int[] q : queries) {
            if (q[0] == 1) {
                int ob = q[1];
                int[] found = set.floor(new int[]{ob, ob});
                int foundstart = found[0];
                int foundend = found[1];
                set.remove(new int[]{foundstart, foundend});
                int old = foundend - foundstart;
                int new1 = ob - foundstart;
                int new2 = foundend - ob;
                seg.rangeAdd(foundstart, ob, (new1 - old));
                seg.rangeAdd(ob, foundend, (new2 - old));
                set.add(new int[]{foundstart, ob});
                set.add(new int[]{ob, foundend});
            } else {
                int x = q[1];
                int sz = q[2];
                int[] found = set.floor(new int[]{x, x});
                int foundstart = found[0];
                int foundend = found[1];
                if (foundstart + sz <= x) {
                    res.add(true);
                } else {
                    Node query = seg.query(0, foundstart - 1);
                    if (query != null) {
                        int maxv = (int) query.max;
                        if (maxv >= sz) {
                            res.add(true);
                        } else {
                            res.add(false);
                        }
                    } else {
                        res.add(false);
                    }
                }
            }
        }
        return res;

    }
}