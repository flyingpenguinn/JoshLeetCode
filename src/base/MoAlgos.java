package base;

import java.util.Arrays;

public class MoAlgos {
    /*
    if sqrt decomp needs merging maps consider Mo algo.
    only need to change the calc part

    static array
many [l, r] queries
answer depends on frequencies
single add/remove is O(1) or O(log n)
block merge would be hard
=> Mo
     */


    static class Query {
        int l;
        int r;
        int idx;

        public Query(int l, int r, int idx) {
            this.l = l;
            this.r = r;
            this.idx = idx;
        }
    }

    static class Mo {
        int n;
        int B;
        int[] a;

        int[] cnt;
        long cur = 0;

        public Mo(int[] a, int maxVal) {
            this.a = a;
            this.n = a.length;
            this.B = (int)Math.sqrt(n) + 1;
            this.cnt = new int[maxVal + 1];
        }

        public long[] solve(int[][] queries) {
            int qn = queries.length;
            Query[] qs = new Query[qn];

            for (int i = 0; i < qn; i++) {
                qs[i] = new Query(queries[i][0], queries[i][1], i);
            }

            Arrays.sort(qs, (x, y) -> {
                int bx = x.l / B;
                int by = y.l / B;

                if (bx != by) {
                    return bx - by;
                }

                if ((bx & 1) == 0) {
                    return x.r - y.r;
                } else {
                    return y.r - x.r;
                }
            });

            long[] res = new long[qn];

            int l = 0;
            int r = -1;

            for (Query q : qs) {
                while (l > q.l) {
                    --l;
                    add(a[l]);
                }

                while (r < q.r) {
                    ++r;
                    add(a[r]);
                }

                while (l < q.l) {
                    remove(a[l]);
                    ++l;
                }

                while (r > q.r) {
                    remove(a[r]);
                    --r;
                }

                res[q.idx] = cur;
            }

            return res;
        }

        private void add(int x) {
            cur -= calc(cnt[x]);
            cnt[x]++;
            cur += calc(cnt[x]);
        }

        private void remove(int x) {
            cur -= calc(cnt[x]);
            cnt[x]--;
            cur += calc(cnt[x]);
        }

        private long calc(long c) {
            return c * c;
        }
    }
}
