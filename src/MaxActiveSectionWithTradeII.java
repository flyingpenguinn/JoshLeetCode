import base.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class MaxActiveSectionWithTradeII {
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
            if (n > 0) {
                build(1, 0, n - 1, a);
            }
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
            if (cur == null) {
                return null;
            }
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

    class Seg {
        int start;
        int end;
        int len;

        public Seg(int start, int end, int len) {
            this.start = start;
            this.end = end;
            this.len = len;
        }
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        s = "1" + s + "1";

        char[] c = s.toCharArray();
        int n = c.length;
        int ones = 0;

        List<Seg> zeros = new ArrayList<>();
        int i = 0;
        while (i < n) {
            if (c[i] == '1') {
                ++ones;
                ++i;
                continue;
            }
            int j = i;
            while (j < n && c[j] == '0') {
                ++j;
            }
            int len = j - i;
            zeros.add(new Seg(i, j - 1, len));
            i = j;
        }
        ones -= 2;
        int[] pairs = new int[Math.max(0, zeros.size() - 1)];
        for (i = 0; i + 1 < zeros.size(); ++i) {
            pairs[i] = zeros.get(i).len + zeros.get(i + 1).len;
        }
        SegTree seg = new SegTree(pairs);
        List<Integer> res = new ArrayList<>();
        for (int[] q : queries) {

            int l = q[0] + 1;
            int r = q[1] + 1;

            int leftstart = binaryfirstbiggerstart(zeros, l); // first fully enclosed pair
            int rightend = binarylastsmallerend(zeros, r);
            if (leftstart > rightend) {
                int l1 = binarylastsmallerstart(zeros, l);
                int l2 = binarylastsmallerstart(zeros, r);
                if (l1 < l2 && l1 >= 0 && l1 < zeros.size() && l2 < zeros.size() && zeros.get(l1).end >= l && zeros.get(l2).start <= r) {
                    int p1 = zeros.get(l1).end - l + 1;
                    int p2 = r - zeros.get(l2).start + 1;
                    int cres = p1 + p2 + ones;
                    res.add(cres);
                } else {
                    res.add(ones);
                }
                continue;
            }
            int cres = 0;
            if (leftstart < rightend) {


                Node cur = seg.query(leftstart, rightend - 1);
                if (cur != null) {
                    cres = (int) cur.max;
                }
            }
            if (leftstart < zeros.size() && leftstart - 1 >= 0 && zeros.get(leftstart - 1).end >= l) {
                int leftpart = zeros.get(leftstart - 1).end - l + 1;
                int cleft = leftpart + zeros.get(leftstart).len;
                cres = Math.max(cres, cleft);
            }
            if (rightend >= 0 && rightend + 1 < zeros.size() && zeros.get(rightend + 1).start <= r) {
                int rightpart = r - zeros.get(rightend + 1).start + 1;
                int cright = rightpart + zeros.get(rightend).len;
                cres = Math.max(cres, cright);
            }
            int c1 = cres + ones;
            res.add(c1);
        }
        return res;
    }

    private int binarylastsmallerstart(List<Seg> a, int t) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid).start <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;

    }

    private int binarylastsmallerend(List<Seg> a, int t) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid).end <= t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    private int binaryfirstbiggerstart(List<Seg> a, int t) {
        int l = 0;
        int u = a.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a.get(mid).start >= t) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    static void main() {
        System.out.println(new MaxActiveSectionWithTradeII().maxActiveSectionsAfterTrade("0101110001101", ArrayUtils.read("[[0,7]]")));
        System.out.println(new MaxActiveSectionWithTradeII().maxActiveSectionsAfterTrade("00000100", ArrayUtils.read("[[5,6]]")));
        System.out.println(new MaxActiveSectionWithTradeII().maxActiveSectionsAfterTrade("1100", ArrayUtils.read("[[3,3],[1,2]]")));
        System.out.println(new MaxActiveSectionWithTradeII().maxActiveSectionsAfterTrade("0001000000", ArrayUtils.read("[[2,7],[8,9],[2,6],[8,8],[6,9]]")));
        System.out.println(new MaxActiveSectionWithTradeII().maxActiveSectionsAfterTrade("110010", ArrayUtils.read("[[3,3]]")));
        System.out.println(new MaxActiveSectionWithTradeII().maxActiveSectionsAfterTrade("00", ArrayUtils.read("[[1,1],[0,0],[0,1]]")));
        System.out.println(new MaxActiveSectionWithTradeII().maxActiveSectionsAfterTrade("0100", ArrayUtils.read("[[0,3],[0,2],[1,3],[2,3]]")));
        System.out.println(new MaxActiveSectionWithTradeII().maxActiveSectionsAfterTrade("1", ArrayUtils.read("[[0,0]]")));


    }
}
