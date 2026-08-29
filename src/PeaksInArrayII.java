public class PeaksInArrayII {
    static class Node {
        int l, r;
        int len;
        int pre, suf;
        long zero;

        Node(int l, int r, int len, int pre, int suf, long zero) {
            this.l = l;
            this.r = r;
            this.len = len;
            this.pre = pre;
            this.suf = suf;
            this.zero = zero;
        }
    }

    static class SegTree {
        Node[] tree;

        SegTree(int[] a) {
            int n = a.length;
            tree = new Node[4 * n + 5];
            build(1, 0, n - 1, a);
        }

        private void build(int idx, int l, int r, int[] a) {
            if (l == r) {
                int z = a[l] == 0 ? 1 : 0;
                tree[idx] = new Node(l, r, 1, z, z, z);
                return;
            }

            int mid = l + (r - l) / 2;
            build(idx * 2, l, mid, a);
            build(idx * 2 + 1, mid + 1, r, a);

            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }

        private Node merge(Node left, Node right) {
            int pre = left.pre;
            if (left.pre == left.len) {
                pre += right.pre;
            }

            int suf = right.suf;
            if (right.suf == right.len) {
                suf += left.suf;
            }

            long zero = left.zero
                    + right.zero
                    + (long) left.suf * right.pre;

            return new Node(
                    left.l,
                    right.r,
                    left.len + right.len,
                    pre,
                    suf,
                    zero
            );
        }

        public void pointUpdate(int q, int v) {
            pointUpdate(1, q, v);
        }

        private void pointUpdate(int idx, int q, int v) {
            Node cur = tree[idx];

            if (cur.l == cur.r) {
                int z = v == 0 ? 1 : 0;
                tree[idx] = new Node(cur.l, cur.r, 1, z, z, z);
                return;
            }

            int mid = cur.l + (cur.r - cur.l) / 2;

            if (q <= mid) {
                pointUpdate(idx * 2, q, v);
            } else {
                pointUpdate(idx * 2 + 1, q, v);
            }

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

    public long[] countOfPeaks(int[] nums, int[][] queries) {
        int n = nums.length;

        int[] peak = new int[n];

        for (int i = 1; i + 1 < n; ++i) {
            peak[i] = isPeak(nums, i) ? 1 : 0;
        }

        SegTree seg = new SegTree(peak);

        int qcnt = 0;
        for (int[] q : queries) {
            if (q[0] == 1) {
                ++qcnt;
            }
        }

        long[] res = new long[qcnt];
        int ri = 0;

        for (int[] q : queries) {
            if (q[0] == 1) {
                int l = q[1];
                int r = q[2];

                if (r - l < 2) {
                    res[ri++] = 0;
                    continue;
                }

                Node cur = seg.query(l + 1, r - 1);

                long len = cur.len;
                long all = len * (len + 1) / 2;

                res[ri++] = all - cur.zero;
            } else {
                int x = q[1];
                int v = q[2];

                nums[x] = v;

                for (int i = x - 1; i <= x + 1; ++i) {
                    if (i <= 0 || i >= n - 1) {
                        continue;
                    }

                    peak[i] = isPeak(nums, i) ? 1 : 0;
                    seg.pointUpdate(i, peak[i]);
                }
            }
        }

        return res;
    }

    private boolean isPeak(int[] a, int i) {
        return a[i] > a[i - 1] && a[i] > a[i + 1];
    }
}
