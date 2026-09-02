import java.util.Arrays;

public class ValidKUniqueSubarraysII {
    int[][] tree;
    int[] pre;

    public boolean[] validSubarrays(int[] nums, int k, int l0, int r0, int q) {
        int n = nums.length;

        pre = new int[n];
        int[] last = new int[500001];
        Arrays.fill(last, -1);
        for (int i = 0; i < n; ++i) {
            pre[i] = last[nums[i]];
            last[nums[i]] = i;
        }

        tree = new int[n * 4][];
        build(1, 0, n - 1);

        long[] h1 = new long[500001];
        long[] h2 = new long[500001];
        for (int x : nums) {
            if (h1[x] == 0) {
                h1[x] = hash(x);
                h2[x] = hash(x + 500001L);
            }
        }

        long[] px1 = new long[n + 1];
        long[] px2 = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            px1[i + 1] = px1[i] ^ h1[nums[i]];
            px2[i + 1] = px2[i] ^ h2[nums[i]];
        }

        boolean[] ans = new boolean[q];
        int l = l0;
        int r = r0;

        for (int i = 0; i < q; ++i) {
            int d = query(1, 0, n - 1, l, r, l);
            boolean even = (px1[r + 1] ^ px1[l]) == 0
                    && (px2[r + 1] ^ px2[l]) == 0;
            ans[i] = d == k && even;

            if (i + 1 < q) {
                int g = ans[i] ? l + r : r - l;
                int nl = (l ^ g) % n;
                int nr = (r ^ g) % n;
                l = Math.min(nl, nr);
                r = Math.max(nl, nr);
            }
        }

        return ans;
    }

    void build(int p, int l, int r) {
        if (l == r) {
            tree[p] = new int[]{pre[l]};
            return;
        }
        int m = (l + r) >>> 1;
        build(p << 1, l, m);
        build(p << 1 | 1, m + 1, r);
        int[] a = tree[p << 1];
        int[] b = tree[p << 1 | 1];
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0, t = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) c[t++] = a[i++];
            else c[t++] = b[j++];
        }
        while (i < a.length) c[t++] = a[i++];
        while (j < b.length) c[t++] = b[j++];
        tree[p] = c;
    }

    int query(int p, int l, int r, int ql, int qr, int x) {
        if (ql <= l && r <= qr) return lowerBound(tree[p], x);
        int m = (l + r) >>> 1;
        int res = 0;
        if (ql <= m) res += query(p << 1, l, m, ql, qr, x);
        if (qr > m) res += query(p << 1 | 1, m + 1, r, ql, qr, x);
        return res;
    }

    int lowerBound(int[] a, int x) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] < x) l = m + 1;
            else r = m;
        }
        return l;
    }

    long hash(long x) {
        x += 0x9e3779b97f4a7c15L;
        x = (x ^ (x >>> 30)) * 0xbf58476d1ce4e5b9L;
        x = (x ^ (x >>> 27)) * 0x94d049bb133111ebL;
        return x ^ (x >>> 31);
    }
}
