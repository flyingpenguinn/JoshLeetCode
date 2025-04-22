import base.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RangeXorWithSubarrayReversal {
    // segment tree and treap. use treap for range updates
    class Node {
        int v;      // value
        int xor;    // subtree XOR
        int size;   // subtree size
        int pr;     // heap priority
        boolean rev;// lazy reversal flag
        Node left, right;

        Node(int v) {
            this.v = this.xor = v;
            this.size = 1;
            this.pr = rnd.nextInt();
        }
    }

    class SegTree {
        private Node root;

        // build by merging one by one
        public SegTree(int[] a) {
            root = null;
            for (int v : a) {
                root = merge(root, new Node(v));
            }
        }

        // helpers
        private int sz(Node t) {
            return t == null ? 0 : t.size;
        }

        private void pull(Node t) {
            t.size = 1 + sz(t.left) + sz(t.right);
            t.xor = (t.left != null ? t.left.xor : 0)
                    ^ t.v
                    ^ (t.right != null ? t.right.xor : 0);
        }

        private void push(Node t) {
            if (t != null && t.rev) {
                t.rev = false;
                Node tmp = t.left;
                t.left = t.right;
                t.right = tmp;
                if (t.left != null) t.left.rev ^= true;
                if (t.right != null) t.right.rev ^= true;
            }
        }

        // merge & split
        private Node merge(Node a, Node b) {
            if (a == null) return b;
            if (b == null) return a;
            if (a.pr < b.pr) {
                push(a);
                a.right = merge(a.right, b);
                pull(a);
                return a;
            } else {
                push(b);
                b.left = merge(a, b.left);
                pull(b);
                return b;
            }
        }

        // splits t into [0..k-1] and [k..end]
        private Node[] split(Node t, int k) {
            if (t == null) return new Node[]{null, null};
            push(t);
            if (sz(t.left) >= k) {
                Node[] p = split(t.left, k);
                t.left = p[1];
                pull(t);
                return new Node[]{p[0], t};
            } else {
                Node[] p = split(t.right, k - sz(t.left) - 1);
                t.right = p[0];
                pull(t);
                return new Node[]{t, p[1]};
            }
        }

        // point update: nums[pos] = nv
        public void update(int pos, int nv) {
            Node[] A = split(root, pos);
            Node[] B = split(A[1], 1);     // B[0] is the single node at index pos
            B[0].v = nv;
            B[0].xor = nv;
            root = merge(A[0], merge(B[0], B[1]));
        }

        // range XOR query [l..r]
        public int query(int l, int r) {
            Node[] A = split(root, l);
            Node[] B = split(A[1], r - l + 1);
            int ans = (B[0] != null ? B[0].xor : 0);
            root = merge(A[0], merge(B[0], B[1]));
            return ans;
        }

        // reverse subarray [l..r]
        public void reverse(int l, int r) {
            Node[] A = split(root, l);
            Node[] B = split(A[1], r - l + 1);
            if (B[0] != null) B[0].rev ^= true;
            root = merge(A[0], merge(B[0], B[1]));
        }
    }

    private final Random rnd = new Random();

    public int[] getResults(int[] a, int[][] qs) {
        SegTree seg = new SegTree(a);
        List<Integer> out = new ArrayList<>();
        for (int[] q : qs) {
            if (q[0] == 1) {
                seg.update(q[1], q[2]);
            } else if (q[0] == 2) {
                out.add(seg.query(q[1], q[2]));
            } else {
                seg.reverse(q[1], q[2]);
            }
        }
        int[] res = new int[out.size()];
        for (int i = 0; i < res.length; i++) res[i] = out.get(i);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new RangeXorWithSubarrayReversal().getResults(ArrayUtils.read1d("[5,2,10]"), ArrayUtils.read("[[3,2,2],[2,2,2]]"))));
        System.out.println(Arrays.toString(new RangeXorWithSubarrayReversal().getResults(ArrayUtils.read1d("[1,2,3,4,5]"), ArrayUtils.read("[[2,1,3],[1,2,10],[3,0,4],[2,0,4]]"))));
        System.out.println(Arrays.toString(new RangeXorWithSubarrayReversal().getResults(ArrayUtils.read1d("[7,8,9]"), ArrayUtils.read("[[1,0,3],[2,0,2],[3,1,2]]"))));
    }
}
