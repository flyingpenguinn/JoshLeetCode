public class ValidKUniqueSubarraysI {
    static class Fenwick {
        int[] bit;
        int n;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 1];
        }

        void add(int i, int v) {
            ++i;
            while (i <= n) {
                bit[i] += v;
                i += i & -i;
            }
        }

        int sum(int i) {
            ++i;
            int res = 0;
            while (i > 0) {
                res += bit[i];
                i -= i & -i;
            }
            return res;
        }

        int query(int l, int r) {
            return sum(r) - (l == 0 ? 0 : sum(l - 1));
        }
    }

    static class Query {
        int l, r, idx;

        Query(int l, int r, int idx) {
            this.l = l;
            this.r = r;
            this.idx = idx;
        }
    }

    public boolean[] validSubarrays(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        int qn = queries.length;

        long[] hash = new long[100001];
        for (int i = 1; i <= 100000; ++i) {
            hash[i] = mix(i);
        }

        long[] px = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            px[i + 1] = px[i] ^ hash[nums[i]];
        }

        Query[] qs = new Query[qn];
        for (int i = 0; i < qn; ++i) {
            qs[i] = new Query(queries[i][0], queries[i][1], i);
        }

        Arrays.sort(qs, (x, y) -> Integer.compare(x.r, y.r));

        boolean[] res = new boolean[qn];

        Fenwick fen = new Fenwick(n);
        int[] last = new int[100001];
        Arrays.fill(last, -1);

        int cur = -1;

        for (Query q : qs) {
            while (cur < q.r) {
                ++cur;

                int v = nums[cur];

                if (last[v] != -1) {
                    fen.add(last[v], -1);
                }

                fen.add(cur, 1);
                last[v] = cur;
            }

            if (px[q.r + 1] != px[q.l]) {
                continue;
            }

            int distinct = fen.query(q.l, q.r);
            res[q.idx] = distinct == k;
        }

        return res;
    }

    private long mix(long x) {
        x += 0x9e3779b97f4a7c15L;
        x = (x ^ (x >>> 30)) * 0xbf58476d1ce4e5b9L;
        x = (x ^ (x >>> 27)) * 0x94d049bb133111ebL;
        return x ^ (x >>> 31);
    }
}
