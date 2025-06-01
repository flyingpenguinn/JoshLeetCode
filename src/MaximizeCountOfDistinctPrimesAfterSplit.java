import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class MaximizeCountOfDistinctPrimesAfterSplit {
    public int[] maximumCount(int[] nums, int[][] queries) {
        int n = nums.length;
        int U = 100000;
        // 1) Sieve for primes up to U
        boolean[] isP = new boolean[U + 1];
        Arrays.fill(isP, true);
        isP[0] = false;
        isP[1] = false;
        for (int i = 2; i * i <= U; i++) {
            if (isP[i]) {
                for (int j = i * i; j <= U; j += i) {
                    isP[j] = false;
                }
            }
        }

        // 2) Build occ: map each prime value to a sorted set of indices where it appears
        Map<Integer, TreeSet<Integer>> occ = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int v = nums[i];
            if (v <= U && isP[v]) {
                occ.computeIfAbsent(v, x -> new TreeSet<>()).add(i);
            }
        }

        // 3) Build segment tree over k = 1..n, initially all zeros
        Seg seg = new Seg(n);

        // 4) For each prime p in occ, let f = first index, l = last index.
        //    - prefix contribution: add +1 on range [f+1 .. n]
        //    - suffix contribution: add +1 on range [1 .. l]
        for (TreeSet<Integer> st : occ.values()) {
            int f = st.first();
            int l = st.last();
            seg.updateRange(f + 1, n, +1, 1, 1, n);  // all k ≥ f+1 get +1
            seg.updateRange(1, l, +1, 1, 1, n);     // all k ≤ l get +1
        }

        int m = queries.length;
        int[] ans = new int[m];
        for (int qi = 0; qi < m; qi++) {
            int idx = queries[qi][0];
            int val = queries[qi][1];
            int old = nums[idx];

            if (old != val) {
                // A) Remove old at position idx, if old was prime
                if (old <= U && isP[old]) {
                    TreeSet<Integer> st0 = occ.get(old);
                    int f0 = st0.first();
                    int l0 = st0.last();
                    st0.remove(idx);

                    if (st0.isEmpty()) {
                        occ.remove(old);
                        // remove prefix: range [f0+1 .. n] -= 1
                        seg.updateRange(f0 + 1, n, -1, 1, 1, n);
                        // remove suffix: range [1 .. l0] -= 1
                        seg.updateRange(1, l0, -1, 1, 1, n);
                    } else {
                        int f1 = st0.first();
                        int l1 = st0.last();
                        // if first changed from f0→f1: for k ≥ f0+1 remove 1, for k ≥ f1+1 add 1
                        if (f1 != f0) {
                            seg.updateRange(f0 + 1, n, -1, 1, 1, n);
                            seg.updateRange(f1 + 1, n, +1, 1, 1, n);
                        }
                        // if last changed from l0→l1: for k ≤ l0 remove 1, for k ≤ l1 add 1
                        if (l1 != l0) {
                            seg.updateRange(1, l0, -1, 1, 1, n);
                            seg.updateRange(1, l1, +1, 1, 1, n);
                        }
                    }
                }

                // B) Add new value 'val' at position idx, if val is prime
                if (val <= U && isP[val]) {
                    TreeSet<Integer> st1 = occ.get(val);
                    if (st1 == null) {
                        // brand‐new prime
                        st1 = new TreeSet<>();
                        occ.put(val, st1);
                        st1.add(idx);
                        // prefix: add +1 on [idx+1 .. n]
                        seg.updateRange(idx + 1, n, +1, 1, 1, n);
                        // suffix: add +1 on [1 .. idx]
                        seg.updateRange(1, idx, +1, 1, 1, n);
                    } else {
                        int f0 = st1.first();
                        int l0 = st1.last();
                        st1.add(idx);
                        int f1 = st1.first();
                        int l1 = st1.last();
                        // if first moved from f0→f1: k ≥ f0+1 -=1, k ≥ f1+1 +=1
                        if (f1 != f0) {
                            seg.updateRange(f0 + 1, n, -1, 1, 1, n);
                            seg.updateRange(f1 + 1, n, +1, 1, 1, n);
                        }
                        // if last moved from l0→l1: k ≤ l0 -=1, k ≤ l1 +=1
                        if (l1 != l0) {
                            seg.updateRange(1, l0, -1, 1, 1, n);
                            seg.updateRange(1, l1, +1, 1, 1, n);
                        }
                    }
                }

                // C) Commit the change
                nums[idx] = val;
            }

            // D) Query the maximum over T[1..n-1] (if n>1)
            if (n > 1) {
                ans[qi] = seg.queryMax(1, n - 1, 1, 1, n);
            } else {
                ans[qi] = 0;
            }
        }

        return ans;
    }

    // Segment Tree supporting range-add and range-max over [1..N]
    static class Seg {
        int N;
        int[] mx;
        int[] lazy;

        public Seg(int n) {
            N = n;
            mx = new int[4 * (N + 1)];
            lazy = new int[4 * (N + 1)];
        }

        // push lazy down
        private void push(int u) {
            int val = lazy[u];
            if (val != 0) {
                int lc = u << 1;
                int rc = u << 1 | 1;
                mx[lc] += val;
                mx[rc] += val;
                lazy[lc] += val;
                lazy[rc] += val;
                lazy[u] = 0;
            }
        }

        // pull up from children
        private void pull(int u) {
            mx[u] = Math.max(mx[u << 1], mx[u << 1 | 1]);
        }

        // update range [L..R] by adding v
        public void updateRange(int L, int R, int v, int u, int l, int r) {
            if (R < l || r < L) return;
            if (L <= l && r <= R) {
                mx[u] += v;
                lazy[u] += v;
                return;
            }
            push(u);
            int mid = (l + r) >>> 1;
            updateRange(L, R, v, u << 1, l, mid);
            updateRange(L, R, v, u << 1 | 1, mid + 1, r);
            pull(u);
        }

        // query max on [L..R]
        public int queryMax(int L, int R, int u, int l, int r) {
            if (R < l || r < L) return Integer.MIN_VALUE;
            if (L <= l && r <= R) {
                return mx[u];
            }
            push(u);
            int mid = (l + r) >>> 1;
            return Math.max(
                    queryMax(L, R, u << 1, l, mid),
                    queryMax(L, R, u << 1 | 1, mid + 1, r)
            );
        }
    }
}
