import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MinFuelToReachCapital {
    // could be simplified to ceil(n/seats), where n is the size of subtree
    private Map<Integer, Set<Integer>> tree = new HashMap<>();

    public long minimumFuelCost(int[][] roads, int seats) {
        for (int[] r : roads) {
            int s = r[0];
            int e = r[1];
            tree.computeIfAbsent(s, k -> new HashSet<>()).add(e);
            tree.computeIfAbsent(e, k -> new HashSet<>()).add(s);
        }
        long[] res = dfs(0, -1, seats);
        return res[0];
    }

    // cost, cars, remaining nodes
    private long[] dfs(int i, int p, int seats) {
        long cnodes = 0;
        long ccost = 0;
        long ccars = 0;
        for (int ne : tree.getOrDefault(i, new HashSet<>())) {
            if (ne == p) {
                continue;
            }
            long[] rne = dfs(ne, i, seats);
            long necost = rne[0];
            ccost += necost;
            long necars = rne[1];
            ccost += necars;
            ccars += necars;
            long nenodes = rne[2];
            cnodes += nenodes;
            ccost += nenodes > 0 ? 1 : 0; // all these remaining nodes in one car is enough
        }
        long ncars = (cnodes + 1) / seats;
        long allcars = ccars + ncars;
        long rmnodes = (cnodes + 1) % seats;

        return new long[]{ccost, allcars, rmnodes};
    }
}
