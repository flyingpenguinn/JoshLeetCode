import base.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SortItemsByGroupRespectingDependencies {
    // hierarchical topo sorting. as long as group1  has one item -> group 2, the whole group 1 must appear before group2
    // make -1 groups extra standalone single member groups
    // topo sort groups first then within each group sort the items in the same manner
    // beware of empty groups and empty items, stil need to include them!
    private Map<Integer, Set<Integer>> gg = new HashMap<>();
    private Map<Integer, Map<Integer, Set<Integer>>> ig = new HashMap<>();

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        int gindex = m;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = gindex++;
            }
            gg.put(group[i], new HashSet<>());
        }
        for (int i = 0; i < n; i++) {
            List<Integer> blist = beforeItems.get(i);
            Map<Integer, Set<Integer>> items = ig.get(group[i]);
            if (items == null) {
                items = new HashMap<>();
                ig.put(group[i], items);
            }
            Set<Integer> iset = new HashSet<>();
            for (int b : blist) {
                if (group[b] == group[i]) {
                    iset.add(b);
                }
                if (group[b] != group[i]) {
                    gg.computeIfAbsent(group[i], k -> new HashSet<>()).add(group[b]);
                }
            }
            items.put(i, iset);
        }
        List<Integer> gtopo = topo(gg);
        if (gtopo == null) {
            return new int[0];
        }
        List<Integer> res = new ArrayList<>();
        for (int g : gtopo) {
            List<Integer> itopo = topo(ig.get(g));
            if (itopo == null) {
                return new int[0];
            }
            res.addAll(itopo);
        }
        return toarray(res);
    }

    private int[] toarray(List<Integer> list) {
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private List<Integer> topo(Map<Integer, Set<Integer>> g) {
        Map<Integer, Integer> st = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int k : g.keySet()) {
            if (!st.containsKey(k)) {
                boolean good = dfs(g, k, st, res);
                if (!good) {
                    return null;
                }
            }
        }
        return res;
    }

    private boolean dfs(Map<Integer, Set<Integer>> g, int i, Map<Integer, Integer> st, List<Integer> res) {
        st.put(i, 1);
        for (int next : g.getOrDefault(i, new HashSet<>())) {
            int nst = st.getOrDefault(next, 0);
            if (nst == 1) {
                return false;
            } else if (nst == 0) {
                boolean later = dfs(g, next, st, res);
                if (!later) {
                    return false;
                }
            }
        }
        st.put(i, 2);
        res.add(i);
        return true;
    }


    public static void main(String[] args) throws IOException {
        /*
        String file = "E:\\dev\\project\\JoshLeet\\tests\\sol.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
        String n = reader.readLine();
        String m = reader.readLine();
        String groups = reader.readLine();
        String dep = reader.readLine();
        System.out.println(Arrays.toString(new SortItemsByGroupRespectingDependencies().sortItems(Integer.valueOf(n),Integer.valueOf(m), ArrayUtils.read1d(groups), ArrayUtils.readAsListUneven(dep))));

        */

        System.out.println(Arrays.toString(new SortItemsByGroupRespectingDependencies().sortItems(8, 2, ArrayUtils.read1d("[-1,-1,1,0,0,1,0,-1]"), ArrayUtils.readAsListUneven("[[],[6],[5],[6],[3,6],[],[],[]]"))));
        System.out.println(Arrays.toString(new SortItemsByGroupRespectingDependencies().sortItems(8, 2, ArrayUtils.read1d("[-1,-1,1,0,0,1,0,-1]"), ArrayUtils.readAsListUneven("[[],[6],[5],[6],[3],[],[4],[]]"))));
        System.out.println(Arrays.toString(new SortItemsByGroupRespectingDependencies().sortItems(5, 5, ArrayUtils.read1d("[2,0,-1,3,0]"), ArrayUtils.readAsListUneven("[[2,1,3],[2,4],[],[],[]]"))));

    }
}
