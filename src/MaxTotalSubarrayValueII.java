import java.util.PriorityQueue;

public class MaxTotalSubarrayValueII {
    // real segtree template!
    private final long Max = (long) (2e15);

    static class Node {
        long max = 0;
        long min = 0;

        public Node(int max, int min) {
            this.max = max;
            this.min = min;
        }
    }


    static class SegTree {
        Node[] t;
        int n = 0;

        public SegTree(int[] a) {
            this.n = a.length;
            this.t = new Node[4 * n + 1];
            build(a, 1, 0, a.length - 1);
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
            Node cur = new Node(0, 0);
            cur.max = Math.max(left.max, right.max);
            cur.min = Math.min(left.min, right.min);
            return cur;
        }

        private Node makeleaf(int v) {
            return new Node(v, v);
        }

        private Node query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
        }

        // Range query [ql..qr], returns identity‑if‑empty
        private Node query(int idx, int l, int r, int ql, int qr) {
            if (qr < l || r < ql) {
                return new Node(Integer.MIN_VALUE, Integer.MAX_VALUE);
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

    class Item {
        int start;
        int end;
        long v;

        public Item(int start, int end, long v) {
            this.start = start;
            this.end = end;
            this.v = v;
        }
    }

    public long maxTotalValue(int[] a, int k) {
        int n = a.length;
        SegTree seg = new SegTree(a);
        PriorityQueue<Item> pq = new PriorityQueue<>((x, y) -> Long.compare(y.v, x.v));
        for (int i = 0; i < n; ++i) {
            long v = getValue(i, n - 1, seg);
            pq.offer(new Item(i, n - 1, v));
        }
        long res = 0;
        while (k > 0 && !pq.isEmpty()) {
            Item top = pq.poll();
            res += top.v;
            int start = top.start;
            int end = top.end;
            if (start == end) {
                continue;
            }
            long nv = getValue(start, end - 1, seg);
            pq.offer(new Item(start, end - 1, nv));
            --k;
        }
        return res;
    }

    protected long getValue(int i, int j, SegTree seg) {
        Node nd = seg.query(i, j);
        return nd.max - nd.min;
    }
}
