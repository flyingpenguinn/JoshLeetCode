import base.ArrayUtils;

import java.util.*;

public class FindBuildingWhereAliceBobMeet {
    class Query {
        int v2;
        int v1;
        int index;
        int result;

        public Query(int v1, int v2, int index, int result) {
            this.v2 = v2;
            this.v1 = v1;
            this.index = index;
            this.result = result;
        }
    }

    class BinarySearchStack {
        List<Integer> st = new ArrayList<>();

        public void push(int v) {
            st.add(v);
        }

        public int peek() {
            return st.get(st.size() - 1);
        }

        public boolean isEmpty() {
            return st.isEmpty();
        }

        public int pop() {
            int rt = peek();
            st.remove(st.size() - 1);
            return rt;
        }

        public int size() {
            return st.size();
        }

        // the order in st is actually reversed. the back is the smallest
        public int binaryLastBigger(int[] a, int t) {
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

        public int get(int pos) {
            return st.get(pos);
        }
    }

    public int[] leftmostBuildingQueries(int[] a, int[][] queries) {
        Map<Integer, List<Query>> qm = new HashMap<>();
        int qn = queries.length;
        List<Query> lres = new ArrayList<>();
        for (int i = 0; i < qn; ++i) {
            int[] q = queries[i];
            int ov1 = q[0];
            int ov2 = q[1];
            int v1 = Math.min(ov1, ov2);
            int v2 = Math.max(ov1, ov2);
            if (v1 == v2) {
                lres.add(new Query(v1, v2, i, v2));
            } else if (a[v1] < a[v2]) {
                lres.add(new Query(v1, v2, i, v2));
            } else {
                qm.computeIfAbsent(v2, k -> new ArrayList<>()).add(new Query(v1, v2, i, -1));
            }
        }
        int n = a.length;
        BinarySearchStack stack = new BinarySearchStack();
        for (int i = n - 1; i >= 0; --i) {
            while (!stack.isEmpty() && a[stack.peek()] <= a[i]) {
                stack.pop();
            }
            for (Query q : qm.getOrDefault(i, new ArrayList<>())) {
                int v1 = q.v1;
                int pos = stack.binaryLastBigger(a, a[v1]);
                if (pos == -1) {
                    lres.add(new Query(v1, i, q.index, -1));
                } else {
                    lres.add(new Query(v1, i, q.index, stack.get(pos)));
                }
            }
            stack.push(i);

        }
        int[] res = new int[qn];
        for (int i = 0; i < lres.size(); ++i) {
            Query cq = lres.get(i);
            int index = cq.index;
            res[index] = cq.result;
        }
        return res;
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
        int n;

        SegTree(int[] a) {
            this.n = a.length;
            this.tree = new Node[4 * n + 5];

            build(1, 0, n - 1, a);
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


        public Node query(int ql, int qr, int v) {
            return query2(1, ql, qr, v);
        }

        private Node query2(int idx, int ql, int qr, int v) {
            if (idx >= tree.length || tree[idx] == null) {
                return null;
            }
            Node cur = tree[idx];

            if (qr < cur.l || cur.r < ql) {
                return null;
            }
            if (cur.max < v) {
                return null;
            }
            if (cur.l == cur.r) {
                return cur.max >= v ? cur : null;
            }


            Node left = query2(idx * 2, ql, qr, v);
            if (left != null && left.max >= v) {
                return left;
            }
            Node right = query2(idx * 2 + 1, ql, qr, v);
            if (right != null && right.max >= v) {
                return right;
            }
            return null;
        }
    }

    public int[] leftmostBuildingQueries(int[] a, int[][] qs) {
        int n = a.length;
        SegTree seg = new SegTree(a);

        int[] res = new int[qs.length];
        Arrays.fill(res, -1);
        int ri = 0;
        for (int[] q : qs) {
            int ov1 = q[0];
            int ov2 = q[1];
            int v1 = Math.min(ov1, ov2);
            int v2 = Math.max(ov1, ov2);
            if (v1 == v2) {
                res[ri++] = v2;
            } else if (a[v1] < a[v2]) {
                res[ri++] = v2;
            } else {
                Node cur = seg.query(v2 + 1, n - 1, a[v1] + 1);
                if (cur != null) {
                    res[ri++] = (int) cur.l;
                } else {
                    res[ri++] = -1;
                }
            }
        }
        return res;
    }
}


class FindBuildingWhereAliceBobMeetBinaryLifting {
    // binary lifting template!
    private int[][] lift;
    private int BITS = 18;

    public int[] leftmostBuildingQueries(int[] a, int[][] qs) {
        Deque<Integer> st = new ArrayDeque<>();
        int n = a.length;
        lift = new int[BITS][n];
        for (int i = 0; i < BITS; ++i) {
            Arrays.fill(lift[i], -1);
        }
        for (int i = 0; i < n; ++i) {
            while (!st.isEmpty() && a[st.peek()] < a[i]) {
                int pre = st.pop();
                lift[0][pre] = i;
            }
            st.push(i);
        }
        buildlifting(n);
        int[] res = new int[qs.length];
        int ri = 0;
        for (int[] q : qs) {
            int ov1 = q[0];
            int ov2 = q[1];
            int v1 = Math.min(ov1, ov2);
            int v2 = Math.max(ov1, ov2);
            if (v1 == v2) {
                res[ri++] = v2;
            } else if (a[v1] < a[v2]) {
                res[ri++] = v2;
            } else {
                int t = lifting(a, v2, a[v1]);
                res[ri++] = t;
            }
        }
        return res;
    }

    private int lifting(int[] a, int start, int t) {
        int cur = start;
        for (int j = BITS - 1; j >= 0; --j) {
            if (lift[j][cur] != -1 && a[lift[j][cur]] <= t) {
                cur = lift[j][cur];
            }
        }
        if (lift[0][cur] != -1 && a[lift[0][cur]] > t) {
            return lift[0][cur];
        } else {
            return -1;
        }
    }

    private void buildlifting(int n) {
        for (int k = 1; k < BITS; k++) {
            for (int i = 0; i < n; i++) {
                if (lift[k - 1][i] != -1) {
                    lift[k][i] = lift[k - 1][lift[k - 1][i]];
                }
            }
        }
    }
}