import base.ArrayUtils;

import java.util.*;

public class FindBuildingWhereAliceBobMeet {
    public int[] leftmostBuildingQueries(int[] a, int[][] qs) {
        int n = a.length;
        int qn = qs.length;
        int[][] nqs = new int[qn][3];
        for (int i = 0; i < qn; ++i) {
            nqs[i][0] = Math.min(qs[i][0], qs[i][1]);
            nqs[i][1] = Math.max(qs[i][0], qs[i][1]);
            nqs[i][2] = i;
        }
        Arrays.sort(nqs, (x, y) -> Integer.compare(x[1], y[1]));
        int[] res = new int[qn];
        Arrays.fill(res, -1);
        List<Integer> st = new ArrayList<>();
        int qi = qn - 1;
        for (int i = n - 1; i >= 0; --i) {

            while (!st.isEmpty() && a[st.get(st.size() - 1)] <= a[i]) {
                st.remove(st.size() - 1);
            }
            st.add(i);
            while (qi >= 0 && i == nqs[qi][1]) {
                int x = Math.min(nqs[qi][0], nqs[qi][1]);
                int y = Math.max(nqs[qi][0], nqs[qi][1]);
                int idx = nqs[qi][2];
                if (x == y) {
                    res[idx] = x;
                } else if (a[x] < a[y]) {
                    res[idx] = y;
                } else {
                    int pos = binary(a, st, a[x]);
                    if (pos >= 0 && pos < st.size()) {
                        res[idx] = st.get(pos);
                    }
                }

                --qi;
            }
        }
        return res;
    }

    private int binary(int[] a, List<Integer> st, int t) {
        int l = 0;
        int u = st.size() - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[st.get(mid)] > t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return u;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindBuildingWhereAliceBobMeet().leftmostBuildingQueries(ArrayUtils.read1d("[1,2,1,2,1,2]"), ArrayUtils.read("[[0,2]]"))));
    }

}

class FindBuildingWhereAliceBobMeetSegTree {
    // segtree binary search for the left most xxx
    // note this is different pruning from a "normal" segtree where we dont look for full seg hit but look for left/right BST like pruning
    static class Node {
        int l, r;
        int  max;


        Node(int l, int r, int max) {
            this.l = l;
            this.r = r;
            this.max = max;
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
                int v = a[l];
                tree[idx] = new Node(l, r, v);
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

                    Math.max(left.max, right.max)
            );
        }




        public Node query(int ql, int qr, int v) {
            return query(1, ql, qr, v);
        }

        private Node query(int idx, int ql, int qr, int v) {
            Node cur = tree[idx];

            if (qr < cur.l || cur.r < ql) {
                return null;
            }

            if (cur.l == cur.r) {
                return cur;
            }

            Node found = null;
            if (tree[idx * 2].max > v) {
                found = query(idx * 2, ql, qr, v);
            }
            if (found != null) {
                return found;
            } else if (tree[idx * 2 + 1].max > v) {
                found = query(idx * 2 + 1, ql, qr, v);
            }
            return found;

        }
    }

    public int[] leftmostBuildingQueries(int[] a, int[][] qs) {
        int n = a.length;
        int[] res = new int[qs.length];
        int ri = 0;
        SegTree seg = new SegTree(a);
        for (int[] q : qs) {
            int ql = q[0];
            int qr = q[1];
            if(ql == qr){
                res[ri++] = ql;
                continue;
            }
            int nql = Math.min(ql, qr);
            int nqr = Math.max(ql, qr);
            if (a[nql] < a[nqr]) {
                res[ri++] = nqr;
            } else {
                Node found = seg.query(nqr + 1, n - 1, a[nql]);
                if (found == null) {
                    res[ri++] = -1;
                } else {
                    res[ri++] = found.l;
                }
            }
        }
        return res;
    }
}


class FindBuildingWhereAliceBobMeetBinaryLifting {
    private int BITS = 17;

    public int[] leftmostBuildingQueries(int[] a, int[][] qs) {
        Deque<Integer> st = new ArrayDeque<>();
        int n = a.length;
        int[] next = new int[n];
        Arrays.fill(next, -1);

        for (int i = 0; i < n; ++i) {
            while (!st.isEmpty() && a[st.peek()] < a[i]) {
                next[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }
        int[][] up = new int[n][BITS];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(up[i], -1);
        }
        for (int j = 0; j < BITS; ++j) {
            for (int i = 0; i < n; ++i) {

                if (j == 0) {
                    up[i][j] = next[i];
                } else if (up[i][j - 1] != -1) {
                    up[i][j] = up[up[i][j - 1]][j - 1];
                }
            }
        }
        int qn = qs.length;
        int[] res = new int[qn];
        for (int i = 0; i < qn; ++i) {
            int s = qs[i][0];
            int e = qs[i][1];
            int v1 = Math.min(s, e);
            int v2 = Math.max(s, e);
            if (v1 == v2) {
                res[i] = v2;
            } else if (a[v1] < a[v2]) {
                res[i] = v2;
            } else {
                for (int j = BITS - 1; j >= 0; --j) {
                    if (up[v2][j] != -1 && a[up[v2][j]] <= a[v1]) {
                        v2 = up[v2][j];
                    }
                }
                final int cand = up[v2][0];
                if (cand != -1 && a[cand] > a[v1]) {
                    res[i] = cand;
                } else {
                    res[i] = -1;
                }
            }
        }
        return res;
    }
}