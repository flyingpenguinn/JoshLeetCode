import base.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SortItemsByGroupRespectingDependencies {
    // hierarchical topo sorting. as long as group1  has one item -> group 2, the whole group 1 must appear before group2
    Set<Integer>[] itemDep;
    Map<Integer, Set<Integer>> groupdep;
    int[] itemToGroup;
    Map<Integer, Set<Integer>> groupToItem = new HashMap<>();

    int[] v; // for item dfs
    int[] sortedItems;
    int itemp;

    Map<Integer, Integer> groupStatus = new HashMap<>(); // for group dfs

    LinkedList<Integer> sortedGroups = new LinkedList<>();

    private boolean invalid = false;

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        long start = System.currentTimeMillis();
        groupdep = new HashMap<>();
        itemToGroup = new int[n];
        sortedItems = new int[n];
        itemp= n-1;
        v = new int[n];

        int extragroupToItem = n + 1;
        for (int i = 0; i < group.length; i++) {
            int groupnum = group[i];
            if (groupnum == -1) {
                // -1 ones form their own group
                groupnum = extragroupToItem++;
            }
            Set<Integer> cur = groupToItem.getOrDefault(groupnum, new HashSet<>());
            cur.add(i);
            groupToItem.put(groupnum, cur);
            itemToGroup[i] = groupnum;
            groupdep.put(groupnum, new HashSet<>());
        }
        itemDep = new HashSet[n];
        for (int i = 0; i < n; i++) {
            itemDep[i] = new HashSet<>();
        }
        for (int i = 0; i < beforeItems.size(); i++) {
            for (int b : beforeItems.get(i)) {
                // b=> i
                itemDep[b].add(i);
                int gb = itemToGroup[b];
                int gi = itemToGroup[i];
                if (gb != gi) {
                    // avoid self group
                    Set<Integer> curgdep = groupdep.getOrDefault(gb, new HashSet<>());
                    curgdep.add(gi);
                    groupdep.put(gb, curgdep);
                }
            }
        }

        long prep = System.currentTimeMillis();
  //      System.out.println(prep-start);
        if (topoGroup()) {
            return new int[0];
        }
        long toppogrpup = System.currentTimeMillis();
    //    System.out.println(toppogrpup-prep);
        if (topoInGroup()) {
            return new int[0];
        }
        long toppoingrpup = System.currentTimeMillis();
      //  System.out.println(toppoingrpup-toppogrpup);

        return sortedItems;
    }

    private boolean topoGroup() {

        for (int g : groupdep.keySet()) {
            dfsGroup(g);
            if (invalid) {
                return true;
            }
        }
        return false;
    }

    private void dfsGroup(int g) {
        groupStatus.put(g, 1);
        for (int ng : groupdep.getOrDefault(g, new HashSet<>())) {
            Integer curstatus = groupStatus.get(ng);
            if (curstatus == null) {
                dfsGroup(ng);
            } else if (curstatus == 1) {
                invalid = true;
                return;
            }
        }
        groupStatus.put(g, 2);
        sortedGroups.addFirst(g);
    }


    // return if it's invalid
    private boolean topoInGroup() {

        for (int gi = sortedGroups.size() - 1; gi >= 0; gi--) {
            int g = sortedGroups.get(gi);
            for (int i : groupToItem.get(g)) {
                if (v[i] == 0) {
                    dfs(i);
                    if (invalid) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void dfs(int i) {
        v[i] = 1;
        Set<Integer> direct = itemDep[i];
        Set<Integer> groupItem = groupToItem.getOrDefault(itemToGroup[i], new HashSet<>());
        for (int di : direct) {
            if (!groupItem.contains(di)) {
                // only visit my own group
                continue;
            }
            if (v[di] == 0) {
                dfs(di);
            } else if (v[di] == 1) {
                invalid = true;
                return;
            } else {
                // we have visited this in another group so just skip
            }
        }
        v[i] = 2;
        sortedItems[itemp--]= i;
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

        System.out.println(Arrays.toString(new SortItemsByGroupRespectingDependencies().sortItems(8,2, ArrayUtils.read1d("[-1,-1,1,0,0,1,0,-1]"), ArrayUtils.readAsListUneven("[[],[6],[5],[6],[3,6],[],[],[]]"))));
        System.out.println(Arrays.toString(new SortItemsByGroupRespectingDependencies().sortItems(8,2, ArrayUtils.read1d("[-1,-1,1,0,0,1,0,-1]"), ArrayUtils.readAsListUneven("[[],[6],[5],[6],[3],[],[4],[]]"))));
        System.out.println(Arrays.toString(new SortItemsByGroupRespectingDependencies().sortItems(5, 5, ArrayUtils.read1d("[2,0,-1,3,0]"), ArrayUtils.readAsListUneven("[[2,1,3],[2,4],[],[],[]]"))));

    }
}
