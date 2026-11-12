public class MaxSubseqSumNonAdjElementQuery {
    // 2026-11-11 redo: 1 WA (mixed i and index in the query loop). Old record: 11 submissions, 8 WA/TLE.
    // Node keeps 4 states by whether the first / last element of the segment may be chosen.
    // Merge only forbids "left's last AND right's first" both chosen.
    private static long max(long a, long b) {
        return Math.max(a, b);
    }

    static class Node {
        int l, r;
        long sum;       // first and last both allowed
        long sumno0;    // first NOT chosen, last allowed
        long sumnon1;   // first allowed, last NOT chosen
        long sumnoends; // neither first nor last chosen

        public Node(int l, int r, long sum, long sumno0, long sumnon1, long sumnoends) {
            this.l = l;
            this.r = r;
            this.sum = sum;
            this.sumno0 = sumno0;
            this.sumnon1 = sumnon1;
            this.sumnoends = sumnoends;
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
                // max(v, 0): choosing nothing is always allowed, keeps sum >= the other three
                tree[idx] = new Node(l, r, Math.max(v, 0), 0, 0, 0);
                return;
            }
            int mid = l + (r - l) / 2;
            build(idx * 2, l, mid, a);
            build(idx * 2 + 1, mid + 1, r, a);
            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }

        private Node merge(Node left, Node right) {
            long newsum = max(left.sum + right.sumno0, left.sumnon1 + right.sum);
            long newsumno0 = max(left.sumno0 + right.sumno0, left.sumnoends + right.sum);
            long newsumnon1 = max(left.sumnon1 + right.sumnon1, left.sum + right.sumnoends);
            long newsumnoends = max(left.sumno0 + right.sumnoends, left.sumnoends + right.sumnon1);
            return new Node(left.l, right.r, newsum, newsumno0, newsumnon1, newsumnoends);
        }

        public void pointSet(int q, long v) {
            pointSet(1, q, v);
        }

        private void pointSet(int idx, int q, long v) {
            Node cur = tree[idx];
            if (q < cur.l || cur.r < q) {
                return;
            }
            if (cur.l == cur.r) {
                cur.sum = Math.max(v, 0);
                return;
            }
            pointSet(idx * 2, q, v);
            pointSet(idx * 2 + 1, q, v);
            tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
        }

        public Node root() {
            return tree[1];
        }
    }

    public int maximumSumSubsequence(int[] a, int[][] queries) {
        SegTree seg = new SegTree(a);
        long res = 0;
        long mod = (long) (1e9 + 7);
        for (int[] q : queries) {
            int index = q[0];
            int v = q[1];
            seg.pointSet(index, v);
            res += seg.root().sum;
            res %= mod;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        MaxSubseqSumNonAdjElementQuery sol = new MaxSubseqSumNonAdjElementQuery();
        System.out.println(sol.maximumSumSubsequence(new int[]{3, 5, 9}, new int[][]{{1, -2}, {0, -3}})); // 21
        System.out.println(sol.maximumSumSubsequence(new int[]{0, -1}, new int[][]{{0, -5}})); // 0
    }
}

class MaxSubseqSumSegTreeAlternative{
    class Solution {
        private static final int MOD = 1_000_000_007;
        private int Min = (int)(-1e9);

        class SegmentTreeNode {
            long lr;     // pick l and r
            long l1r;    // do nto pick l, pick r
            long lr1;    // pick l, do not pick r
            long l1r1;   // do not pick l and r

            SegmentTreeNode(long lr, long l1r, long lr1, long l1r1) {
                this.lr = lr;
                this.l1r = l1r;
                this.lr1 = lr1;
                this.l1r1 = l1r1;
            }

            SegmentTreeNode() {
                this(0, 0, 0, 0);
            }
        }

        class SegmentTree {
            private SegmentTreeNode[] tree;
            private int n;

            public SegmentTree(int[] arr) {
                n = arr.length;
                tree = new SegmentTreeNode[4 * n];
                build(arr, 0, 0, n - 1);
            }

            private void build(int[] arr, int node, int start, int end) {
                if (start == end) {
                    tree[node] = new SegmentTreeNode(arr[start], Min, Min, 0);
                } else {
                    int mid = (start + end) / 2;
                    build(arr, 2 * node + 1, start, mid);
                    build(arr, 2 * node + 2, mid + 1, end);
                    tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
                }
            }

            private SegmentTreeNode merge(SegmentTreeNode left, SegmentTreeNode right) {
                long lr = Math.max(
                        Math.max(left.lr1 + right.l1r, left.lr + right.l1r),
                        left.lr1 + right.lr);
                long l1r = Math.max(
                        Math.max(left.l1r1 + right.l1r, left.l1r + right.l1r),
                        left.l1r1 + right.lr);
                long lr1 = Math.max(
                        Math.max(left.lr1 + right.l1r1, left.lr + right.l1r1),
                        left.lr1 + right.lr1);
                long l1r1 = Math.max(
                        Math.max(left.l1r1 + right.l1r1, left.l1r + right.l1r1),
                        left.l1r1 + right.lr1);
                return new SegmentTreeNode(lr, l1r, lr1, l1r1);
            }

            public void update(int index, int value, int start, int end, int node) {
                if (start == end) {
                    tree[node] = new SegmentTreeNode(value, Min, Min, 0);
                } else {
                    int mid = (start + end) / 2;
                    if (index <= mid) {
                        update(index, value, start, mid, 2 * node + 1);
                    } else {
                        update(index, value, mid + 1, end, 2 * node + 2);
                    }
                    tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2]);
                }
            }

            public SegmentTreeNode query() {
                return tree[0];
            }
        }

        public int maximumSumSubsequence(int[] nums, int[][] queries) {
            int n = nums.length;
            SegmentTree segmentTree = new SegmentTree(nums);
            long totalSum = 0;

            for (int[] query : queries) {
                int pos = query[0];
                int value = query[1];
                segmentTree.update(pos, value, 0, n - 1, 0);
                SegmentTreeNode result = segmentTree.query();
                totalSum = (totalSum + Math.max(Math.max(result.lr, result.l1r), Math.max(result.lr1, result.l1r1))) % MOD;
            }

            return (int) totalSum;
        }
    }

}