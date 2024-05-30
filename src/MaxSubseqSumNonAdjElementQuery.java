import base.ArrayUtils;

import java.util.Arrays;

import static java.lang.Integer.max;


public class MaxSubseqSumNonAdjElementQuery {
    // segment tree with multiple info stored, still point update !
    private int Min = (int) -1e9;

    void build(int i, int l, int r, int[] a, int[][][] seg) {
        if (l == r) {
            seg[i][0][0] = 0;
            seg[i][0][1] = Min;
            seg[i][1][0] = Min;
            seg[i][1][1] = a[l];
            return;
        }
        int mid = l + (r - l) / 2;
        build(2 * i + 1, l, mid, a, seg);
        build(2 * i + 2, mid + 1, r, a, seg);
        merge(i, seg, l, r);
    }

    private void merge(int i, int[][][] seg, int l, int r) {
        final int left = 2 * i + 1;
        final int right = 2 * i + 2;
        seg[i][0][0] = max(seg[left][0][0] + seg[right][0][0], max(seg[left][0][1] + seg[right][0][0], seg[left][0][0] + seg[right][1][0]));
        seg[i][0][1] = max(seg[left][0][0] + seg[right][0][1], max(seg[left][0][1] + seg[right][0][1], seg[left][0][0] + seg[right][1][1]));
        seg[i][1][0] = max(seg[left][1][0] + seg[right][0][0], max(seg[left][1][1] + seg[right][0][0], seg[left][1][0] + seg[right][1][0]));
        seg[i][1][1] = max(seg[left][1][0] + seg[right][0][1], max(seg[left][1][1] + seg[right][0][1], seg[left][1][0] + seg[right][1][1]));
    }

    void update(int i, int l, int r, int[] a, int[][][] seg, int k, int val) {
        if (l > k || r < k) {
            return;
        }
        if (l == k && r == k) {
            seg[i][0][0] = 0;
            seg[i][1][1] = val;
            return;
        }
        int mid = l + (r - l) / 2;
        update(2 * i + 1, l, mid, a, seg, k, val);
        update(2 * i + 2, mid + 1, r, a, seg, k, val);
        merge(i, seg, l, r);
    }

    int maximumSumSubsequence(int[] a, int[][] queries) {
        int n = a.length;
        int[][][] seg = new int[4 * n + 1][2][2];
        int mod = (int) (1e9 + 7);
        build(0, 0, n - 1, a, seg);
        int m = queries.length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            update(0, 0, n - 1, a, seg, queries[i][0], queries[i][1]);
            ans = (ans + max(seg[0][0][0], max(seg[0][0][1], max(seg[0][1][0], seg[0][1][1])))) % mod;
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxSubseqSumNonAdjElementQuery sol = new MaxSubseqSumNonAdjElementQuery();


        int[] nums2 = {0, -1};
        int[][] queries2 = {{0, -5}};
        System.out.println(sol.maximumSumSubsequence(nums2, queries2)); // Output: 0

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