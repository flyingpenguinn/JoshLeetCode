import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class CountWaysToBuildRoomInAntColony {
    // we get results from subtrees... Rs1, Rs2...
    // but they can interleave. among all n-1 subtree nodes, we have (n-1)! ways to permute them. but Ns1, Ns2... nodes' orders are fixed
    // so we end up having Rcur = ((Ncur-1)!/Ns1*Ns2...) * Rs1*Rs2...
    // then the trick is to use (b/a) % m  = b*(a mod inverse m) to get the mod result

    private final int Mod = 1000000007;
    private int[] children;

    public int waysToBuildRooms(int[] prev) {
        int n = prev.length;
        children = new int[n];
        factor = new long[n + 1];
        initFact(n);
        List<Integer>[] tree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            tree[prev[i]].add(i);
        }
        long res = solve(0, tree, children);
        return (int) res;
    }

    private long solve(int i, List<Integer>[] tree, int[] children) {
        children[i] = 1;
        long num = 1;
        long denom = 1;
        for (int ne : tree[i]) {
            long cr = solve(ne, tree, children);
            children[i] += children[ne];
            num *= cr;
            num %= Mod;
            long fcc = factor[children[ne]];
            denom *= fcc;
            denom %= Mod;
        }
        num *= factor[children[i] - 1];
        num %= Mod;
        num *= modInverse(denom, Mod);
        num %= Mod;
        return num;
    }

    private long[] factor;

    private void initFact(int n) {
        long last = 1L;
        factor[0] = last;
        for (int i = 1; i <= n; i++) {
            long cur = last * i;
            cur %= Mod;
            factor[i] = cur;
            last = cur;
        }
    }

    // copied mod inverse code
    private long modInverse(long a, long m) {
        long m0 = m;
        long y = 0, x = 1;
        if (m == 1) {
            return 0;
        }
        while (a > 1) {
            // q is quotient
            long q = a / m;
            long t = m;
            // m is remainder now, process
            // same as Euclid's algo
            m = a % m;
            a = t;
            t = y;
            // Update x and y
            y = x - q * y;
            x = t;
        }
        // Make x positive
        if (x < 0) {
            x += m0;
        }
        return x;
    }
}
