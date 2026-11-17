import java.util.Arrays;
import java.util.Random;

public class FindXvalueOfArrayII {
    // 2026-11-16 redo (review of the 11/11 discussion). 2 WAs: (1) negative mod after adding a negative delta;
    // (2) forgot a[index] = value. Both come from adapting a range-ADD template to a point-SET problem:
    // pointSet(index, value % k) on the leaf removes both bugs.
    // Rewrite: removing a suffix = keeping a prefix. k <= 5 -> node keeps prod mod k + cnt[k] = #prefixes by residue.
    // merge: prefixes of left, plus (left.prod * each prefix residue of right).
    static class SegTree {
        private int k = 0;

        class Node {
            int l, r;
            long prod;
            long[] cnt;

            Node(int l, int r, long prod, long[] cnt) {
                this.l = l;
                this.r = r;
                this.prod = prod;
                this.cnt = cnt;
            }

            int len() {
                return r - l + 1;
            }
        }

        Node[] tree;
        long[] lazy;
        int n;

        SegTree(int[] a, int k) {
            this.n = a.length;
            this.k = k;
            this.tree = new Node[4 * n + 5];
            this.lazy = new long[4 * n + 5];
            build(1, 0, n - 1, a);
        }

        private void build(int idx, int l, int r, int[] a) {
            if (l == r) {
                long v = a[l];
                long[] ccnt = new long[k];
                ccnt[(int) (v % k)] = 1;
                tree[idx] = new Node(l, r, v, ccnt);

                return;
            }

            int mid = l + (r - l) / 2;
            build(idx * 2, l, mid, a);
            build(idx * 2 + 1, mid + 1, r, a);

            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }

        private Node merge(Node left, Node right) {
            long newprod = left.prod * right.prod;
            newprod %= k;
            long[] newcnt = Arrays.copyOf(left.cnt, k);
            for (int i = 0; i < k; ++i) {
                long cprod = left.prod * i;
                int cprodmod = (int) (cprod % k);
                newcnt[cprodmod] += right.cnt[i];
            }
            return new Node(
                    left.l,
                    right.r,
                    newprod,
                    newcnt
            );
        }

        private void apply(int idx, long delta) {
            Node cur = tree[idx];
            int nv = (int) ((cur.prod + delta) % k);
            if (nv < 0) {
                nv += k;
            }
            cur.prod = nv;
            long[] ncnt = new long[k];

            ncnt[nv] = 1;
            cur.cnt = ncnt;
        }

        public void pointAdd(int q, long delta) {
            rangeAdd(1, q, q, delta);
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

    public int[] resultArray(int[] a, int k, int[][] queries) {
        int n = a.length;
        SegTree seg = new SegTree(a, k);
        int qn = queries.length;
        int[] res = new int[qn];
        for (int i = 0; i < qn; ++i) {
            int[] q = queries[i];
            int index = q[0];
            int value = q[1];
            int start = q[2];
            int x = q[3];
            int old = a[index];
            int delta = value - old;
            seg.pointAdd(index, delta);
            SegTree.Node node = seg.query(start, n - 1);
            res[i] = (int) node.cnt[x];
            a[index] = value;
        }
        return res;
    }

    private static int[] brute(int[] a0, int k, int[][] qs) {
        int[] a = a0.clone();
        int[] res = new int[qs.length];
        for (int i = 0; i < qs.length; ++i) {
            a[qs[i][0]] = qs[i][1];
            long p = 1;
            int c = 0;
            for (int j = qs[i][2]; j < a.length; ++j) {
                p = p * a[j] % k;
                if (p == qs[i][3]) {
                    ++c;
                }
            }
            res[i] = c;
        }
        return res;
    }

    public static void main(String[] args) {
        Random rd = new Random(3525);
        int bad = 0;
        for (int t = 0; t < 3000; ++t) {
            int n = 1 + rd.nextInt(8);
            int k = 1 + rd.nextInt(5);
            int[] a = new int[n];
            for (int i = 0; i < n; ++i) {
                a[i] = 1 + rd.nextInt(1000000000);
            }
            int[][] qs = new int[1 + rd.nextInt(6)][];
            for (int i = 0; i < qs.length; ++i) {
                qs[i] = new int[]{rd.nextInt(n), 1 + rd.nextInt(1000000000), rd.nextInt(n), rd.nextInt(k)};
            }
            if (!Arrays.equals(brute(a, k, qs), new FindXvalueOfArrayII().resultArray(a.clone(), k, qs))) {
                ++bad;
            }
        }
        System.out.println("bad=" + bad);
    }
}
