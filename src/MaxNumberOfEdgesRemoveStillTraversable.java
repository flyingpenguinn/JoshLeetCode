import java.util.*;

public class MaxNumberOfEdgesRemoveStillTraversable {
    // try to keep type 3 edges. calculate MST using 3 first then 1 and 2. those 1 and 2 are deletable
    // note we need to use a set to filter duplicated edges like deltable 3
    private Set<List<Integer>> removed = new HashSet<>();

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int[] pa = new int[n + 1];

        List<int[]> ae = new ArrayList<>();
        List<int[]> be = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][0] == 1) {
                ae.add(edges[i]);
            } else if (edges[i][0] == 2) {
                be.add(edges[i]);
            } else {
                ae.add(edges[i]);
                be.add(edges[i]);
            }
        }
        int ra = count(ae, pa);
        if (ra == -1) {
            return -1;
        }
        int rb = count(be, pa);
        if (rb == -1) {
            return -1;
        }
        return removed.size();
    }

    private int count(List<int[]> es, int[] pa) {
        // 3 first
        Collections.sort(es, (x, y) -> Integer.compare(y[0], x[0]));

        Arrays.fill(pa, -1);
        int res = 0;
        for (int i = 0; i < es.size(); i++) {
            int[] e = es.get(i);
            int roota = find(e[1], pa);
            int rootb = find(e[2], pa);
            if (roota != rootb) {
                pa[roota] = rootb;
            } else {
                res++;
                removed.add(edge(e));
            }
        }
        boolean seen = false;
        for (int i = 1; i < pa.length; i++) {
            if (pa[i] == -1) {
                if (seen) {
                    return -1;
                }
                seen = true;
            }
        }
        return res;
    }

    private int find(int node, int[] pa) {
        if (pa[node] == -1) {
            return node;
        }
        int rt = find(pa[node], pa);
        pa[node] = rt;
        return rt;
    }

    private List<Integer> edge(int[] e) {
        return List.of(e[0], e[1], e[2]);
    }
}
