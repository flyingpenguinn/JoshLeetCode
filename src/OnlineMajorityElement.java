import base.ArrayUtils;

import java.util.*;

public class OnlineMajorityElement {
    class MajorityChecker {
        // using segment tree and binary search. binary search can check if an element is majority or not in logn time
        // not calculating real frequency here. the counter is just the voting algo "survived count"
// we are not negating every "non majority" element yet so if this number can survive it's the best candidate. otherwise there is no majority at all
        class Node {
            int l;
            int r;
            int[] mc; // majority and counter
            Node left;
            Node right;

            public Node(int l, int r, int[] mc) {
                this.l = l;
                this.r = r;
                this.mc = mc;
            }
        }

        private Node root = null;
        private Map<Integer, List<Integer>> indices = new HashMap<>();

        public MajorityChecker(int[] a) {
            for (int i = 0; i < a.length; i++) {
                indices.computeIfAbsent(a[i], k -> new ArrayList<>()).add(i);
            }
            root = build(a, 0, a.length - 1);
        }

        private int[] merge(int[] left, int[] right) {
            if (left[0] == right[0]) {
                return new int[]{left[0], left[1] + right[1]};
            } else if (left[1] > right[1]) {
                return new int[]{left[0], left[1] - right[1]};
            } else {
                return new int[]{right[0], right[1] - left[1]};
            }
        }

        private Node build(int[] a, int l, int r) {
            if (l == r) {
                return new Node(l, r, new int[]{a[l], 1});
            }
            int len = r - l + 1;
            int mid = l + (r - l) / 2;
            Node left = build(a, l, mid);
            Node right = build(a, mid + 1, r);
            Node cur = new Node(l, r, new int[]{0, 0});
            cur.mc = merge(left.mc, right.mc);
            cur.left = left;
            cur.right = right;
            //  System.out.println(cur.l+" "+cur.r+" "+cur.m+" "+cur.c);
            return cur;
        }

        public int query(int l, int r, int t) {
            int[] res = dfs(root, l, r);
            if (res[1] > 0) {
                int cand = res[0];
                //   System.out.println("cands..."+l+" "+r+" "+cand);
                List<Integer> ls = indices.get(cand);

                int set1 = biceil(ls, l);
                int set2 = bifloor(ls, r);
                //   System.out.println("ls..."+ls+" "+set1+" "+set2);
                if (set2 - set1 + 1 >= t) {
                    return cand;
                }
            }
            return -1;
        }

        private int[] dfs(Node n, int l, int r) {
            // System.out.println(n.l+" "+n.r+" "+l+" "+r);
            // n.l <=l, n.r >=r at all times
            if (n.l == l && n.r == r) {
                return n.mc;
            } else {
                int mid = n.left.r;
                if (l >= mid + 1) {
                    return dfs(n.right, l, r);
                } else if (r <= mid) {
                    return dfs(n.left, l, r);
                }
                // l<=mid, r>=mid+1
                int[] left = dfs(n.left, l, mid);
                int[] right = dfs(n.right, mid + 1, r);
                return merge(left, right);
            }
        }

        private int biceil(List<Integer> a, int t) {
            int l = 0;
            int u = a.size() - 1;
            while (l <= u) {
                int m = l + (u - l) / 2;
                if (a.get(m) >= t) {
                    u = m - 1;
                } else {
                    l = m + 1;
                }
            }
            return l;
        }


        private int bifloor(List<Integer> a, int t) {
            int l = 0;
            int u = a.size() - 1;
            while (l <= u) {
                int m = l + (u - l) / 2;
                if (a.get(m) <= t) {
                    l = m + 1;
                } else {
                    u = m - 1;
                }
            }
            return u;
        }
    }
}
