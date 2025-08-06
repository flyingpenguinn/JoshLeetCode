import java.util.ArrayList;
import java.util.List;

public class NumberOfIntegersPopcountDepthEqualToKII {

    // segtree template
    // can also use fenwick tree
    private final long Max = (long) (2e15);

    static class Node {
        int sum = 0;

        public Node(int sum) {
            this.sum = sum;
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

        public void update(int pos, int nv) {
            update(1, 0, n - 1, pos, nv);
        }

        private void update(int idx, int l, int u, int pos, int nv) {
            if (l == u) {
                t[idx] = makeleaf(nv);
                return;
            }
            int mid = l + (u - l) / 2;
            int left = 2 * idx;
            int right = 2 * idx + 1;
            if (pos <= mid) {
                update(2 * idx, l, mid, pos, nv);
            } else {
                update(2 * idx + 1, mid + 1, u, pos, nv);
            }
            t[idx] = merge(t[left], t[right]);
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
            Node cur = new Node(0);
            cur.sum = left.sum + right.sum;
            return cur;
        }

        private Node makeleaf(int v) {
            return new Node(v);
        }

        // Range query [ql..qr], returns identity‑if‑empty
        private Node query(int idx, int l, int r, int ql, int qr) {
            if (qr < l || r < ql) {
                // identity node: prod=1, cnt all zero
                return new Node(0);
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

    public int[] popcountDepth(long[] a, long[][] queries) {
        int n = a.length;
        SegTree[] sm = new SegTree[10];
        for (int i = 0; i <= 9; ++i) {
            int[] arr = new int[n];
            sm[i] = new SegTree(arr);
        }
        for (int i = 0; i < n; ++i) {
            int pv = getpop(a[i]);
            sm[pv].update(i, 1);
        }

        List<Integer> res = new ArrayList<>();
        for (long[] q : queries) {
            long type = q[0];
            if (type == 1) {
                int l = (int) q[1];
                int r = (int) q[2];
                int k = (int) q[3];
                SegTree seg = sm[k];
                if (seg == null) {
                    res.add(0);
                } else {
                    Node cres = seg.query(1, 0, n - 1, l, r);
                    res.add(cres.sum);
                }

            } else {
                int idx = (int) q[1];
                long newv = q[2];
                long oldv = a[idx];
                int oldpop = getpop(oldv);
                int newpop = getpop(newv);
                if (oldpop == newpop) {
                    continue;
                }
                SegTree oldseg = sm[oldpop];
                oldseg.update(idx, 0);
                SegTree newSeg = sm[newpop];
                newSeg.update(idx, 1);
                a[idx] = newv;
            }
        }
        int[] rres = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            rres[i] = res.get(i);
        }
        return rres;
    }


    private int getpop(long n) {
        int res = 0;
        while (n > 1) {
            n = Long.bitCount(n);
            ++res;
        }
        return res;
    }
}
