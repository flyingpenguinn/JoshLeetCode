import base.ArrayUtils;

import java.util.*;

public class OnlineMajorityElement {
    // binary search a candidate. try 10 random candidates
    class MajorityChecker {

        private Map<Integer, List<Integer>> m = new HashMap<>();
        private Random rand = new Random();
        private int[] a;

        public MajorityChecker(int[] a) {
            this.a = a;
            for (int i = 0; i < a.length; i++) {
                m.computeIfAbsent(a[i], k -> new ArrayList<>()).add(i);
            }
        }

        public int query(int left, int right, int threshold) {
            int len = right - left + 1;
            int tries = 10;
            while (tries >= 0) {
                int k = a[left + rand.nextInt(len)];

                List<Integer> list = m.get(k);
                int start = binary(list, left, true);
                int end = binary(list, right, false);
                if (end - start + 1 >= threshold) {
                    return k;
                }

                tries--;
            }
            return -1;
        }

        private int binary(List<Integer> list, int t, boolean fns) {
            int l = 0;
            int u = list.size() - 1;
            while (l <= u) {
                int mid = l + (u - l) / 2;
                if (list.get(mid) > t) {
                    u = mid - 1;
                } else if (list.get(mid) < t) {
                    l = mid + 1;
                } else {
                    if (fns) {
                        u = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }
            }
            return fns ? l : u;
        }

    }

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */

}

class MajorityCheckerSegmentTree {
    // using segment tree and binary search. binary search can check if an element is majority or not in logn time
    // not calculating real frequency here. the counter is just the voting algo "survived count"
// we are not negating every "non majority" element yet so if this number can survive it's the best candidate. otherwise there is no majority at all
    // segment trees are complete trees, so we use array representation!
    class Node {
        private int m;
        private int c;// majority and counter

        public Node(int m, int c) {
            this.m = m;
            this.c = c;
        }
    }

    private Node[] seg;
    private int size = 0;
    private Map<Integer, List<Integer>> indices = new HashMap<>();

    public MajorityCheckerSegmentTree(int[] a) {
        size = a.length;
        for (int i = 0; i < size; i++) {
            indices.computeIfAbsent(a[i], k -> new ArrayList<>()).add(i);
        }
        int treeLevel = (int) (Math.ceil(Math.log(size) / Math.log(2)) + 1);
        // java has no log2...
        seg = new Node[1 << treeLevel];
        build(0, a, 0, size - 1);
    }

    private Node merge(Node left, Node right) {
        if (left.m == right.m) {
            return new Node(left.m, left.c + right.c);
        } else if (left.c > right.c) {
            return new Node(left.m, left.c - right.c);
        } else {
            return new Node(right.m, right.c - left.c);
        }
    }

    private void build(int i, int[] a, int l, int r) {
        if (l == r) {
            seg[i] = new Node(a[l], 1);
            return;
        }
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int mid = l + (r - l) / 2;
        build(left, a, l, mid);
        build(right, a, mid + 1, r);
        Node cur = merge(seg[left], seg[right]);
        seg[i] = cur;
    }

    public int query(int l, int r, int t) {
        Node res = query(0, 0, size - 1, l, r);
        if (res.c > 0) {
            int cand = res.m;
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

    private Node query(int i, int l, int r, int tl, int tr) {
        // System.out.println(l+" "+r+" "+tl+" "+tr);
        // l,r is what this node at seg[i] stands for. tl, tr are the targets
        Node n = seg[i];
        if (tl <= l && tr >= r) { // when the query can fully cover this range, return
            return n;
        } else {
            int mid = l + (r - l) / 2;
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (tl >= mid + 1) {
                return query(right, mid + 1, r, tl, tr);
            } else if (tr <= mid) {
                return query(left, l, mid, tl, tr);
            }
            // l<=mid, r>=mid+1
            Node leftres = query(left, l, mid, tl, tr);
            Node rightres = query(right, mid + 1, r, tl, tr);
            return merge(leftres, rightres);
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
