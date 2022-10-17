import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CreateComponentsWithSameValue {

    private Map<Integer, Set<Integer>> tree = new HashMap<>();

    public int componentValue(int[] a, int[][] edges) {
        int n = a.length;
        int sum = 0;
        int max = 0;
        for (int i = 0; i < n; ++i) {
            sum += a[i];
            max = Math.max(max, a[i]);
        }
        for (int[] e : edges) {
            int start = e[0];
            int end = e[1];
            tree.computeIfAbsent(start, p -> new HashSet<>()).add(end);
            tree.computeIfAbsent(end, p -> new HashSet<>()).add(start);
        }
        int maxres = 0;
        for (int i = 1; i <= (int) Math.sqrt(sum); ++i) {
            if (sum % i == 0) {
                int segs = sum / i;

                if (segs >= max) {
                    int cur = dfs(a, 0, segs, -1)[1];
                    maxres = Math.max(maxres, cur);
                }


                if (i >= max) {
                    int cur = dfs(a, 0, i, -1)[1];
                    maxres = Math.max(maxres, cur);
                }
            }
        }
        return maxres;
    }

    private int MIN = (int)-1e9;
    private int[] dfs(int[] a, int i, int k, int parent) {

        int sum = a[i];
        int res = 0;
        for (int ne : tree.getOrDefault(i, new HashSet<>())) {
            if (ne == parent) {
                continue;
            }
            int[] cne = dfs(a, ne, k, i);
            if(cne[1] <0){
                return new int[]{0, MIN};
            }
            res += cne[1];
            if (cne[0] % k == 0) {
                // we can potentially cut here
                ++res;
            }
            sum += cne[0];
        }
        if (sum % k == 0) {
            if (sum / k == res + 1) {
                // verify that we indeed can split to res+1 parts
                return new int[]{sum, res};
            } else {
                return new int[]{sum, MIN};
            }
        } else {
            return new int[]{sum, res};
        }
    }
}
