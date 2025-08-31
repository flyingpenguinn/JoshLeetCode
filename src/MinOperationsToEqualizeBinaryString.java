import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public class MinOperationsToEqualizeBinaryString {
    public int minOperations(String s, int k) {
        int n = s.length();

        // Count how many zeros we start with: this is our start state "c = #zeros".
        int start = 0;
        for (int i = 0; i < n; i++) if (s.charAt(i) == '0') start++;
        if (start == 0) return 0; // already all '1's

        // dist[c] = min #operations to reach a string with exactly c zeros; -1 = unvisited
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        // Two ordered sets store all UNVISITED counts, split by parity.
        // From any c, all reachable c' in one step share the same parity (c' ≡ c + k mod 2).
        @SuppressWarnings("unchecked")
        TreeSet<Integer>[] unvis = new TreeSet[2];
        unvis[0] = new TreeSet<>();
        unvis[1] = new TreeSet<>();
        for (int c = 0; c <= n; c++) unvis[c & 1].add(c);

        // Standard BFS queue on the "c = #zeros" state space.
        ArrayDeque<Integer> q = new ArrayDeque<>();
        dist[start] = 0;
        unvis[start & 1].remove(start); // mark visited by erasing from its parity set
        q.add(start);

        while (!q.isEmpty()) {
            int c = q.poll();            // current #zeros
            int ones = n - c;

            // Choose x zeros and (k - x) ones to flip → c' = c + k - 2x.
            // Feasible x ∈ [x_min, x_max], where:
            int xMin = Math.max(0, k - ones); // must have enough ones to pick k - x
            int xMax = Math.min(k, c);        // can't pick more zeros than exist

            // Map that to the interval of reachable c':
            // c' ranges from c + k - 2*xMax up to c + k - 2*xMin, stepping by 2 (same parity).
            int lo = c + k - 2 * xMax;
            int hi = c + k - 2 * xMin;

            // All those c' have parity (c + k) & 1. We can bulk-pull unvisited nodes
            // from the corresponding ordered set in the interval [lo, hi].
            TreeSet<Integer> set = unvis[(c + k) & 1];

            // Iterate over the still-unvisited nodes in [lo, hi]:
            // Use a tail view starting at lo; stop when we pass hi.
            for (Iterator<Integer> it = set.tailSet(lo, true).iterator(); it.hasNext(); ) {
                int v = it.next();
                if (v > hi) break;            // interval end
                dist[v] = dist[c] + 1;        // first time we see v → shortest distance
                q.add(v);
                it.remove();                  // mark visited: erase so we never touch v again
            }
        }

        return dist[0]; // -1 if unreachable, else min #operations to get 0 zeros (all '1's)
    }
}
